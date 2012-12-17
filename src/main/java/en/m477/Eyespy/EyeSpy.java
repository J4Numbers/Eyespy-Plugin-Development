package en.m477.EyeSpy;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class EyeSpy extends JavaPlugin{
	@Override
    public void onEnable(){
		getLogger().info("EyeSpy has been enabled!");
        // TODO Insert logic to be performed when the plugin is enabled		
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("EyeSpy has been disabled!");
        // TODO Insert logic to be performed when the plugin is disabled
    }
}
