package org.example.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.menu.loader.AbstractMenu;
import org.example.menu.loader.MenuLoader;

public class OpenMenuCommand implements CommandExecutor {

    private final MenuLoader menuLoader;

    public OpenMenuCommand(MenuLoader menuLoader) {
        this.menuLoader = menuLoader;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Укажите имя меню!");
                return false;
            }

            String menuName = args[0];
            AbstractMenu menu = menuLoader.getMenu(menuName);

            if (menu != null) {
                // Клонируем меню для этого игрока и открываем его
                AbstractMenu clonedMenu = menu.cloneFor(player);
                clonedMenu.open();
                player.sendMessage(ChatColor.GREEN + "Меню открыто!");
            } else {
                player.sendMessage(ChatColor.RED + "Меню не найдено!");
            }
            return true;
        } else {
            sender.sendMessage("Эту команду может использовать только игрок!");
            return false;
        }
    }
}


