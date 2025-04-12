package org.example.customization.customizer.impl;

import lombok.NonNull;
import lombok.val;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.PrefixNode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.CustomizePlugin;
import org.example.customization.customizer.AbstractCustomizer;


public class PrefixCustomizer extends AbstractCustomizer {

    public PrefixCustomizer(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    public void customizePlayer(@NonNull Player player) {
        val customization = getCustomizerDao().getCustomization(player.getUniqueId());
        if (customization == null) return;

        player.sendMessage(customization);
        val luckperms = CustomizePlugin.getLuckPerms();
        User user = luckperms.getPlayerAdapter(Player.class).getUser(player);

        PrefixNode node = PrefixNode.builder(customization, 77).build();
        user.data().add(node);

        luckperms.getUserManager().saveUser(user);

    }

}
