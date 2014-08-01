package Main;

public class ServerStopHandler extends Thread{
    
    Main plugin;
    
    public ServerStopHandler(Main plugin){
        this.plugin = plugin;
    }
    
    @Override
    public void run(){
        /*
        Runs when the server is shutdown, no matter how "brutual" the server was closed
        */
        if(!plugin.isAlreadySaved()){
            System.out.println("Thread save");
            plugin.getProfileHandler().saveProfiles();
             plugin.alreadySaved = true;
        }
        
    }
}
