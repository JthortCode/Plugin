package Commands;

import Main.Main;
import Profile.Profile;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SymbolsCommand implements CommandExecutor{
    
    Main plugin;
    
    public SymbolsCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        if(command.equalsIgnoreCase("symbols") || (command.equalsIgnoreCase("sym"))){
            if(args.length == 0){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    Profile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
                    if(profile.getPermissions().hasPermission("Me.Command.Symbols")){
                        if(profile.getPermissions().hasPermission("Me.Chat.Symbols")){
                            profile.getPermissions().removePermission("Me.Chat.Symbols");
                            plugin.getTools().getPrintFormatter().sendPlayerError(
                                    player, "You no longer chatting using the symbols filter!");
                        }else{
                            profile.getPermissions().givePermission("Me.Chat.Symbols");
                            plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(
                                    player, "You are now chatting using the symbols filter!");
                        }
                        
                    }else{
                        plugin.getTools().getPrintFormatter().sendPlayerError(player, "You don't have permission to perform this command!");
                    }
                }else{
                    plugin.getTools().getPrintFormatter().sendConsoleError("You cannot give symbols to yourself!");
                }
            }else if(args.length == 1){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    Profile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
                    if(profile.getPermissions().hasPermission("Me.Command.Symbols.Other")){
                        try{
                            UUID otherPlayer = Bukkit.getServer().getPlayer(args[0]).getUniqueId();
                            if(plugin.getProfileHandler().getProfile(otherPlayer).getPermissions().hasPermission("Me.Chat.Symbols")){
                                plugin.getProfileHandler().getProfile(otherPlayer).getPermissions().removePermission("Me.Chat.Symbols");
                                plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(player, "You removed " + args[0] + "'s permission to use symbols in chat!");
                                plugin.getTools().getPrintFormatter().sendPlayerError(Bukkit.getPlayer(otherPlayer), player.getName() + " revoked your ability to use symbols in chat!");
                            }else{
                                plugin.getProfileHandler().getProfile(otherPlayer).getPermissions().givePermission("Me.Chat.Symbols");
                                plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(player, "You gave " + args[0] + " the permission to use symbols in chat!");
                                plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(Bukkit.getPlayer(otherPlayer), player.getName() + " gave you the permission to use symbols in chat!");
                            }
                        }catch(Exception ex){
                            plugin.getTools().getPrintFormatter().sendPlayerError(player, "Cannot find " + args[0] + "!");
                        }
                    }else{
                        plugin.getTools().getPrintFormatter().sendPlayerError(player, "You don't have permission to perform this command!");
                    }
                }else{
                     try{
                            UUID otherPlayer = Bukkit.getServer().getPlayer(args[0]).getUniqueId();
                            if(plugin.getProfileHandler().getProfile(otherPlayer).getPermissions().hasPermission("Me.Chat.Symbols")){
                                plugin.getProfileHandler().getProfile(otherPlayer).getPermissions().removePermission("Me.Chat.Symbols");
                                plugin.getTools().getPrintFormatter().sendConsoleNotification("You removed " + args[0] + "'s permission to use symbols in chat!");
                                plugin.getTools().getPrintFormatter().sendPlayerError(Bukkit.getPlayer(otherPlayer), sender.getName() + " revoked your ability to use symbols in chat!");
                            }else{
                                plugin.getProfileHandler().getProfile(otherPlayer).getPermissions().givePermission("Me.Chat.Symbols");
                                plugin.getTools().getPrintFormatter().sendConsoleNotification("You gave " + args[0] + " the permission to use symbols in chat!");
                                plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(Bukkit.getPlayer(otherPlayer), sender.getName() + " gave you the permission to use symbols in chat!");
                            }
                        }catch(Exception ex){
                            plugin.getTools().getPrintFormatter().sendConsoleError("Cannot find " + args[0] + "!");
                        }
                }
            }else if(args.length == 2){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().givePermission("Me.Command.Symbols");
                    plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().givePermission("Me.Command.Symbols.Other");
                }
            }else{
                plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid arguments, use /symbols or /help");
            }
        }
        return true;
    }
    
    
}
