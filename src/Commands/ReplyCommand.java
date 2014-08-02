package Commands;

import Main.Main;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor{
        Main plugin;
    
    public ReplyCommand(Main plugin){
        this.plugin = plugin;
    }
          //Sender, reciever
    
      @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        if(command.equalsIgnoreCase("reply") || command.equalsIgnoreCase("r")){
               if(args.length == 0){
                    plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid arguments: /reply <Message>");
               }else if(args.length > 0){
            
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        String message = "";
                       for(int i = 0; i < args.length; i++){
                           message = message.concat(args[i] + " ");
                       }
                       if(plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().hasPermission("Me.Chat.Symbols")){
                            message = plugin.getTools().getChatFilter().addSymbols(message);
                        }
                        
                      if(plugin.getMessageCommand().a(player.getName())){
                          Player otherPlayer = Bukkit.getPlayer(plugin.getMessageCommand().getOtherPlayer(player.getName()));
                          if(!plugin.getTools().getChatFilter().isSentToGracePlayer(player.getName(), otherPlayer.getName())){
                          plugin.getMessageCommand().getList().remove(otherPlayer.getName());
                          plugin.getMessageCommand().getList().put(otherPlayer.getName(), player.getName());
                      
                            
                            otherPlayer.sendMessage(ChatColor.DARK_GRAY + "" + player.getName() + " - " + ChatColor.GRAY + message);
                            player.sendMessage(ChatColor.DARK_GRAY + "You" + ChatColor.GREEN+" -> "+ChatColor.DARK_GRAY+otherPlayer.getName() + " " + ChatColor.GRAY + message);
                          }else{
                              plugin.getTools().getPrintFormatter().sendPlayerError(player, "That player is in grace period!");
                          }
                      }else{
                          plugin.getTools().getPrintFormatter().sendPlayerError(player, "You have nobody to respond to.");
                      }
                    }else{
                        plugin.getTools().getPrintFormatter().sendConsoleError("This command's not supported in the console!");
                    }
                      
             }
        }
        return true;
    }
}
