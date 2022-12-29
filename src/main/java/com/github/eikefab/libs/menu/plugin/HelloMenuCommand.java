package com.github.eikefab.libs.menu.plugin;

import com.google.common.collect.ImmutableMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelloMenuCommand implements CommandExecutor {

    private int times = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        final Player player = (Player) sender;

        if (args.length == 0 || !args[0].equalsIgnoreCase("page")) {
            final HelloMenu menu = new HelloMenu();

            menu.open(player, ImmutableMap.of("times", ++times));
        } else {
            final HelloPageMenu menu = new HelloPageMenu();

            menu.open(player);
        }


        return false;
    }

}
