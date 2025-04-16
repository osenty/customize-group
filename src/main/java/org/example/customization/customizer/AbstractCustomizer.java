package org.example.customization.customizer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.customization.ResetCustomization;
import org.example.customization.customizer.dao.CustomizerDao;
import org.example.customization.customizer.dao.impl.BaseCustomizerDao;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class AbstractCustomizer implements Customizer {
    JavaPlugin plugin;
    CustomizerDao<String> customizerDao;

    public AbstractCustomizer(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.customizerDao = new BaseCustomizerDao(plugin, fileName);

    }

    @Override
    public void addCustomization(@NonNull Player player, @NonNull String customizerName) {
        customizerDao.saveCustomization(player.getUniqueId(), customizerName);
        customizePlayer(player);
    }

    @Override
    public void removeCustomization(@NonNull Player player) {
        customizerDao.deleteCustomization(player.getUniqueId());
        new ResetCustomization(player, customizerDao).resetAllCustomizations();
    }
}
