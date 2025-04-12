package org.example.menu.action.item.impl;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.example.menu.action.item.MenuItem;

public class CloseMenuItem implements MenuItem {
    @Override
    public void handle(InventoryClickEvent event) {
        event.getWhoClicked().closeInventory();
    }
}

