package org.example.customization.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.customization.CustomizationService;
import org.example.customization.customizer.Customizer;
import org.example.customization.customizer.impl.EffectCustomizer;
import org.example.customization.customizer.impl.PrefixCustomizer;
import org.example.customization.customizer.impl.TitleCustomizer;

import java.util.Map;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PlayerCustomizationService implements CustomizationService {
    Map<String, Customizer> customizers;

    public PlayerCustomizationService(JavaPlugin plugin) {
        this.customizers = Map.of(
                "prefixes", new PrefixCustomizer(plugin, "/data/prefixes.yml"),
                "titles", new TitleCustomizer(plugin, "/data/titles.yml"),
                "effects", new EffectCustomizer(plugin, "/data/effects.yml")
        );
    }

    @Override
    public void customizePlayer(@NonNull Player player) {
        customizers.values().forEach(customizer -> customizer.customizePlayer(player));
    }

    public Customizer getCustomizer(@NonNull String customizerName) {
        return customizers.get(customizerName);
    }

    @Override
    public void removeCustomization(@NonNull Player player) {
        customizers.values().forEach(customizer -> customizer.removeCustomization(player));
    }
}
