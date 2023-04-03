package com.github.gameoholic.twitchrewardsplugin.Rewards.RewardsActivators;

import com.github.gameoholic.twitchrewardsplugin.TwitchRewardsPlugin;
import com.github.gameoholic.twitchrewardsplugin.tasks.GodModeTask;
import com.github.gameoholic.twitchrewardsplugin.tasks.InventorySlotLockerTask;
import org.bukkit.entity.Player;

import java.util.Optional;

public class GodMode {
    public static void enableGodMode(TwitchRewardsPlugin plugin, Player player, int time) {
        player.setInvulnerable(true);

        //No duplicate tasks. If new, just change existing one's time
        Optional<GodModeTask> task = GodModeTask.godModeTasks.stream()
                .filter(t -> t.getPlayer() != null && t.getPlayer().getUniqueId().equals(player.getUniqueId()))
                .findFirst();

        if (task.isPresent())
            task.get().setTimeLeft((time + 1));
        else
            new GodModeTask(plugin, player, time);
    }
}
