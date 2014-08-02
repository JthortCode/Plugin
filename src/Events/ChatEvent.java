
package Events;

import Tools.ChatFilter;
import Main.Main;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatEvent implements Listener{
    
    Main plugin;
    
    public ChatEvent(Main plugin, ChatFilter filter){
        this.plugin = plugin;
    }
    
    
    

    @EventHandler
      public void onChat(PlayerChatEvent event){
          String originalMessage = event.getMessage();
          String temp = event.getMessage();
          UUID player = event.getPlayer().getUniqueId();
          
          if(!plugin.getProfileHandler().getProfile(player).getPermissions().hasPermission("Me.Chat.NoFilter")){
              temp = plugin.getTools().getChatFilter().filterProfanity(event.getMessage());
          }
          
          plugin.getTools().getChatFilter().preventMessageToNewPlayers(event);
          
          plugin.getTools().getChatFilter().addRankTag(temp, event, originalMessage);
          
         
      }
}
