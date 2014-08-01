package Profile;

import Main.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;

import org.bukkit.event.Listener;

public class Permissions implements Listener{
    
    
    Main plugin;
    UUID player;
    String permissions;
    String group;
    
    /* Setting up the permissions configuration file */
    public Permissions(Main plugin, UUID player){
        this.player = player;
        this.plugin = plugin;
       permissions = " ";
       group = "Default";
    }
   public Permissions(Main plugin, UUID player, String permissions, String group){
       this.player = player;
        this.plugin = plugin;
       this.permissions = permissions;
       this.group = group;
   }
    
    public void givePermission(String permission){
       
        permissions = permissions.concat(permission + " ");
    }
    public String getPermissions(){
        return permissions;
    }
    public boolean hasPermission(String permission){
      String[] perms =  permissions.split(" ");
      
        for(String perm: perms){
            if(perm.equals(permission)){
                return true;
            }
        }
        return false;
    }
    public void setGroup(String group){
        this.group = group;
        plugin.getTools().getPrintFormatter().sendConsoleNotification(
                       Bukkit.getPlayer(player).getName() + " is now in group " + group
                );
    }
    public String getGroup(){
        return group;
    }
    
    public void removePermission(String permission){
        String[] perms =  permissions.split(" ");
        for(String perm: perms){
            perm = perm.trim();
            if(perm.equals(permission)){
                String listofperms = permissions;
                permissions = listofperms.replace(permission + " ", "");
                plugin.getTools().getPrintFormatter().sendConsoleNotification(
                        "Sucessfully removed permisssion (" + permission + ") for " + Bukkit.getPlayer(player).getName()
                );
            }
        }
    }
    
    
    
  
}
