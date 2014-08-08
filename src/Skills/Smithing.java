/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Skills;

import Main.Main;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author User
 */
public class Smithing {
    
    Main plugin;
    
    public Smithing(Main plugin){
        this.plugin = plugin;
    }
    
    public void updateSmithingSkill(FurnaceSmeltEvent event){
        ItemStack resultItem = event.getResult();
           System.out.println(event.getBlock().getType().toString());
        if(resultItem.getType().toString().equalsIgnoreCase("iron_ingot")){
         //   plugin.getProfileHandler().getProfile(event..getUniqueId()).getStats().giveXp("Mining", 0.7);
        }else if(resultItem.getType().toString().equalsIgnoreCase("gold_ingot")){
            
        }else if(resultItem.getType().toString().equalsIgnoreCase("nether_brick")){
            
        }else if(resultItem.getType().toString().equalsIgnoreCase("hardened_clay")){
            
        }else if(resultItem.getType().toString().equalsIgnoreCase("cracked_stone_bricks")){
            
        }else if(resultItem.getType().toString().equalsIgnoreCase("diamond")){
            
        }else{
            
        }
    }
}
