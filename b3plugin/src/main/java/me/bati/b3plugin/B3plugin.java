package me.bati.b3plugin;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public final class B3plugin extends JavaPlugin implements Listener {


    ItemStack lgrod = new ItemStack(Material.BLAZE_ROD,1);


    public void AddLgrod(){
        ItemMeta lgrodmeta = lgrod.getItemMeta();
        assert lgrodmeta != null;
        lgrodmeta.setDisplayName(ChatColor.YELLOW + "LIGHTNING ROD");
        lgrodmeta.setLore(Collections.singletonList(ChatColor.RED + "Right click to strike lightning."));
        lgrod.setItemMeta(lgrodmeta);
    }

    @Override
    public void onEnable() {
        System.out.println("++++ B3plugin has started ++++");
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        System.out.println("++++ B3plugin has stopped ++++");
    }



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        AddLgrod();
        Player p = event.getPlayer();
        String pstr = p.getDisplayName();
        Location joinloc = p.getLocation();
        GameMode pgmd = p.getGameMode();
        Inventory jinv = p.getInventory();

        Double xlocdb = joinloc.getX();
        Double ylocdb = joinloc.getY();
        Double zlocdb = joinloc.getY();

        int xloc = (int) Math.round(xlocdb);
        int yloc = (int) Math.round(ylocdb);
        int zloc = (int) Math.round(zlocdb);

        String strjoinloc = (ChatColor.GREEN + "[ "+ xloc+ " " + yloc +" "+ zloc + " ]");
        event.setJoinMessage(pstr + " joined at "+ strjoinloc + " welcome!");

        if (pstr.equals("B3t7") && pgmd == GameMode.CREATIVE ){
            jinv.addItem(lgrod);
        }




    }
}


