/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Events;

import Main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener{
    
    Main plugin;
    
    public BlockBreak(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
      public void onBlockBreak(BlockBreakEvent event){
          plugin.getSkillHandler().getMiningHandler().updateMiningStats(event);
          plugin.getSkillHandler().getWoodcuttingHandler().updateWoodcuttingStats(event);
      }
      
      
}
