package me.intier.asdfasdfasdf.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static me.intier.asdfasdfasdf.Commands.SuicideCommands.suicide;
import static me.intier.asdfasdfasdf.Commands.WarpCommands.warpEnd;
import static me.intier.asdfasdfasdf.Commands.WarpCommands.warpNether;
import static me.intier.asdfasdfasdf.Commands.WarpCommands.warpOverworld;

public class MenuCommands implements TabExecutor, Listener {

    private static Inventory statsMenu = Bukkit.createInventory(null, 9*6, "Stats Menu");

    static {
        ItemStack suicideSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta suicideSwordMeta = suicideSword.getItemMeta();
        suicideSwordMeta.displayName(Component.text()
                        .append(Component.text("Suicide...")
                                .color(NamedTextColor.DARK_RED)
                                .decoration(TextDecoration.ITALIC, true)
                                .decoration(TextDecoration.BOLD, true)
                                .decoration(TextDecoration.STRIKETHROUGH, false)
                                .decoration(TextDecoration.UNDERLINED, false)
                                .decoration(TextDecoration.OBFUSCATED, false)).build());
        suicideSwordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        suicideSword.setItemMeta(suicideSwordMeta);
        statsMenu.setItem(9+9+9+9+7,suicideSword);
    }

    private static Inventory warpMenu = Bukkit.createInventory(null, 9*3, "Warp Menu");

    static {
        ItemStack warpMenuFrame = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta warpMenuFrameMeta = warpMenuFrame.getItemMeta();
        warpMenuFrameMeta.displayName(Component.text()
                .append(Component.text("Warp End")
                                .color(NamedTextColor.DARK_PURPLE)
                                .decoration(TextDecoration.ITALIC, false)
                                .decoration(TextDecoration.BOLD, false)
                                .decoration(TextDecoration.STRIKETHROUGH, false)
                                .decoration(TextDecoration.UNDERLINED, false)
                                .decoration(TextDecoration.OBFUSCATED, false)).build());
        warpMenuFrameMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        warpMenuFrame.setItemMeta(warpMenuFrameMeta);
        warpMenu.setItem(9+2,warpMenuFrame);
    }

    static {
        ItemStack warpMenuNether = new ItemStack(Material.NETHERRACK);
        ItemMeta warpMenuNetherMeta = warpMenuNether.getItemMeta();
        warpMenuNetherMeta.displayName(Component.text()
                .append(Component.text("Warp Nether")
                                .color(NamedTextColor.RED)
                                .decoration(TextDecoration.ITALIC, false)
                                .decoration(TextDecoration.BOLD, false)
                                .decoration(TextDecoration.STRIKETHROUGH, false)
                                .decoration(TextDecoration.UNDERLINED, false)
                                .decoration(TextDecoration.OBFUSCATED, false)).build());
        warpMenuNetherMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        warpMenuNether.setItemMeta(warpMenuNetherMeta);
        warpMenu.setItem(9+4,warpMenuNether);
    }

    static {
        ItemStack warpMenuOverworld = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta warpMenuOverworldMeta = warpMenuOverworld.getItemMeta();
        warpMenuOverworldMeta.displayName(Component.text()
                .append(Component.text("Warp Overworld")
                                .color(NamedTextColor.GREEN)
                                .decoration(TextDecoration.ITALIC, true)
                                .decoration(TextDecoration.BOLD, false)
                                .decoration(TextDecoration.STRIKETHROUGH, false)
                                .decoration(TextDecoration.UNDERLINED, false)
                                .decoration(TextDecoration.OBFUSCATED, false)).build());
        warpMenuOverworldMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        warpMenuOverworld.setItemMeta(warpMenuOverworldMeta);
        warpMenu.setItem(9+6,warpMenuOverworld);
    }

    private static Inventory menu = Bukkit.createInventory(null, 9*6, "Menu");

    static {
        ItemStack warpMenu = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta warpMenuMeta = warpMenu.getItemMeta();
        warpMenuMeta.displayName(Component.text()
                .append(Component.text("Warp Menu")
                        .color(NamedTextColor.DARK_PURPLE)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.BOLD, false)
                        .decoration(TextDecoration.STRIKETHROUGH, false)
                        .decoration(TextDecoration.UNDERLINED, false)
                        .decoration(TextDecoration.OBFUSCATED, false)).build());
        warpMenuMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        warpMenu.setItemMeta(warpMenuMeta);
        menu.setItem(9 + 9 + 4, warpMenu);
    }

    static {
        ItemStack statsMenu = new ItemStack(Material.IRON_SWORD);
        ItemMeta statsMenuMeta = statsMenu.getItemMeta();
        statsMenuMeta.displayName(Component.text()
                .append(Component.text("Stats Menu")
                        .color(NamedTextColor.RED)
                        .decoration(TextDecoration.ITALIC, false)
                        .decoration(TextDecoration.BOLD, false)
                        .decoration(TextDecoration.STRIKETHROUGH, false)
                        .decoration(TextDecoration.UNDERLINED, false)
                        .decoration(TextDecoration.OBFUSCATED, false)).build());
        statsMenuMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        statsMenu.setItemMeta(statsMenuMeta);
        menu.setItem(9 + 9 + 9 + 4, statsMenu);
    }

    Player p;

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {

        //WarpMenu
        if (event.getWhoClicked().getOpenInventory().getTitle().equals("Menu")) {
            event.setCancelled(true);
            if (event.getSlot() == 9+9+4) event.getWhoClicked().openInventory(warpMenu);
        }
        else if (event.getWhoClicked().getOpenInventory().getTitle().equals("Warp Menu")) {
            event.setCancelled(true);
            if (event.getSlot() == 9 + 2) warpEnd(p);
        }

        else if (event.getWhoClicked().getOpenInventory().getTitle().equals("Warp Menu")) {
            event.setCancelled(true);
            if (event.getSlot() == 9 + 4) warpNether(p);
        }

        else if (event.getWhoClicked().getOpenInventory().getTitle().equals("Warp Menu")) {
            event.setCancelled(true);
            if (event.getSlot() == 9 + 6) warpOverworld(p);
        }

        //StatsMenu
        else if (event.getWhoClicked().getOpenInventory().getTitle().equals("Menu")) {
            event.setCancelled(true);
            if (event.getSlot() == 9+9+9+4) event.getWhoClicked().openInventory(statsMenu);
        }

        else if (event.getWhoClicked().getOpenInventory().getTitle().equals("Stats Menu")) {
            event.setCancelled(true);
            if (event.getSlot() == 9+9+9+9+7) suicide(p);
        }
    }

    String helpMessage = "§cWrong Command! /menu [warp/stats]";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("menu")) {
            if (args.length != 1) {
                if (command instanceof Player p) {
                    p.sendMessage(helpMessage);
                    return true;
                }
                switch (args[0].toLowerCase()) {
                    case "warp" -> p.openInventory(warpMenu);
                    case "stats" -> p.openInventory(statsMenu);
                }
            }
            return true;
        }
        return false;
    }



    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("menu")) {
            List<String> list = new ArrayList<>();
            list.add("warp");
            list.add("stats");
            return list;
        }
        else {
            return new ArrayList<>();
        }
    }
}
