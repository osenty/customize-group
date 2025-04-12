package org.example.menu.action.item.impl;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.example.menu.action.item.MenuItem;

import java.util.List;

public class CompositeItem implements MenuItem {

    private final List<MenuItem> actions;

    public CompositeItem(List<MenuItem> actions) {
        this.actions = actions;
    }

    @Override
    public void handle(InventoryClickEvent event) {
        for (MenuItem item : actions) {
            item.handle(event);
        }
    }
}

