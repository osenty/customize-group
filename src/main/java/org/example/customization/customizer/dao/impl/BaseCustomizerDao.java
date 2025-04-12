package org.example.customization.customizer.dao.impl;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.customization.customizer.dao.CustomizerDao;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BaseCustomizerDao implements CustomizerDao<String> {

    Map<UUID, String> cachedValues;
    File file;
    YamlConfiguration configuration;

    public BaseCustomizerDao(JavaPlugin plugin, String fileName) {
        this.cachedValues = new HashMap<>();
        file = new File(plugin.getDataFolder(), fileName);
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    @SneakyThrows
    public void saveCustomization(@NonNull UUID uuid, @NotNull String value) {
        val customization = getCustomization(uuid);
        if (customization != null) {
            if (customization.equals(value)) return;
        }

        configuration.set(uuid.toString(), value);
        configuration.save(file);
    }

    @Override
    public String getCustomization(@NonNull UUID uuid) {
        if (!cachedValues.containsKey(uuid)){
            return getCustomization0(uuid);
        }
        return cachedValues.get(uuid);
    }


    @Override
    public void deleteCustomization(@NonNull UUID uuid) {
        configuration.set(uuid.toString(), "");
    }

    private String getCustomization0(@NonNull UUID uuid) {
        return configuration.getString(uuid.toString());
    }
}
