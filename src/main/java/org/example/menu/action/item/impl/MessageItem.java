package org.example.menu.action.item.impl;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.example.menu.action.item.MenuItem;

public class MessageItem implements MenuItem {

    private final String message;

    public MessageItem(String message) {
        this.message = message;
    }

    @Override
    public void handle(InventoryClickEvent event) {
        event.getWhoClicked().sendMessage(message);
    }
}

