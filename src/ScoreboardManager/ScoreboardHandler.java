/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ScoreboardManager;

import Main.Main;
import Profile.Profile;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardHandler {
    
    Main plugin;
    ScoreboardManager manager;
    String[] skills = {"Overall", "Woodcutting", "Mining", "Agility", "Farming", "Attack", "Defence",
       "Range", "Building", "Crafting", "Smithing", "Alchemy"};
    
    public ScoreboardHandler(Main plugin){
        this.plugin = plugin;
        manager = Bukkit.getScoreboardManager();
    }
    public void createNewScoreboard(UUID player){
        
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Current Stats");
        
        for(String skill : skills){
            objective.getScore(ChatColor.GREEN + skill + ":").setScore(3);
        }
        
        try{
            Player playerUser = Bukkit.getPlayer(player);
            playerUser.setScoreboard(board);
        }catch(Exception ex){
            plugin.getTools().getPrintFormatter().sendConsoleError("Cannot create a scoreboard for a player that's not online");
        }
    }
    public String[] getSkillList(){
        return skills;
    }
}
