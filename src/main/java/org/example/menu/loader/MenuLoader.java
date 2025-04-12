package org.example.menu.loader;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.menu.action.MenuActionRegistry;
import org.example.menu.loader.impl.YamlMenu;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuLoader {

    Map<String, AbstractMenu> loadedMenus = new HashMap<>();
    MenuActionRegistry actionRegistry;


    public void loadMenus(JavaPlugin plugin) {
        loadedMenus.clear();

        File menuFolder = new File(plugin.getDataFolder() + "/menus");

        if (!menuFolder.exists()) menuFolder.mkdirs();
        File[] files = menuFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files == null) return;

        for (File file : files) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

            String name = file.getName().replace(".yml", "");

            loadedMenus.put(name, new YamlMenu(plugin, name, config, actionRegistry));
        }
    }

    public AbstractMenu getMenu(String name) {
        return loadedMenus.get(name);
    }

    public Collection<AbstractMenu> getAllMenus() {
        return loadedMenus.values();
    }
}



