package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.val;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.command.CustomizeCommand;
import org.example.customization.impl.PlayerCustomizationService;
import org.example.listener.PlayerListener;

import java.io.File;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CustomizePlugin extends JavaPlugin {

    @Getter
    public static CustomizePlugin plugin;

    @Getter
    public static LuckPerms luckPerms;

    PlayerCustomizationService playerCustomizationService;


    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        playerCustomizationService = new PlayerCustomizationService(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(playerCustomizationService, this), this);

        setupLuckPerms();

        val customizeCommand = new CustomizeCommand(plugin, getConfig(), playerCustomizationService);
        getCommand("customize").setExecutor(customizeCommand);
        getCommand("customize").setTabCompleter(customizeCommand);
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
