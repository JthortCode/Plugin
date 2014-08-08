package Profile;

import Main.Main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scoreboard.Objective;

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
            profileConfig.set(profile.getProfileUUID().toString() + ".Stats", "");
            for (Map.Entry<String, Double> entry : profile.getStats().getXpData().entrySet()) {
                profileConfig.set(profile.getProfileUUID().toString() + ".Stats." + entry.getKey(), entry.getValue());
            }
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
            HashMap <String,Double> Xp = new HashMap();
            for(String skill : plugin.getScoreboardHandler().getSkillList()){
                Xp.put(skill, profileConfig.getDouble(uid + ".Stats." + skill));
            }
            Stats stats = new Stats(plugin, 
                    UUID.fromString(uid),
                    Xp
            );
           Profile profile = new Profile(plugin, UUID.fromString(uid), pemissions, stats);
           profiles.add(profile);
        }
        savePermissionsConfig();
        plugin.getTools().getPrintFormatter().sendConsoleNotification("Done Loading profiles.");
    }
    public void deleteProfile(Profile profile){
        profiles.remove(profile);
        profile = null;
    }

}
