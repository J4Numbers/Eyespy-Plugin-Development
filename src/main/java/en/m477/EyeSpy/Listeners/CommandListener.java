package en.m477.EyeSpy.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;

import en.m477.EyeSpy.Logging.Logging;

/**
 * Command listener, fired when someone enters a command. The listener will then send it to the en.m477.logging.logging class for assimilation... Commands are specified as any text starting with '/'
 * @author Matthew Ball
 *
 */

public class CommandListener implements Listener {
	
	private static String Message;
	
	/**
	 * Triggered when a command is entered, the message is recorded as is the player name, and both are sent to the logging class for processing.
	 * @param PlayerCommandPreprocessEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerCommand( PlayerCommandPreprocessEvent event ) {
		String name = event.getPlayer().getName();
		Message = event.getMessage();
		Logging.addNewCommand(name, Message);
	}
}
