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
    Stats stats;
    
    /* Called when a new player needs a profile */
    public Profile(Main plugin, UUID player, boolean generateScoreboard){
        this.plugin = plugin;
        this.player = player;
        permissions = new Permissions(plugin, player);
        plugin.getScoreboardHandler().createNewScoreboard(player);
        stats = new Stats(plugin, player);
    }
    /* Called when a new profile is loaded up from the config file */
    public Profile(Main plugin, UUID player, Permissions permissions, Stats stats){
        this.plugin = plugin;
        this.player = player;
        this.permissions = permissions;
        this.stats = stats;
    }
    public UUID getProfileUUID(){
        return player;
    }
    public Permissions getPermissions(){
        return permissions;
    }
    public Stats getStats(){
        return stats;
    }
   
    
}
