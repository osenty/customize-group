package org.example.customization.customizer.impl;

import lombok.NonNull;
import lombok.val;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.CustomizePlugin;
import org.example.customization.customizer.AbstractCustomizer;

public class TitleCustomizer extends AbstractCustomizer {
    public TitleCustomizer(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    public void customizePlayer(@NonNull Player player) {
        val customization = getCustomizerDao().getCustomization(player.getUniqueId());
        if (customization == null) return;

        val luckperms = CustomizePlugin.getLuckPerms();
        User user = luckperms.getPlayerAdapter(Player.class).getUser(player);

        val oldPrefix = user.getNodes(NodeType.SUFFIX).stream().filter(suffixNode -> suffixNode.getPriority() == 77).findAny();
        oldPrefix.ifPresent(suffixNode -> user.data().remove(suffixNode));

        SuffixNode node = SuffixNode.builder(customization + " §f", 77).build();

        user.data().add(node);

        luckperms.getUserManager().saveUser(user);


    }
}
