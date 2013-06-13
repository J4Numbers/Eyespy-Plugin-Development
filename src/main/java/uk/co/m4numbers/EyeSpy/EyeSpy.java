package uk.co.m4numbers.EyeSpy;

//import uk.co.m4numbers.EyeSpy.Listeners.BlockListener; Excluded for this build
import uk.co.m4numbers.EyeSpy.Listeners.ChatListener;
import uk.co.m4numbers.EyeSpy.Listeners.CommandListener;
import uk.co.m4numbers.EyeSpy.Logging.Logging;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
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
	public static String Server;
	public static EyeSpy self = null;
	
	Logging db;
	
	public static String host;
	public static String username;
	public static String password ;
	public static String database;
	public static String prefix;
	
	public static Properties props = new Properties();
	
	/**
	 * This is the onEnable class for when the plugin starts up. Basic checks are run for the version, name and information of the plugin, then startup occurs.
	 */
	@Override
    public void onEnable(){
        
        version = this.getDescription().getVersion();
        name = this.getDescription().getName();
        Server = this.getServer().getName();
        self = this;
        log.info(name + " version " + version + " has started...");
        
        PluginManager pm = getServer().getPluginManager();
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        props.setProperty("#mine", "Global");
        props.setProperty("#dev", "Dev");
        props.setProperty("#help", "Support");
        props.setProperty("#mods", "Mods");
        props.setProperty("#new", "New");
        props.setProperty("#trade", "Trade");
        try {
			props.store(new FileOutputStream("chan.properties"), null);
			printInfo("Properties stored.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Collect Database information
        
        host     = getConfig().getString("EyeSpy.database.host");
        username = getConfig().getString("EyeSpy.database.username");
        password = getConfig().getString("EyeSpy.database.password");
        database = getConfig().getString("EyeSpy.database.database");
        prefix   = getConfig().getString("EyeSpy.database.prefix");
        
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
    	Logging.killConnection();
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
    
    public static String ircToGame( String channel ) {
    	try {
			props.load( new FileInputStream("chan.properties") );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return props.getProperty(channel);
    }
    
}
