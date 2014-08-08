package Profile;

import Main.Main;
import RPG.StatsCalculator;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Score;

public class Stats {
    
    Main plugin;
    UUID player;
    
    HashMap<String, Double> Xp = new HashMap();
    
    public Stats(Main plugin, UUID player){
        this.plugin = plugin;
        this.player = player;
        setupXpData();
    }
    //Called when loading from config with xp
    public Stats(Main plugin, UUID player, HashMap<String, Double> Xp){
        this.plugin = plugin;
        this.player = player;
        this.Xp = Xp;
    }
    
    
    //Level methods --------------------------
    public Integer getLevel(String skill){
        return Bukkit.getPlayer(player).getScoreboard().getObjective("test").getScore(ChatColor.GREEN + skill + ":").getScore();
    }
    public void incrementLevel(String skill){
        Score score = Bukkit.getPlayer(player).getScoreboard().getObjective("test").getScore(ChatColor.GREEN + skill + ":");
        score.setScore(score.getScore() + 1);
    }
    public void decrementLevel(String skill){
        Score score = Bukkit.getPlayer(player).getScoreboard().getObjective("test").getScore(ChatColor.GREEN + skill + ":");
        score.setScore(score.getScore() - 1);
    }
    public void setLevel(String skill, int newAmount){
        Bukkit.getPlayer(player).getScoreboard().getObjective("test").getScore(ChatColor.GREEN + skill + ":").setScore(newAmount);
    }
    //---------------------------------------------
    
    
    private Double getCurrentXP(String skill){
        return Xp.get(skill);
    }
    public void giveXp(String skill, double amount){
       
        //Increment their current xp
        Xp.put(skill, getCurrentXP(skill)+amount);
       
        //Remove the previous bar if they have one and give a new one with appropriate xp and name
        BarAPI.removeBar(Bukkit.getPlayer(player));
        Double amnt = amount;
        DecimalFormat format = new DecimalFormat("0.00");
            BarAPI.setMessage(Bukkit.getPlayer(player), ChatColor.RED + skill + ChatColor.GRAY + " +" + ChatColor.DARK_GRAY+ format.format(amnt));
            BarAPI.setHealth(Bukkit.getPlayer(player), (float)StatsCalculator.calculateBarPercent(getCurrentXP(skill), getLevel(skill)));
           
            //remove the bar in seven seconds
            removeInSeven();
            
            //Check for level ups
        if(StatsCalculator.isLevelUp(skill, getLevel(skill), getCurrentXP(skill))){
             Xp.put(skill, 0.0);
             incrementLevel(skill);
             plugin.getTools().getPrintFormatter().sendPlayerAccomplishmentMessage(
                     Bukkit.getPlayer(player), "You leveled up in " + skill);
         }
    }
    private void removeInSeven(){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
    		try{
                    BarAPI.removeBar(Bukkit.getPlayer(player));
                }catch(Exception ex){
                    
                }
            }
    	}, 140);
    }
    
    private void setupXpData(){
        for(String skill : plugin.getScoreboardHandler().getSkillList()){
            Xp.put(skill, 0.0);
        }
    }
    
    //Used to load all to config to handle reloads
    public HashMap<String, Double> getXpData(){
        return Xp;
    }
}
