package com.github.gameoholic.twitchrewardsplugin.Commands;

import com.github.gameoholic.twitchrewardsplugin.TwitchRewardsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetStreamerCommand implements CommandExecutor {
    private TwitchRewardsPlugin plugin;
    public SetStreamerCommand(TwitchRewardsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("twitchrewards.setstreamer")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
                return true;
            }
        }

        if (args[0] == null || args[0].equals("")) {
            sender.sendMessage(ChatColor.RED + "Invalid Streamer username. ");
            return true;
        }
        plugin.getConfig().set("StreamerUsername", args[0]);
        plugin.saveConfig();
        sender.sendMessage(ChatColor.AQUA + "Set Streamer username to " + ChatColor.YELLOW + args[0]);
        return true;
    }
}