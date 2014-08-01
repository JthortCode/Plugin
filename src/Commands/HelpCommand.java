package Commands;

import Main.Main;
import Profile.Permissions;
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
    Main plugin;
    
    public HelpCommand(Main plugin){
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        if(command.equalsIgnoreCase("help")){
            if(args.length == 0){
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().hasPermission("Me.Command.Help")){
                for(Plugin plug : Bukkit.getPluginManager().getPlugins()){
                     List<Command> cmdList = PluginCommandYamlParser.parse(plug);
                     String group = plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().getGroup();
                      player.sendMessage(ChatColor.RED + "--------["+ group + " " + player.getName() +"]---------");
                        for(int i=0; i<= cmdList.size() -1; i++){
                            if(plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions().hasPermission("Me.Command." + cmdList.get(i).getLabel())){
                              player.sendMessage(ChatColor.RED + "/" + cmdList.get(i).getLabel() + ChatColor.GRAY + " : " + ChatColor.GREEN + cmdList.get(i).getDescription());
                            }
                        }
                }
                }else{
                    plugin.getTools().getPrintFormatter().sendPlayerError(player, "You don't have permission to perform this command!");
                }
            }else{
                plugin.getTools().getPrintFormatter().sendConsoleError("Not implemented for console!");
            }
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("permissions") || args[0].equalsIgnoreCase("perms")){
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
                            }else{
                                plugin.getTools().getPrintFormatter().sendPlayerError(player, "You don't have permission to perform this command!");
                            }
                        }else{
                            sender.sendMessage(ChatColor.RED + "--------[Permissions Help Menu (Console)]---------");
                            sender.sendMessage(ChatColor.RED + "/perms - " + ChatColor.GREEN + "View your permissions");
                            sender.sendMessage(ChatColor.RED + "/perms <User> - " + ChatColor.GREEN + "View another players permissions");
                            sender.sendMessage(ChatColor.RED + "/perms give <User> <Permission> - " + ChatColor.GREEN + "Give another player a permission");
                            sender.sendMessage(ChatColor.RED + "/perms remove <User> <Permission> - " + ChatColor.GREEN + "Remove another players permission");
                        }
                }else if(args[0].equalsIgnoreCase("symbols") || args[0].equalsIgnoreCase("sym")){
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        Permissions permissions = plugin.getProfileHandler().getProfile(player.getUniqueId()).getPermissions();
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
                         plugin.getTools().getPrintFormatter().sendPlayerError(player, "You don't have permission to perform this command!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "--------[ Symbols Help Menu (Console) ]---------");
                        sender.sendMessage(ChatColor.RED + "/Symbols - " + ChatColor.GREEN + "Toggle your symbols filter");
                        sender.sendMessage(ChatColor.RED + "/Symbols <User> - " + ChatColor.GREEN + "Toggle symbols for another player. (Doesn't prevent the use of the command)");
                        sender.sendMessage(ChatColor.RED + "/Symbols help - " + ChatColor.GREEN + "Symbols help menu");
                    }
                }
            }else{
                plugin.getTools().getPrintFormatter().sendUserError(sender, "Invalid arguments, use: /help");
            }
        }
        return true;
    }
}
