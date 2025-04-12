package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.command.MainCommand;
import org.example.command.OpenMenuCommand;
import org.example.customization.impl.PlayerCustomizationService;
import org.example.listener.PlayerListener;
import org.example.menu.action.MenuActionRegistry;
import org.example.menu.loader.MenuLoader;
import org.example.menu.manager.MenuManager;

import java.io.File;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CustomizePlugin extends JavaPlugin {

    @Getter
    public static CustomizePlugin plugin;

    @Getter
    public static LuckPerms luckPerms;

    PlayerCustomizationService playerCustomizationService;
    MenuLoader menuLoader;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        playerCustomizationService = new PlayerCustomizationService(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(playerCustomizationService, this), this);
        getCommand("customize").setExecutor(new MainCommand(this));

        setupLuckPerms();


        menuLoader = new MenuLoader(new MenuActionRegistry());
        menuLoader.loadMenus(this);

        getCommand("openmenu").setExecutor(new OpenMenuCommand(menuLoader));

        getServer().getPluginManager().registerEvents(new MenuManager(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupLuckPerms(){
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        } else {
            Bukkit.getLogger().warning("Не удалось загрузить luckperms");
        }
    }
}
