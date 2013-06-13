package uk.co.m4numbers.EyeSpy.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.ensifera.animosity.craftirc.Minebot;

import com.dthielke.herochat.Channel;
import com.dthielke.herochat.ChannelChatEvent;

import uk.co.m4numbers.EyeSpy.EyeSpy;
//import uk.co.m4numbers.EyeSpy.EyeSpy;					//Left in for debug purposes at the moment.
import uk.co.m4numbers.EyeSpy.Logging.Logging;

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
		String name = event.getSender().getName();
		Channel channel = event.getChannel();
		String ch_name = channel.getName();
		Message = event.getMessage();
		Logging.addNewChat(name, ch_name, EyeSpy.Server, Message);
	}
	
	//@EventHandler(priority = EventPriority.MONITOR)
	//public static void onPlayerTalk( AsyncPlayerChatEvent event ) {
	//	Player Player = event.getPlayer();
	//	String plName = Player.getName();
	//	String Message = event.getMessage();
	//	
	//	EyeSpy.printInfo(plName + " : " + Message);
	//}
	
}