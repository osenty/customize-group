package org.example.menu.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Collections;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChooseMenuConfig {
    YamlConfiguration config;

    public ChooseMenuConfig(JavaPlugin plugin) {
        File file = new File(plugin.getDataFolder(), "/menus/choose.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {plugin.saveResource("/menus/choose.yml", false);}

    }

    public String getTitle() {
        return config.getString("menu.title");
    }

    public int getSize() {
        return config.getInt("menu.size");
    }

    public Set<String> getItems() {
        val set = config.getConfigurationSection("menu.items");
        return set == null ? Collections.emptySet() : set.getKeys(false);
    }

}
