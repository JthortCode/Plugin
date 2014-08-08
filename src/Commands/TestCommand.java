/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Commands;

import Main.Main;
import Profile.Profile;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/**
 *
 * @author User
 */
public class TestCommand implements CommandExecutor{
    
    Main plugin;
    
    public TestCommand(Main plugin){
       this.plugin = plugin;   
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String command, String[] args) {
        Player player = (Player) sender;
        if(!BarAPI.hasBar(player)){
            BarAPI.setMessage(player, "Testing!");
        }else{
        BarAPI.removeBar(player);
    }
        return false;
    }
}
