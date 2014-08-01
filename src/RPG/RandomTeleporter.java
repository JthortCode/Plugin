package RPG;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class RandomTeleporter {
    public RandomTeleporter(){
        
    }
    
    public void sendToRandomLocation(Player player){
        Random random = new Random();
        int x,y,z;
        World world = player.getWorld();
       do{
           
         x = random.nextInt(699);
         z = random.nextInt(699);
         y = player.getWorld().getHighestBlockYAt(x, z) + 2;
       }while(isWater(new Location(world, x, y-3, z)));
        player.teleport(new Location(
        player.getWorld(),
        x,
        y,
        z
        ));
    }
    
    public boolean isWater(Location location){
        Material type = location.getBlock().getType();
      if(type == Material.WATER || type == Material.LAVA
              || type == Material.CACTUS ||  type == Material.FIRE
              || type == Material.STATIONARY_WATER || type == Material.STATIONARY_LAVA
              ){
          return true;
      }else{
          return false;
      }
    }
    
}
