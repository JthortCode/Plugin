package RPG;

import Main.Main;


public class RPGHandler {
    
    Main plugin;
    RandomTeleporter randomTeleporter;
    
    public RPGHandler(Main plugin){
        this.plugin = plugin;
        randomTeleporter = new RandomTeleporter();
    }
    
    public RandomTeleporter getRandomTeleporter(){
        return randomTeleporter;
    }
    
}
