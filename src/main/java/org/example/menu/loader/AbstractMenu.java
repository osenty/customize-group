package org.example.menu.loader;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public abstract class AbstractMenu {

    protected final String title;
    protected final Inventory inventory;
    protected Player player;
    protected JavaPlugin plugin;

    public AbstractMenu(JavaPlugin plugin, String title, Inventory inventory, Player player) {
        this.plugin = plugin;
        this.title = title;
        this.inventory = inventory;
        this.player = player;
    }

    // Абстрактный метод для клонирования меню для конкретного игрока
    public abstract AbstractMenu cloneFor(Player player);

    public void open() {
        Bukkit.getScheduler().runTask(plugin, () -> player.openInventory(inventory));
    }
    public abstract void handleClick(InventoryClickEvent event);
}



