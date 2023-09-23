package me.intier.asdfasdfasdf.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WarpCommands implements TabExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("warp")) {
            if (command instanceof Player p) {
                if (args.length != 1)  {
                    p.sendMessage(helpMessage);
                    return true;
                }
                switch (args[0].toLowerCase()) {
                    case "end" -> warpEnd(p);
                    case "nether" -> warpNether(p);
                    case "overworld" -> warpOverworld(p);
                }
            }
            return true;
        }
        return false;
    }

    String helpMessage = "§cWrong Commands! /warp [end/nether/overwold]";

    public static void warpEnd(Player p) {
        p.teleport(new Location(Bukkit.getWorlds().get(2), 0, Bukkit.getWorlds().get(2).getHighestBlockYAt(0, 0), 0));
    }

    public static void warpNether(Player p) {
        p.teleport(new Location(Bukkit.getWorlds().get(1), 0, Bukkit.getWorlds().get(1).getHighestBlockYAt(0, 0), 0));
    }

    public static void warpOverworld(Player p) {
        p.teleport(new Location(Bukkit.getWorlds().get(0), 0, Bukkit.getWorlds().get(2).getHighestBlockYAt(0, 0), 0));
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("warp")) {
            List<String> list = new ArrayList<>();
            list.add("end");
            list.add("nether");
            list.add("overworld");
            return list;
        }
        else {
            return new ArrayList<>();
        }
    }

}
