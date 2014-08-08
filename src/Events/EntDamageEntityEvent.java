/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Events;

import Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 *
 * @author User
 */
public class EntDamageEntityEvent implements Listener{
    
    Main plugin;    
    
    public EntDamageEntityEvent(Main plugin){
        this.plugin = plugin;
    }
    
    @EventHandler
      public void onEntityDamageEntity(EntityDamageByEntityEvent event){
          
          if(event.getEntity() instanceof Player){
              Player playerDamaged = (Player) event.getEntity();
              if(event.getDamager() instanceof Player){
                  Player player = (Player) event.getDamager();
                  if(plugin.getOnJoinEvent().isInGraceChat(playerDamaged.getUniqueId())){
                     plugin.getTools().getPrintFormatter().sendPlayerError(player, "That player is in grace period!");
                     event.setCancelled(true); 
                  }
              }
          }
          plugin.getSkillHandler().getRangeHandler().updateRangeStat(event);
      }
}
