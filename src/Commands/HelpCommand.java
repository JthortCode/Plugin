package Commands;

import Main.Main;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HelpCommand implements CommandExecutor{
    //hey Mansd fknasiodfn oaskldf asdnkllk
    Main plugin;
    
    public HelpCommand(Main plugin){
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        if(command.equalsIgnoreCase("help")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                for(Plugin plug : Bukkit.getPluginManager().getPlugins()){
                     List<Command> cmdList = PluginCommandYamlParser.parse(plug);
                     String group = plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().getGroup();
                      player.sendMessage(ChatColor.RED + "--------["+ group + " " + player.getName() +"]---------");
                        for(int i=0; i<= cmdList.size() -1; i++){
                           player.sendMessage(ChatColor.RED + "/" + cmdList.get(i).getLabel() + ChatColor.GRAY + " : " + ChatColor.GREEN + cmdList.get(i).getDescription());
                        }
                }
            }else{
                
            }
        }
        return true;
    }
}
