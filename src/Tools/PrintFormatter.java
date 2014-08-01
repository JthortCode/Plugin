/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tools;
import Main.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PrintFormatter {
    
   private final ConsoleCommandSender sender;
    private final String pluginTag = "[jthort] ";
    
    public PrintFormatter(Main main){
        sender = main.getServer().getConsoleSender();
    }
    
    public void sendConsoleNotification(String notification){
       sender.sendMessage(ChatColor.YELLOW + pluginTag + notification);
    }
    public void sendConsoleError(String error){
        sender.sendMessage(ChatColor.RED + pluginTag + error);
    }
    public void sendConsoleMessage(String message){
        sender.sendMessage(pluginTag + message);
    }
    public void sendPlayerNotification(Player player, String message){
        player.sendMessage(ChatColor.YELLOW + pluginTag + message);
    }
    public void sendPlayerError(Player player, String message){
        player.sendMessage(ChatColor.RED + pluginTag + message);
    }
    public void sendPlayerMessage(Player player, String message){
        player.sendMessage(ChatColor.WHITE + pluginTag + message);
    }
    public void sendPlayerAccomplishmentMessage(Player player, String message){
        player.sendMessage(ChatColor.GREEN + pluginTag + message);
    }
    public void sendUserError(CommandSender sender, String message){
        sender.sendMessage(ChatColor.RED + pluginTag + message);
    }
    public void sendUserAccomplishmentMessage(CommandSender sender, String message){
        sender.sendMessage(ChatColor.GREEN + pluginTag + message);
    }
    public void sendUserNotificationMessage(CommandSender sender, String message){
        sender.sendMessage(ChatColor.YELLOW + pluginTag + message);
    }
    public void sendPlayerDazedMessage(Player player){
        player.sendMessage(ChatColor.RED + "{============================================}");
        player.sendMessage(ChatColor.ITALIC + "You wake up dazed in the middle of the wild");
        player.sendMessage("");
        player.sendMessage(ChatColor.ITALIC + "You begin to understand the severity of the situation.");
        player.sendMessage("");
        player.sendMessage(ChatColor.ITALIC + "You take a deep breath...And begin your new life. Good luck, you're going to need it...");
        player.sendMessage(ChatColor.RED + "{============================================}");
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 250, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 250, 4));
    }
}
