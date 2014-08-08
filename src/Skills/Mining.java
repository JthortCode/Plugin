package Skills;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;

public class Mining{
    
    Main plugin;
    
    String[] rockBlocks = {"monster_eggs", "ore", "step", "double_step", "piston", "dispenser", "dropper", "furnace",
    "anvil", "wall", "stairs", "stone", "brick", "gravel", "obsidian", "iron_door", "iron_trapdoor", "iron_block",
    "gold_block", "redstone_block", "diamond_block", "emerald_block", "coal_block"};
    
    public Mining(Main plugin){
      this.plugin = plugin;       
    }
    
    public void updateMiningStats(BlockBreakEvent event){
        Bukkit.broadcastMessage(event.getBlock().getType().toString().toLowerCase());
        String block = event.getBlock().getType().toString();
        if(isRock(event.getBlock())){
           if(block.equalsIgnoreCase("redstone_ore")
                   || block.equalsIgnoreCase("lapis_ore")
                   || block.equalsIgnoreCase("coal_ore")
                   || block.equalsIgnoreCase("glowstone")
                   ){
               plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getStats().giveXp("Mining", 0.6);
           }else if(block.equalsIgnoreCase("iron_ore")){
               plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getStats().giveXp("Mining", 0.7);
           }else if(block.equalsIgnoreCase("gold_ore")){
               plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getStats().giveXp("Mining", 0.8);
           }else if(block.equalsIgnoreCase("diamond_ore")){
               plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getStats().giveXp("Mining", 1.0);
           }else if(block.equalsIgnoreCase("emerald_ore")){
               plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getStats().giveXp("Mining", 5.0);
           }else{
               plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getStats().giveXp("Mining", 0.5);
           }
            
        }
    }
    
      private boolean isRock(Block block){
        String type = block.getType().toString().toLowerCase();
        for(String rockBlockNames : rockBlocks){
            if(type.contains(rockBlockNames)){
                if(type.equalsIgnoreCase("redstone_wire")
                        || type.equalsIgnoreCase("redstone_torch_off")
                        || type.equalsIgnoreCase("redstone_torch_on")
                        || type.equalsIgnoreCase("spruce_wood_stairs")
                        || type.equalsIgnoreCase("birch_wood_stairs")
                        || type.equalsIgnoreCase("jungle_wood_stairs")
                        || type.equalsIgnoreCase("acacia_stairs")
                        || type.equalsIgnoreCase("dark_oak_stairs")
                        || type.equalsIgnoreCase("wood_stairs")
                ){
                    //this will be called when it's a handpicked nonstone item
                }else{
                    return true;
                }
            }
        }
        return false;
      }
    
}
