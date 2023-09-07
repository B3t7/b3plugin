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
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) throws ArrayIndexOutOfBoundsException {


        if (command.getName().equalsIgnoreCase("b3give")) {
            AddGrnd();
            AddLgrod();
            if (commandSender instanceof Player) {
                Player p = ((Player) commandSender).getPlayer();



                if (strings.length == 0) {
                    assert p != null;
                    p.sendMessage("Specify an item.");
                    p.sendMessage("/b3give <item>");
                    System.out.println("strlenght 0");

                } else if (strings.length == 1) {
                    String arg = strings[0];
                    if (arg.equalsIgnoreCase("lgrod")) {
                        AddLgrod();
                        assert p != null;
                        p.getInventory().addItem(lgrod);
                    } else if (arg.equalsIgnoreCase("grenade")) {
                        AddGrnd();
                        assert p != null;
                        p.getInventory().addItem(grenade);
                    }
                } else if (strings.length == 2) {
                    int arg2 = Integer.parseInt(strings[1]);
                    System.out.println("arg2 is "+arg2);
                    String arg = strings[0];

                    if (arg.equalsIgnoreCase("lgrod")) {
                        AddLgrod();
                        lgrod.setAmount(arg2);
                        assert p != null;
                        p.getInventory().addItem(lgrod);


                    } else if (arg.equalsIgnoreCase("grenade")) {
                        AddGrnd();
                        grenade.setAmount(arg2);
                        assert p != null;
                        p.getInventory().addItem(grenade);

                    }


                }

            }


            }return true;



        }

    }

