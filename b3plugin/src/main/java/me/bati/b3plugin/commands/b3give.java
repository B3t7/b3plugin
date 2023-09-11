package me.bati.b3plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class b3give implements CommandExecutor {
    ItemStack lgrod = new ItemStack(Material.BLAZE_ROD, 1);
    ItemStack grenade = new ItemStack(Material.EGG, 1);
    ItemStack mgmn = new ItemStack(Material.PRISMARINE_SHARD,1);


    public void AddMgmn(){
        ItemMeta mgmnmeta = mgmn.getItemMeta();
        mgmnmeta.setDisplayName(ChatColor.YELLOW + "Magic Mineral");
        mgmnmeta.setLore(Collections.singletonList(ChatColor.BLUE+"Used to craft special items."));
        mgmnmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
        mgmnmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        mgmn.setItemMeta(mgmnmeta);
    }
    public void AddLgrod() {
        ItemMeta lgrodmeta = lgrod.getItemMeta();
        assert lgrodmeta != null;
        lgrodmeta.setDisplayName(ChatColor.YELLOW + "LIGHTNING ROD");
        lgrodmeta.setLore(Collections.singletonList(ChatColor.RED + "Right click to strike lightning."));
        lgrodmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
        lgrodmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lgrod.setItemMeta(lgrodmeta);
    }

    public void AddGrnd() {
        ItemMeta grndmeta = grenade.getItemMeta();
        assert grndmeta != null;
        grndmeta.setDisplayName(ChatColor.GREEN + "GRENADE");
        grndmeta.setLore(Collections.singletonList(ChatColor.RED + "boom boom"));
        grndmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
        grndmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        grenade.setItemMeta(grndmeta);
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) throws ArrayIndexOutOfBoundsException {


        if (command.getName().equalsIgnoreCase("b3give")) {
            AddGrnd();
            AddLgrod();
            AddMgmn();
            if (commandSender instanceof Player) {
                Player p = ((Player) commandSender).getPlayer();



                if (strings.length == 0) {
                    assert p != null;
                    p.sendMessage("Specify an item and amount.");
                    p.sendMessage("/b3give <item> <amount>");
                    System.out.println("strlenght 0");

                } else if (strings.length == 1) {
                    String arg = strings[0];
                    if (arg.equalsIgnoreCase("lgrod")) {
                        AddLgrod();
                        assert p != null;
                        p.getInventory().addItem(lgrod);
                    }
                    else if (arg.equalsIgnoreCase("grenade")) {
                        AddGrnd();
                        assert p != null;
                        p.getInventory().addItem(grenade);
                    }
                    else if(arg.equalsIgnoreCase("MagicMineral")) {
                        AddMgmn();
                        assert p != null;
                        p.getInventory().addItem(mgmn);

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


                    }
                    else if (arg.equalsIgnoreCase("grenade")) {
                        AddGrnd();
                        grenade.setAmount(arg2);
                        assert p != null;
                        p.getInventory().addItem(grenade);

                    }
                    else if (arg.equalsIgnoreCase("MagicMineral")) {
                        AddMgmn();
                        mgmn.setAmount(arg2);
                        assert p != null;
                        p.getInventory().addItem(mgmn);

                    }


                }

            }


            }return true;



        }

    }

