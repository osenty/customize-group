package org.example.menu.loader.impl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.menu.action.MenuActionRegistry;
import org.example.menu.action.item.MenuItem;
import org.example.menu.loader.AbstractMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlMenu extends AbstractMenu {

    private final Map<Integer, MenuItem> menuItems;

    public YamlMenu(JavaPlugin plugin, String title, YamlConfiguration config, MenuActionRegistry actionRegistry) {
        super(plugin, title, Bukkit.createInventory(null, 27, title), null);
        this.menuItems = new HashMap<>();
        loadMenu(config, actionRegistry);
    }

    private void loadMenu(YamlConfiguration config, MenuActionRegistry actionRegistry) {
        // Загружаем элементы меню из конфигурации

        for (String key : config.getConfigurationSection("items").getKeys(false)) {
            int slot = Integer.parseInt(key);
            String material = config.getString("items." + key + ".material");
            String name = config.getString("items." + key + ".name");
            List<String> actions = config.getStringList("items." + key + ".action");

            ItemStack item = new ItemStack(Material.getMaterial(material.toUpperCase()));
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                item.setItemMeta(meta);
            }

            // Регистрируем действие
            MenuItem menuItem = actionRegistry.parseAll(actions);
            menuItems.put(slot, menuItem);
            getInventory().setItem(slot, item);
        }
    }

    @Override
    public AbstractMenu cloneFor(Player player) {
        // Клонируем меню для конкретного игрока, создаем новый инвентарь
        YamlMenu clonedMenu = new YamlMenu(plugin, title, null, null);
        clonedMenu.menuItems.putAll(menuItems);

        // Сохраняем игрока в новом меню
        clonedMenu.player = player;
        return clonedMenu;
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        // Отмена стандартного поведения
        event.setCancelled(true);

        // Получаем нажатый слот
        int slot = event.getRawSlot();

        // Проверяем, есть ли элемент в этом слоте
        if (menuItems.containsKey(slot)) {
            MenuItem menuItem = menuItems.get(slot);
            menuItem.handle(event);
        }
    }
}

