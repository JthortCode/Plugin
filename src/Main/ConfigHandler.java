package Main;

public class ConfigHandler {
    
    Main plugin;
    
    public ConfigHandler(Main plugin){
        
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }
        
}
