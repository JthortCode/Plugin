
package Main;

import Commands.SymbolsCommand;
import Events.ChatEvent;
import Tools.ChatFilter;
import Commands.HelpCommand;
import Commands.MessageCommand;
import Commands.PermissionsCommands;
import Commands.ReplyCommand;
import Events.EntDamageEntityEvent;
import Events.EntDamageEvent;
import Events.OnCommandEvent;
import Events.OnJoinEvent;
import Events.OnLeaveEvent;
import Profile.Permissions;
import Profile.ProfileHandler;
import RPG.RPGHandler;
import Tools.Tools;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
    
    Tools tools;
    ChatEvent chat;
    SymbolsCommand chatCommands;
    ProfileHandler profileHandler;
    OnJoinEvent onJoinEvent;
    RPGHandler rpgHandler;
    PermissionsCommands permissionsComamnds;
    OnCommandEvent onCommandEvent;
    HelpCommand helpCommand;
    ConfigHandler configHandler;
    EntDamageEntityEvent entDamageEntityEvent;
    EntDamageEvent entDamageEvent;
    OnLeaveEvent onLeaveEvent;
    MessageCommand messageCommand;
    ReplyCommand replyCommand;
    
    /* Prevents multiple save instances */
    boolean useBackupSave = false;
    boolean alreadySaved = false;
    /*==================================*/
    
    @Override
 public void onEnable(){
     tools = new Tools(this);
     getTools().getPrintFormatter().sendConsoleNotification("Loading Objects...");
     PluginManager pm = getServer().getPluginManager();
     
     //Prevents data loss on unsupported shutdown by the ondisable method
     try{
         Runtime.getRuntime().addShutdownHook(new ServerStopHandler(this));
     }catch(Exception ex){
         getServer().getConsoleSender().sendMessage(
                 ChatColor.RED + "Error while starting up Runtime Stop Handler, Reverting to Alternate method.");
         useBackupSave = true;
     }
     
     configHandler = new ConfigHandler(this);
     
     //Initialize this first so printFormatter is enabled
     
     profileHandler = new ProfileHandler(this);
     
     onJoinEvent = new OnJoinEvent(this); 
     pm.registerEvents(onJoinEvent, this);
    
     
     
     chat = new ChatEvent(this, new ChatFilter(this));
     pm.registerEvents(chat, this);
     
     /* Setup the command executors */
     chatCommands = new SymbolsCommand(this);
     getCommand("symbols").setExecutor(chatCommands);
     getCommand("sym").setExecutor(chatCommands);
     
     permissionsComamnds = new PermissionsCommands(this);
     getCommand("permissions").setExecutor(permissionsComamnds);
     getCommand("perms").setExecutor(permissionsComamnds);
     
     helpCommand = new HelpCommand(this);
     getCommand("help").setExecutor(helpCommand);
     
     messageCommand = new MessageCommand(this);
     getCommand("message").setExecutor(messageCommand);
     getCommand("msg").setExecutor(messageCommand);
     
     replyCommand = new ReplyCommand(this);
     getCommand("reply").setExecutor(replyCommand);
     getCommand("r").setExecutor(replyCommand);
     
     /* Set up extra classes */
     rpgHandler = new RPGHandler(this);
     
     /* Set up event handlers */
     onCommandEvent = new OnCommandEvent(this);
     pm.registerEvents(onCommandEvent, this);
     
     entDamageEntityEvent = new EntDamageEntityEvent(this);
     pm.registerEvents(entDamageEntityEvent, this);
     
     entDamageEvent = new EntDamageEvent(this);
     pm.registerEvents(entDamageEvent, this);
     
     onLeaveEvent = new OnLeaveEvent(this);
     pm.registerEvents(onLeaveEvent, this);
 
     getTools().getPrintFormatter().sendConsoleNotification("Done.");
 }
 
 @Override
 public void onDisable(){
     if(useBackupSave){
         profileHandler.saveProfiles();
         getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Jthort] Sucessfully restored profile data, feeew...");
         getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Jthort] Please perform matinence on saving profiles to prevent his from happening again!");
     }else{
         alreadySaved = true;
         profileHandler.saveProfiles();
     }
 }
 public Tools getTools(){
     return tools;
 }
 public ProfileHandler getProfileHandler(){
     return profileHandler;
 }
 public RPGHandler getRPGHandler(){
     return rpgHandler;
 }
 public OnJoinEvent getOnJoinEvent(){
     return onJoinEvent;
 }
 public boolean isAlreadySaved(){
     return alreadySaved;
 }
 public ConfigHandler getConfigHandler(){
     return configHandler;
 }
 public MessageCommand getMessageCommand(){
     return messageCommand;
 }
}
