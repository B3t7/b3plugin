package me.bati.b3plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class b3give implements CommandExecutor {
    ItemStack lgrod = new ItemStack(Material.BLAZE_ROD, 1);
    ItemStack grenade = new ItemStack(Material.EGG, 1);

    public void AddLgrod() {
        ItemMeta lgrodmeta = lgrod.getItemMeta();
        assert lgrodmeta != null;
        lgrodmeta.setDisplayName(ChatColor.YELLOW + "LIGHTNING ROD");
        lgrodmeta.setLore(Collections.singletonList(ChatColor.RED + "Right click to strike lightning."));
        lgrod.setItemMeta(lgrodmeta);
    }

    public void AddGrnd() {
        ItemMeta grndmeta = grenade.getItemMeta();
        assert grndmeta != null;
        grndmeta.setDisplayName(ChatColor.GREEN + "GRENADE");
        grndmeta.setLore(Collections.singletonList(ChatColor.RED + "boom boom"));
        grenade.setItemMeta(grndmeta);
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {



        if (command.getName().equalsIgnoreCase("b3givegrenade")){
            AddGrnd();
            if (commandSender instanceof Player){
                Player p = ((Player) commandSender).getPlayer();
                    p.getInventory().addItem(grenade);



            }

        }else if(command.getName().equalsIgnoreCase("b3givelgrod")) {
            AddLgrod();
            if (commandSender instanceof Player) {
                Player p = ((Player) commandSender).getPlayer();
                p.getInventory().addItem(lgrod);


            }
        }

        return true;
    }
}