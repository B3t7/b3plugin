package me.bati.b3plugin;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.util.Collections;
import java.util.Objects;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;

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
        Boolean isop = event.getPlayer().isOp();

        Double xlocdb = joinloc.getX();
        Double ylocdb = joinloc.getY();
        Double zlocdb = joinloc.getY();

        int xloc = (int) Math.round(xlocdb);
        int yloc = (int) Math.round(ylocdb);
        int zloc = (int) Math.round(zlocdb);

        String strjoinloc = (ChatColor.GREEN + "[ "+ xloc+ " " + yloc +" "+ zloc + " ]");
        event.setJoinMessage(pstr + " joined at "+ strjoinloc + " welcome!");

        if (isop && pgmd == GameMode.CREATIVE ){
            jinv.addItem(lgrod);
        }




    }
    @EventHandler
    public void onPlayerLgrodRC(PlayerInteractEvent event){
        AddLgrod();
        Location rcpos = event.getPlayer().getTargetBlock(null,512).getLocation();
        ItemMeta itmm = Objects.requireNonNull(event.getItem()).getItemMeta();

        event.getAction();
        if(event.getAction() == Action.RIGHT_CLICK_AIR){
            if(itmm != null && itmm.equals(lgrod.getItemMeta())){
                event.getPlayer().getWorld().strikeLightning(rcpos);
            }


        }

    }
}


