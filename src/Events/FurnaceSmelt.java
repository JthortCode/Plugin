/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Events;

import Main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

/**
 *
 * @author User
 */
public class FurnaceSmelt implements Listener{
    
    Main plugin;
    
    public FurnaceSmelt(Main plugin){
        this.plugin = plugin;
    }
    
    @EventHandler
      public void onPlayerJoin(FurnaceSmeltEvent event){
          plugin.getSkillHandler().getSmithingHandler().updateSmithingSkill(event);
      }
}
