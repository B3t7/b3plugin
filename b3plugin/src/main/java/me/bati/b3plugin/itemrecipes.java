package me.bati.b3plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Collections;



public class itemrecipes {




    public static void register(){

        ItemStack lgrod = new ItemStack(Material.BLAZE_ROD, 1);
        ItemStack grenade = new ItemStack(Material.EGG, 1);
        ItemStack mgmn = new ItemStack(Material.PRISMARINE_SHARD,1);

            ItemMeta lgrodmeta = lgrod.getItemMeta();
            lgrodmeta.setDisplayName(ChatColor.YELLOW + "LIGHTNING ROD");
            lgrodmeta.setLore(Collections.singletonList(ChatColor.RED + "Right click to strike lightning."));
            lgrodmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
            lgrodmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            lgrod.setItemMeta(lgrodmeta);
            ItemMeta grndmeta = grenade.getItemMeta();
            grndmeta.setDisplayName(ChatColor.GREEN + "GRENADE");
            grndmeta.setLore(Collections.singletonList(ChatColor.RED + "boom boom"));
            grndmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
            grndmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            grenade.setItemMeta(grndmeta);
            ItemMeta mgmnmeta = mgmn.getItemMeta();
            mgmnmeta.setDisplayName(ChatColor.YELLOW + "Magic Mineral");
            mgmnmeta.setLore(Collections.singletonList(ChatColor.BLUE+"Used to craft special items."));
            mgmnmeta.addEnchant(Enchantment.SOUL_SPEED,1,true);
            mgmnmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            mgmn.setItemMeta(mgmnmeta);







        ShapedRecipe lgrodrecipe = new ShapedRecipe(NamespacedKey.minecraft("lgrod"),lgrod);

        lgrodrecipe.shape("GMG","MLM","GMG");
        lgrodrecipe.setIngredient('G',Material.GUNPOWDER);
        lgrodrecipe.setIngredient('M',new RecipeChoice.ExactChoice(mgmn));
        lgrodrecipe.setIngredient('L',Material.BLAZE_ROD);
        Bukkit.addRecipe(lgrodrecipe);

        ItemStack cbd = new ItemStack(Material.COBBLED_DEEPSLATE);
        ItemStack stn = new ItemStack(Material.COBBLESTONE);




        ShapelessRecipe dstocb = new ShapelessRecipe(stn);
        dstocb.addIngredient(1,Material.COBBLED_DEEPSLATE);
        Bukkit.addRecipe(dstocb);


        ShapelessRecipe cbtods = new ShapelessRecipe(cbd);
        cbtods.addIngredient(1,Material.COBBLESTONE);
        Bukkit.addRecipe(cbtods);



    }
}
