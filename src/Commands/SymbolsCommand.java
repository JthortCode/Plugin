package Commands;

import Main.Main;
import Profile.Permissions;
import Profile.Profile;
import Profile.ProfileHandler;
import Tools.PrintFormatter;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SymbolsCommand implements CommandExecutor{
    
    Main plugin;
    PrintFormatter printFormatter;
    ProfileHandler profileHandler;
    
    public SymbolsCommand(Main plugin){
        this.plugin = plugin;
        printFormatter = plugin.getTools().getPrintFormatter();
        profileHandler = plugin.getProfileHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        if(command.equalsIgnoreCase("symbols") || (command.equalsIgnoreCase("sym"))){
            if(args.length == 0){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    Profile profile = profileHandler.getProfile(player.getUniqueId());
                    if(profile.getPermissions().hasPermission("Me.Command.Symbols")){
                        if(profile.getPermissions().hasPermission("Me.Chat.Symbols")){
                            profile.getPermissions().removePermission("Me.Chat.Symbols");
                            printFormatter.sendPlayerError(
                                    player, "You no longer chatting using the symbols filter!");
                        }else{
                            profile.getPermissions().givePermission("Me.Chat.Symbols");
                            printFormatter.sendPlayerAccomplishmentMessage(
                                    player, "You are now chatting using the symbols filter!");
                        }
                        
                    }else{
                        printFormatter.sendPlayerError(player, "You don't have permission to perform this command!");
                    }
                }else{
                    printFormatter.sendConsoleError("You cannot give symbols to yourself!");
                }
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("help")){
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        Permissions permissions = profileHandler.getProfile(player.getUniqueId()).getPermissions();
                        if(permissions.hasPermission("Me.Command.SymbolsHelp")){
                            player.sendMessage(ChatColor.RED + "--------[ Symbols Help Menu ]---------");
                            if(permissions.hasPermission("Me.Command.Symbols")){
                            player.sendMessage(ChatColor.RED + "/Symbols - " + ChatColor.GREEN + "Toggle your symbols filter");
                            }
                            if(permissions.hasPermission("Me.Command.Symbols.Other")){
                            player.sendMessage(ChatColor.RED + "/Symbols <User> - " + ChatColor.GREEN + "Toggle symbols for another player. (Doesn't prevent the use of the command)");
                            }
                            player.sendMessage(ChatColor.RED + "/Symbols help - " + ChatColor.GREEN + "Symbols help menu");
                        }else{
                         printFormatter.sendPlayerError(player, "You don't have permission to perform this command!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "--------[ Symbols Help Menu (Console) ]---------");
                        sender.sendMessage(ChatColor.RED + "/Symbols - " + ChatColor.GREEN + "Toggle your symbols filter");
                        sender.sendMessage(ChatColor.RED + "/Symbols <User> - " + ChatColor.GREEN + "Toggle symbols for another player. (Doesn't prevent the use of the command)");
                        sender.sendMessage(ChatColor.RED + "/Symbols help - " + ChatColor.GREEN + "Symbols help menu");
                    }
                }else if(sender instanceof Player){
                    Player player = (Player) sender;
                    Profile profile = profileHandler.getProfile(player.getUniqueId());
                    if(profile.getPermissions().hasPermission("Me.Command.Symbols.Other")){
                        try{
                            UUID otherPlayer = Bukkit.getServer().getPlayer(args[0]).getUniqueId();
                            if(profileHandler.getProfile(otherPlayer).getPermissions().hasPermission("Me.Chat.Symbols")){
                                profileHandler.getProfile(otherPlayer).getPermissions().removePermission("Me.Chat.Symbols");
                                printFormatter.sendPlayerAccomplishmentMessage(player, "You removed " + args[0] + "'s permission to use symbols in chat!");
                                printFormatter.sendPlayerError(Bukkit.getPlayer(otherPlayer), player.getName() + " revoked your ability to use symbols in chat!");
                            }else{
                                plugin.getProfileHandler().getProfile(otherPlayer).getPermissions().givePermission("Me.Chat.Symbols");
                                printFormatter.sendPlayerAccomplishmentMessage(player, "You gave " + args[0] + " the permission to use symbols in chat!");
                                printFormatter.sendPlayerAccomplishmentMessage(Bukkit.getPlayer(otherPlayer), player.getName() + " gave you the permission to use symbols in chat!");
                            }
                        }catch(Exception ex){
                            try{
                                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                                if(profileHandler.getProfile(offlinePlayer.getUniqueId()).getPermissions().hasPermission("Me.Chat.Symbols")){
                                profileHandler.getProfile(offlinePlayer.getUniqueId()).getPermissions().removePermission("Me.Chat.Symbols");
                                printFormatter.sendPlayerAccomplishmentMessage(player, "You removed " + args[0] + "'s permission to use symbols in chat!");
                            }else{
                                profileHandler.getProfile(offlinePlayer.getUniqueId()).getPermissions().givePermission("Me.Chat.Symbols");
                                printFormatter.sendPlayerAccomplishmentMessage(player, "You gave " + args[0] + " the permission to use symbols in chat!");
                            }
                            }catch(Exception ez){
                                printFormatter.sendPlayerError(player, "Player not found: " + args[0]);
                            }
                        }
                    }else{
                        printFormatter.sendPlayerError(player, "You don't have permission to perform this command!");
                    }
                }else{
                     try{
                            UUID otherPlayer = Bukkit.getServer().getPlayer(args[0]).getUniqueId();
                            if(profileHandler.getProfile(otherPlayer).getPermissions().hasPermission("Me.Chat.Symbols")){
                                profileHandler.getProfile(otherPlayer).getPermissions().removePermission("Me.Chat.Symbols");
                                printFormatter.sendConsoleNotification("You removed " + args[0] + "'s permission to use symbols in chat!");
                                printFormatter.sendPlayerError(Bukkit.getPlayer(otherPlayer), sender.getName() + " revoked your ability to use symbols in chat!");
                            }else{
                                profileHandler.getProfile(otherPlayer).getPermissions().givePermission("Me.Chat.Symbols");
                                printFormatter.sendConsoleNotification("You gave " + args[0] + " the permission to use symbols in chat!");
                                printFormatter.sendPlayerAccomplishmentMessage(Bukkit.getPlayer(otherPlayer), sender.getName() + " gave you the permission to use symbols in chat!");
                            }
                        }catch(Exception ex){
                            printFormatter.sendConsoleError("Cannot find " + args[0] + "!");
                        }
                }
            }else if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    profileHandler.getProfile(player.getUniqueId()).getPermissions().givePermission("Me.Command.Symbols");
                    profileHandler.getProfile(player.getUniqueId()).getPermissions().givePermission("Me.Command.Symbols.Other");
                }
            }else{
                printFormatter.sendUserError(sender, "Invalid arguments, use /symbols or /help");
            }
        }
        return true;
    } 
}
