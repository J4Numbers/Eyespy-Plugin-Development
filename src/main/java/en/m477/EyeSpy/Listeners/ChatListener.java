package en.m477.EyeSpy.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.herocraftonline.dthielke.herochat.channels.Channel;
import com.herocraftonline.dthielke.herochat.event.ChannelChatEvent;

import en.m477.EyeSpy.EyeSpy;					//Left in for debug purposes at the moment.
import en.m477.EyeSpy.Logging.Logging;

/**
 * Chat listener, listens to any chatter on the server. Requires HeroChat at the moment.
 * @author M477h3w1012
 */
public class ChatListener implements Listener {
	
	private static String Message;
	
	/**
	 * This goes through the chat event, if it is sent by the player, their name is gathered, otherwise it defaults to 'server'. This event is dependent on HeroChat and returns the channel name, the player name, and the message sent.
	 * @param HeroChat.ChannelChatEvent
	 * @return SQL variables
	 */
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