package org.example.customization;

import lombok.NonNull;
import org.bukkit.entity.Player;
import org.example.customization.customizer.Customizer;

public interface CustomizationService {
    void customizePlayer(@NonNull Player player);
    Customizer getCustomizer(@NonNull String customizerName);
}
