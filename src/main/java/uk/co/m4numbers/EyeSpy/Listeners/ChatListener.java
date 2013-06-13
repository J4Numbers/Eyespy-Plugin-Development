package uk.co.m4numbers.EyeSpy.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.dthielke.herochat.Channel;
import com.dthielke.herochat.ChannelChatEvent;

import uk.co.m4numbers.EyeSpy.EyeSpy;
import uk.co.m4numbers.EyeSpy.Logging.Logging;

/**
 * Chat listener, listens to any chatter on the server. Requires HeroChat at the moment.
 * @author M477h3w1012
 */
public class ChatListener implements Listener {
	
	private static String Message;
	
	/**
	 * This goes through the chat event, as provided by HeroChat version 5 and returns the channel name, the player name, and the message sent.
	 * @param HeroChat.ChannelChatEvent : This is the event which the whole listener hinges on.
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerChat( ChannelChatEvent event ) {
		String name = event.getSender().getName();
		Channel channel = event.getChannel();
		String ch_name = channel.getName();
		Message = event.getMessage();
		Logging.addNewChat(name, ch_name, EyeSpy.Server, Message);
	}
	
}