package org.example.customization.customizer.impl;

import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.customization.customizer.AbstractCustomizer;

public class EffectCustomizer extends AbstractCustomizer {
    public EffectCustomizer(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    public void customizePlayer(@NonNull Player player) {

    }
}
