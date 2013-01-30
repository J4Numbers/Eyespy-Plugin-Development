package en.m477.EyeSpy.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import en.m477.EyeSpy.SpyEvent;
import en.m477.EyeSpy.Logging.Logging;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Chat listener
 * @author M477h3w1012
 *
 */
public class ChatListener {

	private static final String REGEX = "/.*";
	private static String Message;
	
	private static Pattern pattern;
	private static Matcher matcher;

	@SpyEvent
	public static void onPlayerChat( AsyncPlayerChatEvent event ) {
		if ( event.isAsynchronous() ) {
			Player player = event.getPlayer();
			Message = event.getMessage();
			pattern = Pattern.compile(REGEX);
			matcher = pattern.matcher(Message);
			Logging.addNewChat(player, Message);
			if (matcher.matches()) {
			//	Logging.addNewCommand(player, Message);
			}
			else {
				Logging.addNewChat(player, Message);
			}
		}
	}
}