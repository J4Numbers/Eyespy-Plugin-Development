package en.m477.EyeSpy.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;

import en.m477.EyeSpy.Logging.Logging;

/**
 * Chat listener
 * @author M477h3w1012
 *
 */
public class ChatListener implements Listener {

	// TODO Add a tie in to HeroChat/Mess around with HeroChat things.
	
	private static String Message;
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerChat( AsyncPlayerChatEvent event ) {
		if ( event.isAsynchronous() ) {
			Player player = event.getPlayer();
			Message = event.getMessage();
			String name = player.getName();
			Logging.addNewChat(name, Message);
		}
	}
}