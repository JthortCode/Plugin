
package ChatPrefix;

import Main.Main;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatFilter {
    
    Main plugin;
    String noTag = "[None]";
    String userTag = "[Default] ";
    String modTag = "[Mod] ";
    String adminTag = "[Admin] ";
    String ownerTag = "[Owner] ";
    String trustedTag = "[Trusted] ";
    String donatorTag = "[Donator] ";
    String donatorPlusTag = "[Donator+] ";
    String donatorKingTag = "[♚Donator♚] ";
    
    public ChatFilter(Main plugin){
        this.plugin = plugin;
    }
    public String filterProfanity(String message){
        
        message = message.replaceAll("%", "‰");
        for(String word: message.split(" ")){
            int counter = 0;
            int dotCounter = 0;
            for(char letter: message.toCharArray()){
                if(letter == '.'){
                    dotCounter++;
                }
                if(Character.isUpperCase(letter)){
                    counter++;
                }
                if(!isAscii(letter)){
                    message = "";
                }
            }
            
            if(word.length() <= counter+(word.length()/4) && word.length() >2){
                 message = message.replace(word, word.toLowerCase());
                 word = word.toLowerCase();
             }
            
            if(word.contains("www") || word.contains(".com") || word.contains("dot") || word.contains(".tk")
                     ){
                 message = message.replace(word, "");
             }
            if(dotCounter >=3){
                 message = message.replaceAll(".", "");
             }
             
             
             
        }
        return message;
    
    }
    public void addRankTag(String temp, PlayerChatEvent event, String originalMessage){
        if(temp.equals("") || temp.equals(" ")){
            plugin.getTools().getPrintFormatter().sendConsoleNotification(
              "Removed " + event.getPlayer().getName() + "'s message: " + originalMessage
              );
            plugin.getTools().getPrintFormatter().sendPlayerError(
                      Bukkit.getPlayer(event.getPlayer().getUniqueId()),
                      "I removed your message.");
            event.setCancelled(true);
        }else{
            if(!temp.equals(event.getMessage())){
              plugin.getTools().getPrintFormatter().sendPlayerError(
                      Bukkit.getPlayer(event.getPlayer().getUniqueId()),
                      "I modified some of your content.");
              plugin.getTools().getPrintFormatter().sendConsoleNotification(
              "Modified " + event.getPlayer().getName() + "'s message: " + originalMessage
              );
          }
            if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().hasPermission("Me.Chat.Symbols")){
                temp = addSymbols(temp);
            }
            
        if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("Default")){
              event.setFormat(ChatColor.GRAY + userTag + event.getPlayer().getDisplayName()
                      + " " + ChatColor.RESET + temp);
          }else if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("Mod")){
              event.setFormat(ChatColor.GREEN + modTag + event.getPlayer().getDisplayName()
                      + " " + ChatColor.RESET + temp);
          }else if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("Admin")){
              event.setFormat(ChatColor.RED + adminTag + event.getPlayer().getDisplayName()
                      + " " + ChatColor.RESET + temp);
          }else if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("Owner")){
              event.setFormat(ChatColor.DARK_BLUE + ownerTag + event.getPlayer().getDisplayName()
                      + " " + ChatColor.RESET + temp);
          }else if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("Trusted")){
              event.setFormat(ChatColor.GRAY + "" + ChatColor.ITALIC + trustedTag + ChatColor.RESET +  event.getPlayer().getDisplayName()
                      + " " + temp);
          }else if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("Donator")){
              event.setFormat(ChatColor.GOLD + "" + ChatColor.ITALIC + donatorTag + ChatColor.RESET +  event.getPlayer().getDisplayName()
                      + " " + temp);
          }else if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("DonatorPlus")){
              event.setFormat(ChatColor.GOLD + "" + ChatColor.ITALIC + donatorPlusTag + ChatColor.RESET +  event.getPlayer().getDisplayName()
                      + " " + temp);
          }else if(plugin.getProfileHandler().getProfile(event.getPlayer().getUniqueId()).getPermissions().getGroup().equalsIgnoreCase("DonatorKing")){
              event.setFormat(ChatColor.GOLD + "" + ChatColor.ITALIC + donatorKingTag + ChatColor.RESET +  event.getPlayer().getDisplayName()
                      + " " + temp);
          }else{
              event.setFormat(ChatColor.GRAY + noTag + event.getPlayer().getDisplayName()
                      + " " + ChatColor.RESET + temp);
          }
        }
    }
    private ChatColor getRandomColor(){
        ChatColor[] color = {
            ChatColor.BLUE, ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.GOLD, ChatColor.LIGHT_PURPLE,
            ChatColor.BLACK, ChatColor.GRAY, ChatColor.DARK_RED
        };
        Random random = new Random();
        return color[random.nextInt(color.length)];
    }
    public String addSymbols(String message){
        message = replaceNew(message, "(:", getRandomColor() + "☻" + ChatColor.RESET);
        message = replaceNew(message, ":)", getRandomColor() + "☻" + ChatColor.RESET);
        message = replaceNew(message, ":(", getRandomColor() + "☹" + ChatColor.RESET);
        message = replaceNew(message, "):", getRandomColor() + "☹" + ChatColor.RESET);
        message = replaceNew(message, ":D", getRandomColor() + "☻" + ChatColor.RESET);
        message = replaceNew(message, ":o", getRandomColor() + "჻" + ChatColor.RESET);
        message = replaceNew(message, "o:", getRandomColor() + "჻" + ChatColor.RESET);
        message = replaceNew(message, ":O", getRandomColor() + "჻" + ChatColor.RESET);
        message = replaceNew(message, ":0", getRandomColor() + "჻" + ChatColor.RESET);
        message = replaceNew(message, "0:", getRandomColor() + "჻" + ChatColor.RESET);
        message = replaceNew(message, "O:", getRandomColor() + "჻" + ChatColor.RESET);
        message = replaceNew(message, "<3", getRandomColor() + "❤" + ChatColor.RESET);
        message = replaceNew(message, ":/", getRandomColor() + "ツ" + ChatColor.RESET);
        message = replaceNew(message, "/:", getRandomColor() + "ツ" + ChatColor.RESET);
        return message;
    }
    
    private boolean stillContains(String message,String lookingFor, String replacement){
        int index;
         
          index = message.indexOf(lookingFor);
        
               if(index ==-1){
                   return false;
               }else{
                   return true;
               }
    }
    private String replaceNew(String message,String lookingFor, String replacement){
        int index;
         while(stillContains(message, lookingFor, replacement)){
          index = message.indexOf(lookingFor);
        
               if(index==0){
                   message =  replacement + message.substring(2,message.length());
               }else if (index == message.length()-1){
                   message = message.substring(0,index)+replacement;
               }else{
                  message = message.substring(0,index)+replacement+message.substring(index+2,message.length());
               }
         }
               return message;
    }
   
    private boolean isAscii(char ch) {
    return ch < 128;
     }
    public void preventMessageToNewPlayers(PlayerChatEvent event){
        for(UUID player: plugin.getOnJoinEvent().getGracePlayers()){
            try{
            event.getRecipients().remove(Bukkit.getPlayer(player));
            }catch(Exception ex){
                //trown if the player logged off as the grace period was still active.
            }
        }
    }
}
