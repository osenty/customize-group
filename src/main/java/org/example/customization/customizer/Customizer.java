package org.example.customization.customizer;

import lombok.NonNull;
import org.bukkit.entity.Player;

public interface Customizer {
    void addCustomization(@NonNull Player player, @NonNull String value);
    void removeCustomization(@NonNull Player player);
    void customizePlayer(@NonNull Player player);
}
