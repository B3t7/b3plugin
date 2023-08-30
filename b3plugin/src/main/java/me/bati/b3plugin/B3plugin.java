package me.bati.b3plugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class B3plugin extends JavaPlugin implements Listener {

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
        Player p = event.getPlayer();
        Long plastplayed = p.getLastPlayed();
        Location joinloc = p.getLocation();

        Double xlocdb = joinloc.getX();
        Double ylocdb = joinloc.getY();
        Double zlocdb = joinloc.getY();

        int xloc = (int) Math.round(xlocdb);
        int yloc = (int) Math.round(ylocdb);
        int zloc = (int) Math.round(zlocdb);

        String strjoinloc = ("[ "+ xloc+ " " + yloc +" "+ zloc + " ]");

        event.setJoinMessage(p + " joined at "+ strjoinloc);

        p.sendMessage("Last online : "+ plastplayed);


    }
}


