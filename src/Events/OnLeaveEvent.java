package Events;

import Main.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveEvent implements Listener{
    
    Main plugin;
    
    public OnLeaveEvent(Main plugin){
        this.plugin = plugin;
    }
    
       @EventHandler
      public void onPlayerQuit(PlayerQuitEvent event){
          event.setQuitMessage(ChatColor.YELLOW + "[Jthort] "+ ChatColor.GRAY + generateRandomMessage(event.getPlayer().getName()));
      }
      private String generateRandomMessage(String player){
          List<String> listOfMessages = new ArrayList();
          for(String message : plugin.getConfig().getStringList("leavemessages")){
              listOfMessages.add(message);
          }
          Random random = new Random();
          int randInt = random.nextInt(listOfMessages.size());
          String randomMessage = listOfMessages.get(randInt);
           randomMessage = randomMessage.replace("$", player);
           return randomMessage;
      }
}
