package en.m477.EyeSpy;

//import en.m477.EyeSpy.Listeners.BlockListener; Excluded for this build
import en.m477.EyeSpy.Listeners.ChatListener;
import en.m477.EyeSpy.Listeners.CommandListener;
import en.m477.EyeSpy.Logging.Logging;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import java.util.logging.Logger;

/**
 * Base class for EyeSpy. Main parts are the onEnable(), onDisable(), and the print areas at the moment.
 * @author Matthew Ball
 *
 */
public class EyeSpy extends JavaPlugin{
	
	public EyeSpy plugin;
	public Logger log = Logger.getLogger("Minecraft");
	
	public static String version;
	public static String name;
	public static EyeSpy self = null;
	
	Logging db;
	
	public static String host;
	public static String username;
	public static String password ;
	public static String database;
	
	/**
	 * This is the onEnable class for when the plugin starts up. Basic checks are run for the version, name and information of the plugin, then startup occurs.
	 */
	@Override
    public void onEnable(){
        
        version = this.getDescription().getVersion();
        name = this.getDescription().getName();
        self = this;
        log.info(name + " version " + version + " has started...");
        
        PluginManager pm = getServer().getPluginManager();
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        //Collect Database information
        
        host = getConfig().getString("EyeSpy.database.host");
        username = getConfig().getString("EyeSpy.database.username");
        password = getConfig().getString("EyeSpy.database.password");
        database = getConfig().getString("EyeSpy.database.database");
        
        Logging sqldb = new Logging();
        
        sqldb.startSql();
        
        if (!Logging.sql) {
        	printSevere("SQL database NOT activated!");
        }
        
        printInfo("EyeSpy has been enabled!");
        
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new CommandListener(), this);
        //pm.registerEvents(new BlockListener(), this); Excluded for this build
        // TODO Insert logic to be performed when the plugin is enabled		
    }
 
	/**
	 * The disable needs work to be completed, namely a routine which cuts connection with the database a bit more elegantly than it currently does.
	 */
    @Override
    public void onDisable() {
    	
    	printInfo("EyeSpy has been disabled!");
        // TODO Insert logic to be performed when the plugin is disabled
    }
    
    /**
     * Prints a SEVERE warning to the console.
     * @param line This is the error message
     */
    public static void printSevere(String line) {
      self.log.severe("[EyeSpy] " + line);
    }
    
    /**
     * Prints a WARNING to the console.
     * @param line This is the error message
     */
    public static void printWarning(String line) {
      self.log.warning("[EyeSpy] " + line);
    }
    
    /**
     * Prints INFO to the console
     * @param line This is the information
     */
    public static void printInfo(String line) {
      self.log.info("[EyeSpy] " + line);
    }
    
    public static void printDebug(String line) {
      self.log.info("[EyeSpy DEBUG] " + line);
    }
    
}
