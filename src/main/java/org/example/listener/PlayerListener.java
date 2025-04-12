package org.example.listener;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.customization.CustomizationService;
import org.example.menu.loader.MenuLoader;

import java.io.File;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerListener implements Listener {

    CustomizationService customizationService;
    JavaPlugin plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        val player = event.getPlayer();
        customizationService.customizePlayer(player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        val player = event.getPlayer();



        customizationService.getCustomizer("prefixes").addCustomization(player, "ПОШЕЛ НАХУЙ");
    }


}
