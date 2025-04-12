package org.example.menu.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.example.menu.action.item.MenuItem;
import org.example.menu.loader.AbstractMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MenuManager implements Listener {

    private static final Map<UUID, AbstractMenu> activeMenus = new HashMap<>();
    private static final Map<UUID, Map<Integer, MenuItem>> menuItems = new HashMap<>();

    public static void register(AbstractMenu menu) {
        UUID uuid = menu.getPlayer().getUniqueId();
        activeMenus.put(uuid, menu);
        menuItems.put(uuid, new HashMap<>());
    }

    public static void registerItem(UUID uuid, int slot, MenuItem item) {
        menuItems.computeIfAbsent(uuid, k -> new HashMap<>()).put(slot, item);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        UUID uuid = player.getUniqueId();
        AbstractMenu menu = activeMenus.get(uuid);
        if (menu == null) return;

        event.setCancelled(true);

        Map<Integer, MenuItem> items = menuItems.get(uuid);
        if (items != null) {
            MenuItem item = items.get(event.getRawSlot());
            if (item != null) item.handle(event);
        }

        menu.handleClick(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        activeMenus.remove(uuid);
        menuItems.remove(uuid);
    }
}

