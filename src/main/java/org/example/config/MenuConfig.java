package org.example.config;

import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.Map;

public class MenuConfig {
    public Component title;
    public int size;
    public Map<String, MenuItemConfig> items;

    public static class MenuItemConfig {
        public Integer slot;
        public List<Integer> slots;
        public String material;
        public String name;
        public List<String> lore;
        public String action;
    }
}

