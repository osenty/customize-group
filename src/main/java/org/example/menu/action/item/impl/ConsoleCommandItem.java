package org.example.menu.action.item.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.example.menu.action.item.MenuItem;

public class ConsoleCommandItem implements MenuItem {

    private final String command;

    public ConsoleCommandItem(String command) {
        this.command = command;
    }

    @Override
    public void handle(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String parsedCommand = command.replace("%player%", player.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsedCommand);
    }
}
