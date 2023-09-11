package me.bati.b3plugin;

import me.bati.b3plugin.commands.b3give;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public final class B3plugin extends JavaPlugin implements Listener {

    Random rand = new Random();

    ItemStack lgrod = new ItemStack(Material.BLAZE_ROD, 1);
    ItemStack grenade = new ItemStack(Material.EGG, 1);
    ItemStack mgmn = new ItemStack(Material.PRISMARINE_SHARD,1);

    public void AddLgrod() {
        ItemMeta lgrodmeta = lgrod.getItemMeta();
        lgrodmeta.setDisplayName(ChatColor.YELLOW + "LIGHTNING ROD");
        lgrodmeta.setLore(Collections.singletonList(ChatColor.RED + "Right click to strike lightning."));
        lgrodmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
        lgrodmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        lgrod.setItemMeta(lgrodmeta);
    }

    public void AddGrnd() {
        ItemMeta grndmeta = grenade.getItemMeta();
        grndmeta.setDisplayName(ChatColor.GREEN + "GRENADE");
        grndmeta.setLore(Collections.singletonList(ChatColor.RED + "boom boom"));
        grndmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
        grndmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        grenade.setItemMeta(grndmeta);

    }

    public void AddMgmn(){
        ItemMeta mgmnmeta = mgmn.getItemMeta();
        mgmnmeta.setDisplayName(ChatColor.YELLOW + "Magic Mineral");
        mgmnmeta.setLore(Collections.singletonList(ChatColor.BLUE+"Used to craft special items."));
        mgmnmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
        mgmnmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        mgmn.setItemMeta(mgmnmeta);
    }

    @Override
    public void onEnable() {
        System.out.println("++++ B3plugin has started ++++");
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("b3give").setExecutor(new b3give());

    }

    @Override
    public void onDisable() {
        System.out.println("++++ B3plugin has stopped ++++");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        AddGrnd();
        AddLgrod();
        Player p = event.getPlayer();
        String pstr = p.getDisplayName();
        Location joinloc = p.getLocation();
        GameMode pgmd = p.getGameMode();
        Inventory jinv = p.getInventory();
        boolean isop = event.getPlayer().isOp();

        double xlocdb = joinloc.getX();
        double ylocdb = joinloc.getY();
        double zlocdb = joinloc.getY();

        int xloc = (int) Math.round(xlocdb);
        int yloc = (int) Math.round(ylocdb);
        int zloc = (int) Math.round(zlocdb);

        String strjoinloc = (ChatColor.GREEN + "[ " + xloc + " " + yloc + " " + zloc + " ]");
        event.setJoinMessage(pstr + " joined at " + strjoinloc + " welcome!");

        if (isop && pgmd == GameMode.CREATIVE) {
            jinv.addItem(lgrod);
            jinv.addItem(grenade);
        }
    }

    @EventHandler
    public void onPlayerLgrodRC(PlayerInteractEvent event) {
        AddLgrod();
        Location rcpos = event.getPlayer().getTargetBlock(null, 512).getLocation();

        ItemStack item = event.getItem(); // Get the item in hand
        if (item != null) { // Check if the item is not null
            ItemMeta itmm = item.getItemMeta();

            if (event.getAction() == Action.RIGHT_CLICK_AIR && itmm != null && itmm.equals(lgrod.getItemMeta())) {
                event.getPlayer().getWorld().strikeLightning(rcpos);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Egg) {
            Egg egg = (Egg) event.getEntity();
            ItemStack eggItemStack = egg.getItem();

            // Check if the egg's custom name and lore match
            if (hasDesiredNameAndLore(eggItemStack, ChatColor.GREEN + "GRENADE", ChatColor.RED + "boom boom")) {
                egg.getWorld().spawnEntity(egg.getLocation(), EntityType.PRIMED_TNT);
            }
        }
    }

    // Helper method to check if an ItemStack has a specific display name and lore
    private boolean hasDesiredNameAndLore(ItemStack itemStack, String displayName, String lore) {
        if (itemStack != null) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                String itemDisplayName = itemMeta.getDisplayName();
                List<String> itemLore = itemMeta.getLore();
                return itemDisplayName.equals(displayName) && itemLore != null && itemLore.contains(lore);
            }
        }
        return false;
    }


    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event){
        Player p = event.getPlayer();
        Block broken_block = event.getBlock();
        Material brblmt = broken_block.getType();
        if (brblmt == Material.STONE){
            AddMgmn();
            int randint = rand.nextInt(21);
            if (randint == 1){
                Location blockloc = event.getBlock().getLocation();
                 World brblwrld = broken_block.getWorld();
                 brblwrld.dropItemNaturally(blockloc,mgmn);

            }

        }



    }
}




