package Events;

import Main.Main;
import Profile.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class OnJoinEvent implements Listener{
    
    Main plugin;
    
    public OnJoinEvent(Main plugin){
        this.plugin = plugin;
    }
    
    List<UUID> gracePlayers = new ArrayList();
    
    
     @EventHandler
      public void onPlayerJoin(PlayerJoinEvent event){
          if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()) == null){
              plugin.getProfileHandler().getProfiles().add(new Profile(plugin, event.getPlayer().getUniqueId()));
              event.setJoinMessage("");
              for(Player player: Bukkit.getOnlinePlayers()){
                  if(!event.getPlayer().getName().equals(player.getName())){
                      plugin.getTools().getPrintFormatter().sendPlayerNotification(player, event.getPlayer().getName() + " has joined for the first time!");
                  }
              }
              plugin.getRPGHandler().getRandomTeleporter().sendToRandomLocation(event.getPlayer());
              plugin.getTools().getPrintFormatter().sendPlayerDazedMessage(event.getPlayer());
              addGrace(event.getPlayer());
          }else{
              event.setJoinMessage("");
              String randJoinMessage = getRandomJoinMessage(event.getPlayer().getName());
              for(Player player: Bukkit.getOnlinePlayers()){
                  if(!event.getPlayer().getName().equals(player.getName())){
                      player.sendMessage(ChatColor.YELLOW + "[Jthort] " + ChatColor.GRAY +randJoinMessage);
                  }
              }
              plugin.getTools().getPrintFormatter().sendPlayerNotification(event.getPlayer(), "Welcome back!");
          }
      }
      public boolean isInGraceChat(UUID player){
          for(UUID players: gracePlayers){
              if(players.toString().equals(player.toString())){
                  return true;
              }
          }
          return false;
      }
      private void addGrace(final Player player){
        gracePlayers.add(player.getUniqueId());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
    		gracePlayers.remove(player.getUniqueId());
                plugin.getTools().getPrintFormatter().sendPlayerNotification(player, "You are no longer in grace period, have fun!");
            }
    	}, 200);
    }
      public List<UUID> getGracePlayers(){
          return gracePlayers;
      }
      private String getRandomJoinMessage(String player){
          List<String> listOfMessages = new ArrayList();
          for(String message : plugin.getConfig().getStringList("joinmessages")){
              listOfMessages.add(message);
          }
          Random random = new Random();
          int randInt = random.nextInt(listOfMessages.size());
          String randomMessage = listOfMessages.get(randInt);
           randomMessage = randomMessage.replace("$", player);
           return randomMessage;
      }
}
