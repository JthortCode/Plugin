package Profile;

import Main.Main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class ProfileHandler{
    
    Main plugin;
    
    File profileFile;
    YamlConfiguration profileConfig;
    
    private List<Profile> profiles = new ArrayList();
    
    public ProfileHandler(Main plugin){
        this.plugin = plugin;
        createProfileConfig();
        loadProfiles();
    }
    private void createProfileConfig(){
        profileFile = new File(plugin.getDataFolder(), "Profile.yml");
        profileConfig = YamlConfiguration.loadConfiguration(profileFile);
        savePermissionsConfig();
    }
    private void savePermissionsConfig(){
        try {
            profileConfig.save(profileFile);
        } catch (IOException ex) {
            plugin.getTools().getPrintFormatter().sendConsoleError("Error while saving Permissions Config, code: 26436EF");
        }
    }
    public void createNewProfie(UUID player){
          profileConfig.set(player.toString(), " ");
          profileConfig.set(player.toString() + ".Perms", " ");
          savePermissionsConfig();
          setGroup(player, "Default"); 
    }
    private void setGroup(UUID player, String group){
        profileConfig.set(player + ".Group", group);
        savePermissionsConfig();
        plugin.getTools().getPrintFormatter().sendConsoleNotification(
                       Bukkit.getPlayer(player).getName() + " is now in group " + group
                );
    }
    public Profile getProfile(UUID player){
        for(Profile profile: profiles){
            if(profile.getProfileUUID().toString().equals(player.toString())){
                return profile;
            }
        }
        return null;
    }
    public List<Profile> getProfiles(){
        return profiles;
    }
    public void saveProfiles(){
        plugin.getTools().getPrintFormatter().sendConsoleNotification("Saving profiles...");
        
        for(Profile profile: profiles){
            profileConfig.set(profile.getProfileUUID().toString(), "");
            profileConfig.set(profile.getProfileUUID().toString() + ".Perms", profile.getPermissions().getPermissions());
            profileConfig.set(profile.getProfileUUID().toString() + ".Group", profile.getPermissions().getGroup());
            savePermissionsConfig();
        }
        
        plugin.getTools().getPrintFormatter().sendConsoleNotification("Done.");
    }
    private void loadProfiles(){
        plugin.getTools().getPrintFormatter().sendConsoleNotification("Loading profiles...");
        for(String uid: profileConfig.getKeys(false)){
            Permissions pemissions = new Permissions(plugin, 
                                UUID.fromString(uid), 
                                profileConfig.getString(UUID.fromString(uid) + ".Perms"),
                                profileConfig.getString(UUID.fromString(uid) + ".Group"));
           Profile profile = new Profile(plugin, UUID.fromString(uid), pemissions);
           profiles.add(profile);
        }
        savePermissionsConfig();
        plugin.getTools().getPrintFormatter().sendConsoleNotification("Done.");
    }

}
