package org.example.builder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.example.menu.loader.AbstractMenu;
import org.example.menu.action.item.MenuItem;
import org.example.menu.manager.MenuManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuBuilder {

    Inventory inventory;
    @Getter
    Map<Integer, MenuItem> items = new HashMap<>();

    public MenuBuilder(int size, String title) {
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public MenuBuilder addItem(int slot, ItemStack item, MenuItem menuItem) {
        inventory.setItem(slot, item);
        items.put(slot, menuItem);
        return this;
    }

    public Inventory build() {
        return inventory;
    }

}



