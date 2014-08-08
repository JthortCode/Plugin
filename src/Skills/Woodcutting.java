/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Skills;

import Main.Main;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author User
 */
public class Woodcutting {
    String[] woodenBlocks = {"log", "wood", "ladder", "bookshelf", "workbench", "chest", "sign", "door"};

    Main plugin;
    
    public Woodcutting(Main plugin){
        this.plugin = plugin;
    }
    
    public void updateWoodcuttingStats(BlockBreakEvent event){
        if(isWooden(event.getBlock())){
           plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getStats().giveXp("Woodcutting", 1.0);   
        }
    }
        private boolean isWooden(Block block){
          String type = block.getType().toString().toLowerCase();
          System.out.println(type);
          for(String woodBlockNames : woodenBlocks){
              if(type.contains(woodBlockNames)){
                  if(type.equalsIgnoreCase("ender_chest") || type.equalsIgnoreCase("iron_door")
                          || type.equalsIgnoreCase("iron_trapdoor")
                          ){
                      //This will be called when it's a handpicked nonwood item
                  }else{
                      return true;
                  }
                  
              }
          }
          return false;
      }
}
