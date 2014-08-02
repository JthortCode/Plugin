package Commands;

import Main.Main;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor{
    
    Main plugin;
    
    public MessageCommand(Main plugin){
        this.plugin = plugin;
    }
          //Sender, reciever
    HashMap<String, String>  playersInConversation = new HashMap();
    
      @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        
        if(command.equalsIgnoreCase("msg") || command.equalsIgnoreCase("message")){
         
             if(args.length == 0){     
                    plugin.getTools().getPrintFormatter().sendUserError(sender, "Usage: /message <Username> <Message>");
             }else if(args.length == 1){
             
                    plugin.getTools().getPrintFormatter().sendUserError(sender, "Usage: /message <Username> <Message>");
             }else if(args.length > 1){
              
                    try{
                       Player player =  Bukkit.getPlayer(args[0]);
                       String message = "";
                       for(int i = 1; i < args.length; i++){
                           message = message.concat(args[i] + " ");
                       }
                       if(sender instanceof Player){
                           message = plugin.getTools().getChatFilter().addSymbols(message);
                       }else{
                           Player playerSender = (Player) sender;
                           if(plugin.getProfileHandler().getProfile(playerSender.getUniqueId()).getPermissions().hasPermission("Me.Chat.Symbols")){
                               message = plugin.getTools().getChatFilter().addSymbols(message);
                           }
                       }
                       
                       if(!plugin.getTools().getChatFilter().isSentToGracePlayer(sender.getName(), args[0])){
                           
                       
                       if(a(player.getName())){
                           playersInConversation.remove(player.getName());
                       }
                       playersInConversation.put(sender.getName(), player.getName());
                       playersInConversation.put(player.getName(), sender.getName());
                       
                       
                      player.sendMessage(ChatColor.DARK_GRAY + "" + sender.getName() + " - " + ChatColor.GRAY + message);
                       sender.sendMessage(ChatColor.DARK_GRAY + "" + "You"+ ChatColor.GREEN +" -> " +ChatColor.DARK_GRAY+ player.getName() + " " +ChatColor.GRAY + message);
                       }else{
                           plugin.getTools().getPrintFormatter().sendUserError(sender, "That player is in grace period!");
                       }
                    }catch(Exception ex){
                        plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid player: " + args[0]);
                    }
             }
            
        }
        return true;
    }
   public HashMap<String, String> getList(){
       return playersInConversation;
   }
    public boolean a(String name){
        for (Map.Entry<String, String> entry : playersInConversation.entrySet()) {
            if(entry.getKey().equals(name)){
                return true;
            }
        }
        return false;
    }
    public String getOtherPlayer(String player){
        for (Map.Entry<String, String> entry : playersInConversation.entrySet()) {
            if(entry.getKey().equals(player)){
                return entry.getValue();
            }
        }
        return null;
    }
}
