package Events;

import Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntDamageEvent implements Listener{
    
    Main plugin;
    
    public EntDamageEvent(Main plugin){
       this.plugin = plugin;   
    }
    
    @EventHandler
      public void onEntityDamage(EntityDamageEvent event){
          if(event.getEntity() instanceof Player){
              Player player = (Player) event.getEntity();
              if(plugin.getOnJoinEvent().getGracePlayers().contains(player.getUniqueId())){
                 event.setCancelled(true);
              }
          }
      }
}
