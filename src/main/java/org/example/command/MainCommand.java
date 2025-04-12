package org.example.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.menu.loader.AbstractMenu;
import org.example.menu.loader.MenuLoader;

import java.io.File;

public class MainCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public MainCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/customize reload");
            return false;
        }
        if (!args[0].equalsIgnoreCase("reload")) return false;


        File menusFolder = new File(plugin.getDataFolder(), "menus/");
        if (!menusFolder.exists() || !menusFolder.isDirectory()) {
            sender.sendMessage("§cПапка menus не найдена.");
            return true;
        }

        File[] menuFiles = menusFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (menuFiles == null || menuFiles.length == 0) {
            sender.sendMessage("§cМеню не найдены.");
            return true;
        }

        int reloadedCount = 0;

//        for (Player player : Bukkit.getOnlinePlayers()) {
//            Inventory topInventory = player.getOpenInventory().getTopInventory();
//            if (topInventory.getHolder() instanceof AbstractMenu abstractMenu) {
//                String menuId = abstractMenu.getMenuId();
//
//                for (File menuFile : menuFiles) {
//                    FileConfiguration config = YamlConfiguration.loadConfiguration(menuFile);
//                    String fileName = menuFile.getName().replace(".yml", "");
//                    String fileId = config.getString("id", fileName);
//
//                    if (menuId.equalsIgnoreCase(fileId)) {
//                        player.closeInventory();
//                        MenuLoader.loadAndOpen(plugin, player, fileName);
//                        reloadedCount++;
//                        break;
//                    }
//                }
//            }
//        }

        sender.sendMessage("§aВсе YAML-меню обновлены. Перезагружено у " + reloadedCount + " игроков.");
        return true;
    }
}
