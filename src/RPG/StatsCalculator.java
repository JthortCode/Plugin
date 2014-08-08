package RPG;

import org.bukkit.Bukkit;

public class StatsCalculator {
    public static void deservesOverallRankup(){
        
    }
    //3x^1.9 + 2x
    public static boolean isLevelUp(String skill, int level, double xp){
        double xpRequired = ((3*Math.pow(level, 1.9))+(2*level));
        return xpRequired <= xp;
    }
    public static double calculateBarPercent(double xp, int level){
        float xpRequired = (float) ((3*Math.pow(level, 1.9))+(2*level));
        return ((xp/xpRequired)*100);
    }
    
    
}
