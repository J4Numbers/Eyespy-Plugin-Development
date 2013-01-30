package en.m477.EyeSpy;

import en.m477.EyeSpy.Logging.Logging;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class EyeSpy extends JavaPlugin{
	
	public EyeSpy plugin;
	public Logger log = Logger.getLogger("Minecraft");
	
	public static String version;
	public static String name;
	public static EyeSpy self = null;
	
	Logging db;
	
	/**
	 * SQL variables
	 * @param Host
	 * @param Username
	 * @param Password
	 * @param Database
	 */
	
	public static String host;
	public static String username;
	public static String password;
	public static String database;
	
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
        password = getConfig().getString("Eyespy.database.password");
        database = getConfig().getString("Eyespy.database.database");
        
        Logging sqldb = new Logging(host, database, this);
        
        sqldb.startSql();
        
        printInfo("EyeSpy has been enabled!");
        // TODO Insert logic to be performed when the plugin is enabled		
    }
 
    @Override
    public void onDisable() {
    	printInfo("EyeSpy has been disabled!");
        // TODO Insert logic to be performed when the plugin is disabled
    }
    
    public static void printSevere(String line) {
      self.log.severe("[EyeSpy] " + line);
    }
    
    public static void printWarning(String line) {
      self.log.warning("[EyeSpy] " + line);
    }
    
    public static void printInfo(String line) {
      self.log.info("[EyeSpy] " + line);
    }
}
