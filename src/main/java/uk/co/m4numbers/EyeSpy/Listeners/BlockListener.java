package uk.co.m4numbers.EyeSpy.Listeners;

import java.util.List;
import java.util.ListIterator;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.*;
import org.bukkit.event.Listener;

import uk.co.m4numbers.EyeSpy.Logging.Logging;

/**
 * The main Block Listener class, everything in here is an action upon a block, whether it be movement, breaking, placing or burning... it's here.
 * @author Matthew Ball
 *
 *
 */

public class BlockListener implements Listener {
	
	public static void BlockInfo(Block block, String name, byte broken) {
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		String world = block.getWorld().getName();
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	/**
	 * Block Break listener. Once a block is broken, it sends the variables to the uk.co.m4numbers.EyeSpy.Logging.Logging class to be placed into the SQL string, 0 means break.
	 * @param BlockBreakEvent
	 * @return SQL variables
	 */
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockBreak( BlockBreakEvent event ) {
		String name = event.getPlayer().getName();
		Block block = event.getBlock();
		byte broken = 0;
		BlockInfo(block, name, broken);
	}
	
	/**
	 * Block Place listener. Once a block is broken, it sends the variables to the uk.co.m4numbers.EyeSpy.Logging.Logging class to be placed into the SQL string, 1 means place.
	 * @param BlockPlaceEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockPlace( BlockPlaceEvent event ) {
		String name = event.getPlayer().getName();
		Block block = event.getBlock();
		byte broken = 1;
		BlockInfo(block, name, broken);
	}
	
	/**
	 * Block Fade listener. This applies for burnt blocks as well somehow... doesn't matter. Once a block fades due to natural causes or something, the SQL variables will be sent to the logging class. Removal is 0.
	 * @param BlockFadeEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockFade( BlockFadeEvent event ) {
		String name = "Natural Causes";
		Block block = event.getBlock();
		byte broken = 0;
		BlockInfo(block, name, broken);
	}
	
	/**
	 * Block Form listener, I suspect this is the listener for when lava meets water and dirt becomes grass or vice versa... or something. Again, the SQL variables are passed onto the logging class. Change/forming is 1.
	 * @param BlockFormEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockForm( BlockFormEvent event ) {
		String name = "Natural Causes";
		Block block = event.getBlock();
		byte broken = 1;
		BlockInfo(block, name, broken);
	}
	
	/**
	 * Strangely enough... this is triggered when leaves decay. The variables are sent to the logging class to be put into an SQL string. Decay is 1.
	 * @param LeavesDecayEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onLeafDecay( LeavesDecayEvent event ) {
		String name = "Natural Causes";
		Block block = event.getBlock();
		byte broken = 0;
		BlockInfo(block, name, broken);
	}
	
	/**
	 * Triggered when a block is lit by someone/something, the SQL variables are sent to the logging class for processing. 2 is Ignition.
	 * @param BlockIgniteEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockIgnite( BlockIgniteEvent event ) {
		String name = event.getPlayer().getName();
		Block block = event.getBlock();
		byte broken = 2;
		BlockInfo(block, name, broken);
	}
	
	/**
	 * Triggers when a piston is extended. This is different in that it will send a separate set of variables for each block it affects (hopefully) to the logging class to be inserted into the SQL string. 3 is movement.
	 * @param BlockPistonExtendEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPistonExtend ( BlockPistonExtendEvent event ) {
		List<Block> Extend = event.getBlocks();
		ListIterator<Block> E = Extend.listIterator();
		while (E.hasNext()) {
			int iE = E.nextIndex();
			Block block = Extend.get(iE);
			String name = "Piston";
			byte broken = 3;
			BlockInfo(block, name, broken);
			E.next();
		}
	}
	
	/**
	 * Fired when a piston retracts. If the piston is sticky and the block it pulls is not air, then it will fire an event of the block it moves, sending the result to the logging class. 3 is movement.
	 * @param BlockPistonRetractEvent
	 * @return SQL variables
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPistonRetraction ( BlockPistonRetractEvent event ) {
		if ( event.isSticky() ) {
			if ( event.getBlock().getTypeId() != 0 ) {
				String name = "Piston";
				Location loc = event.getRetractLocation();
				int x = loc.getBlockX();
				int y = loc.getBlockY();
				int z = loc.getBlockZ();
				int type = event.getBlock().getTypeId();
				byte data = event.getBlock().getData();
				String world = loc.getWorld().getName();
				byte broken = 3;
				Logging.addNewBlock(name, type, data, broken, x, y, z, world);
			}
		}
	}
	
	/*
	/**
	 * Fired when gravity/other physics affect a block. /Should/ work for multiple gravitised blocks, but testing will have to confirm. Sends the variables to the logging class. 3 is movement.
	 * @param BlockPhysicsEvent
	 * @return SQL variables
	 */
	/*
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPhysicsEvent ( BlockPhysicsEvent event ) {
		Block block = event.getBlock();
		String name = "Physics";
		byte broken = 3;
		BlockInfo(block, name, broken);
	}
	*/
}
