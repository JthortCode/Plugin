package Commands;

import Main.Main;
import Profile.Permissions;
import Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionsCommands implements CommandExecutor{
    
    Main plugin;
    
    public PermissionsCommands(Main plugin){
        this.plugin = plugin;
    }
    
     @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        
        if(command.equalsIgnoreCase("permissions") || command.equalsIgnoreCase("perms")){
            switch(args.length){
                case 0:///////////////////////////////////////////////////////////////////////////////
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        Profile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
                        if(profile.getPermissions().hasPermission("Me.Command.Permissions")){
                           player.sendMessage(ChatColor.RED + "----------[Your Permissions]---------");
                            int counter = 1;
                            for(String perm: profile.getPermissions().getPermissions().split(" ")){
                                if(!perm.equals("")){
                                perm = perm.trim();
                                player.sendMessage(ChatColor.BLUE + "" + counter + ") " + perm);
                                counter++;
                                }
                            }
                        }else{
                           plugin.getTools().getPrintFormatter().sendPlayerError(
                                   player, "You don't have permission to perform this command!");
                        }
                    }else{
                        plugin.getTools().getPrintFormatter().sendConsoleMessage("Permissions: All");
                    }
                    break;
                case 1:///////////////////////////////////////////////////////////////////////////
                    if(args[0].equalsIgnoreCase("help")){
                        if(sender instanceof Player){
                            
                            Player player = (Player) sender;
                            Permissions permissions = plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions();
                            if(permissions.hasPermission("Me.Command.PermissionsHelp")){
                            player.sendMessage(ChatColor.RED + "--------[Permissions Help Menu]---------");
                            if(permissions.hasPermission("Me.Command.Give")){
                                player.sendMessage(ChatColor.RED + "/perms give <User> <Permission> - " + ChatColor.GREEN + "Gives a player a permission");
                            }
                            if(permissions.hasPermission("Me.Command.Remove")){
                                player.sendMessage(ChatColor.RED + "/perms remove <User> <Permissions> - " + ChatColor.GREEN + "Removes a players permission");
                            }
                            if(permissions.hasPermission("Me.Command.Permissions")){
                                player.sendMessage(ChatColor.RED + "/permissions - " + ChatColor.GREEN + "View your permissions");
                            }
                            if(permissions.hasPermission("Me.Command.Permissions.Other")){
                                player.sendMessage(ChatColor.RED + "/perms <User> - " + ChatColor.GREEN + "View another users permissions");
                            }
                            if(permissions.hasPermission("Me.Command.Deleteprofile")){
                                player.sendMessage(ChatColor.RED + "/perms deleteprofile <User> - " + ChatColor.GREEN + "Set another users group");
                            }
                            if(permissions.hasPermission("Me.Command.Setgroup")){
                                player.sendMessage(ChatColor.RED + "/perms setgroup <User> <Group> - " + ChatColor.GREEN + "Set another users group");
                            }
                            }else{
                                plugin.getTools().getPrintFormatter().sendPlayerError(player, "You don't have permission to perform this command!");
                            }
                        }else{
                            sender.sendMessage(ChatColor.RED + "--------[Permissions Help Menu (Console)]---------");
                            sender.sendMessage(ChatColor.RED + "/perms - " + ChatColor.GREEN + "View your permissions");
                            sender.sendMessage(ChatColor.RED + "/perms <User> - " + ChatColor.GREEN + "View another players permissions");
                            sender.sendMessage(ChatColor.RED + "/perms give <User> <Permission> - " + ChatColor.GREEN + "Give another player a permission");
                            sender.sendMessage(ChatColor.RED + "/perms remove <User> <Permission> - " + ChatColor.GREEN + "Remove another players permission");
                            sender.sendMessage(ChatColor.RED + "/perms deleteprofile <User> - " + ChatColor.GREEN + "Delete another users profile!");
                            sender.sendMessage(ChatColor.RED + "/perms setgroup <User> <Group> - " + ChatColor.GREEN + "Set another users group");
                        }
                    }
                    break;
                case 2://////////////////////////////////////////////////////////////////////////////
                    if(args[0].equalsIgnoreCase("deleteprofile")){
                         if(sender instanceof Player){
                            Player playerSender = (Player) sender;
                            if(!plugin.getProfileHandler().getProfile(playerSender.getUniqueId()).getPermissions().hasPermission("Me.Command.Deleteprofile")){
                               plugin.getTools().getPrintFormatter().sendPlayerError(playerSender, "You don't have permission to perform this command!");
                               break;
                            }
                        }  
                        
                        try{
                            Player targetPlayer = Bukkit.getPlayer(args[1]);
                            plugin.getProfileHandler().deleteProfile(plugin.getProfileHandler().getProfile(targetPlayer.getUniqueId()));
                            plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "Sucessfully deleted " + args[1] + "'s profile!");
                            plugin.getTools().getPrintFormatter().sendPlayerError(targetPlayer, "Your profile was reset by " + sender.getName());
                           plugin.getProfileHandler().getProfiles().add(new Profile(plugin, targetPlayer.getUniqueId(), true));
                            plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "Sucessfully generated a new profile for " + args[1]);
                        }catch(Exception ex){
                            try{
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            plugin.getProfileHandler().deleteProfile(plugin.getProfileHandler().getProfile(target.getUniqueId()));
                            plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "Sucessfully deleted " + args[1] + "'s profile!");
                            plugin.getProfileHandler().getProfiles().add(new Profile(plugin, target.getUniqueId(), true));
                           plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "Sucessfully generated a new profile for " + args[1]);
                            }catch(Exception ez){
                                plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid player " + args[1]);
                            }
                        }
                    }
                    break;
                case 3:///////////////////////////////////////////////////////////////////////////////////////
                    //perm give Ian ""
                    if(args[0].equalsIgnoreCase("give")){
                        
                       if(!(sender instanceof Player)){
                           
                       }else{
                           Player playerSender = (Player) sender;
                           if(!plugin.getProfileHandler().getProfile(playerSender.getUniqueId()).getPermissions().hasPermission("Me.Command.Give")){
                              plugin.getTools().getPrintFormatter().sendPlayerError(playerSender, "You don't have permission to perform this command!");
                               break; 
                           }
                       }
                       try{
                           Player player = Bukkit.getPlayer(args[1]);
                           plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().givePermission(args[2]);
                           plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "You gave " + args[1] + " the permission " + args[2]);
                           plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(player, sender.getName() + " has given you the permission " + args[2]);
                       }catch(Exception ex){
                           try{
                           OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(args[1]);
                           Profile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
                           Permissions permissions = profile.getPermissions();
                                   permissions.givePermission(args[2]);
                          plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "You gave " + args[1] + " the permission " + args[2]);
                           
                           }catch(Exception ez){
                              plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid player: " + args[1]); 
                           }
                       }
                    }else if(args[0].equalsIgnoreCase("remove")){
                        if(!(sender instanceof Player)){
                           
                       }else{
                           Player playerSender = (Player) sender;
                           if(!plugin.getProfileHandler().getProfile(playerSender.getUniqueId()).getPermissions().hasPermission("Me.Command.Remove")){
                              plugin.getTools().getPrintFormatter().sendPlayerError(playerSender, "You don't have permission to perform this command!");
                               break; 
                           }
                       }
                        try{
                            Player playerTarget = Bukkit.getPlayer(args[1]);
                            if(plugin.getProfileHandler().getProfile(playerTarget.getUniqueId()).getPermissions().hasPermission(args[2])){
                                plugin.getProfileHandler().getProfile(playerTarget.getUniqueId()).getPermissions().removePermission(args[2]);
                                plugin.getTools().getPrintFormatter().sendUserError(sender, "You removed " + playerTarget.getName() + "'s permission: " + args[2]);
                              plugin.getTools().getPrintFormatter().sendPlayerError(playerTarget, sender.getName() + " removed your permission: " + args[2]);
                            }else{
                                plugin.getTools().getPrintFormatter().sendUserError(sender, "That player doesn't have " + args[2] + " to remove!");
                            }
                        }catch(Exception ex){
                            try{
                            OfflinePlayer playerTarget = Bukkit.getServer().getOfflinePlayer(args[1]);
                            if(plugin.getProfileHandler().getProfile(playerTarget.getUniqueId()).getPermissions().hasPermission(args[2])){
                                plugin.getProfileHandler().getProfile(playerTarget.getUniqueId()).getPermissions().removePermission(args[2]);
                                plugin.getTools().getPrintFormatter().sendUserError(sender, "You removed " + playerTarget.getName() + "'s permission: " + args[2]);
                            }else{
                                plugin.getTools().getPrintFormatter().sendUserError(sender, "That player doesn't have " + args[2] + " to remove!");
                            }
                            }catch(Exception ez){
                                 plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid player: " + args[1]);
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("setgroup")){
                        if(sender instanceof Player){
                            
                            Player player = (Player) sender;
                            if(!plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().hasPermission("Me.Command.Setgroup")){
                                plugin.getTools().getPrintFormatter().sendPlayerError(player, "You don't have permission to perform this command!");
                                break;
                            }
                        }
                            try{
                                Player targetPlayer = Bukkit.getPlayer(args[1]);
                                plugin.getProfileHandler().getProfile(targetPlayer.getUniqueId()).getPermissions().setGroup(args[2]);
                                plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "Sucessfully set " + args[1] + "'s group to " + args[2]);
                                plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(targetPlayer, sender.getName() + " changed you to group " + args[2]);
                            }catch(Exception ex){
                                try{
                                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                    try{
                                    plugin.getProfileHandler().getProfile(target.getUniqueId()).getPermissions().setGroup(args[2]);
                                    }catch(Exception ey){
                                        plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid group! " + args[2]);
                                    }
                                    plugin.getTools().getPrintFormatter().sendUserAccomplishmentMessage(sender, "Sucessfully set " + args[1] + "'s group to " + args[2]);
                                }catch(Exception ez){
                                    plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid player: " + args[1]);
                                }
                            }
                        
                    }else{
                        plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid arguments!");
                    }
                    break;
                default:
                    plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid arguments!");
                    break;
            }
        }
        return true;
    }
    
    
}
