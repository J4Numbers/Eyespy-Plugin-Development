package en.m477.EyeSpy.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import en.m477.EyeSpy.EyeSpy;
import en.m477.EyeSpy.Logging.Logging;

import com.herocraftonline.dthielke.herochat.channels.Channel;
import com.herocraftonline.dthielke.herochat.event.ChannelChatEvent;

/**
 * Chat listener
 * @author M477h3w1012
 *
 */
public class ChatListener implements Listener {

	// TODO Add a tie in to HeroChat/Mess around with HeroChat things.
	
	private static String Message;
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerChat( ChannelChatEvent event ) {
		String name = null;
		if ( event.isSentByPlayer() ) {
			name = event.getSource();
		} else {
			name = "Server";
		}
		Channel channel = event.getChannel();
		String ch_name = channel.getCName();
		Message = event.getMessage();
		Logging.addNewChat(name, ch_name, Message);
	}
	
}