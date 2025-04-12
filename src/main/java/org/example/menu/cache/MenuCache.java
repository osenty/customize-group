package org.example.menu.cache;

import org.example.menu.loader.AbstractMenu;

import java.util.HashMap;
import java.util.Map;

public class MenuCache {

    private static final Map<String, AbstractMenu> cachedMenus = new HashMap<>();

    public static AbstractMenu getMenu(String menuId) {
        return cachedMenus.get(menuId);
    }

    public static void putMenu(String menuId, AbstractMenu menu) {
        cachedMenus.put(menuId, menu);
    }

    public static void clearMenu(String menuId) {
        cachedMenus.remove(menuId);
    }

    public static void clearAllMenus() {
        cachedMenus.clear();
    }
}
