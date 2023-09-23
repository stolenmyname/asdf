package me.intier.asdfasdfasdf;

import me.intier.asdfasdfasdf.Commands.MenuCommands;
import me.intier.asdfasdfasdf.Commands.SuicideCommands;
import me.intier.asdfasdfasdf.Commands.WarpCommands;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public final class Asdfasdfasdf extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new MenuCommands(), this);

        TabExecutor suicideCommands = new SuicideCommands();

        getCommand("suicide").setExecutor(suicideCommands);
        getCommand("suicide").setTabCompleter(suicideCommands);

        TabExecutor warpCommands = new WarpCommands();

        getCommand("warp").setExecutor(warpCommands);
        getCommand("warp").setTabCompleter(warpCommands);

        TabExecutor menuCommands = new MenuCommands();

        getCommand("menu").setExecutor(menuCommands);
        getCommand("menu").setTabCompleter(menuCommands);


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.setJoinMessage(event.getPlayer().getName() + " §bJoined!");

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        event.setQuitMessage(event.getPlayer().getName() + " §bQuit.");
    }


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        p.sendMessage("§bRespawned!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
