/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Profile;

import Main.Main;
import java.util.UUID;

public class Profile {
    
    Main plugin;
    UUID player;
    Permissions permissions;
    
    public Profile(Main plugin, UUID player){
        this.plugin = plugin;
        this.player = player;
        permissions = new Permissions(plugin, player);
    }
    public Profile(Main plugin, UUID player, Permissions permissions){
        this.plugin = plugin;
        this.player = player;
        this.permissions = permissions;
    }
    public UUID getProfileUUID(){
        return player;
    }
    public Permissions getPermissions(){
        return permissions;
    }
   
    
}
