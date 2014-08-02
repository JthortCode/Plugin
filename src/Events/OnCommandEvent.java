package Events;

import Main.Main;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class OnCommandEvent implements Listener{
    
    Main plugin;
    
    public OnCommandEvent(Main plugin){
      this.plugin = plugin;    
    }
    
    @EventHandler
    public void PlayerCommand(PlayerCommandPreprocessEvent event) {
        if(plugin.getOnJoinEvent().getGracePlayers().contains(event.getPlayer().getUniqueId())){
            event.setCancelled(true);
        }else{
            if(!isValidCommand(event.getMessage().split(" ")[0], event.getPlayer())){
                if(!plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().hasPermission("Me.Command.AllowAdminCommands")){
                   plugin.getTools().getPrintFormatter().sendPlayerError(event.getPlayer(), "You don't have permission to perform this command!!");
                   event.setCancelled(true);
                }
            }
         }
    }
    public boolean isValidCommand(String command, Player player){
        for(Plugin plug : Bukkit.getPluginManager().getPlugins()){
                     List<Command> cmdList = PluginCommandYamlParser.parse(plug);
                        for(int i=0; i<= cmdList.size() -1; i++){
                            if(command.equals("/" + cmdList.get(i).getLabel())){
                               return true;
                            }else{
                                int counter = 0;
                                for(String commandLabel: cmdList.get(i).getAliases()){
                                    if(command.equals("/" + commandLabel)){
                                        return true;
                                    }
                                    counter++;
                                }
                            }
                           //player.sendMessage(ChatColor.RED + "/" + cmdList.get(i).getLabel() + ChatColor.GRAY + " : " + ChatColor.GREEN + cmdList.get(i).getDescription());
                        }
                }
        return false;
    }
}
