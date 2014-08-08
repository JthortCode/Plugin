/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Skills;

import Main.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 *
 * @author User
 */
public class Range {
    
    Main plugin;
    
    public Range(Main plugin){
        this.plugin = plugin;
    }
    
    public void updateRangeStat(EntityDamageByEntityEvent event){
        
      if(event.getDamager() instanceof Projectile){
            Projectile arrow = (Projectile) event.getDamager();
            if(arrow.getShooter() instanceof Player){
                Player player = (Player) arrow.getShooter();
                Entity entity = event.getEntity();
                
                Double distance = player.getLocation().distance(entity.getLocation());
                if(distance <= 10){
                    plugin.getProfileHandler().getProfile(player.getUniqueId()).getStats().giveXp("Range", distance/10);
                    System.out.println(distance/10);
                }else{
                    plugin.getProfileHandler().getProfile(player.getUniqueId()).getStats().giveXp("Range", distance/20);
                    System.out.println(distance/20);
                }
            }
        }
    }
}
