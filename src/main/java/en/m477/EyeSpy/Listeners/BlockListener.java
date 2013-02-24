package en.m477.EyeSpy.Listeners;

import java.util.List;
import java.util.ListIterator;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.*;
import org.bukkit.event.Listener;

import en.m477.EyeSpy.Logging.Logging;

public class BlockListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockBreak( BlockBreakEvent event ) {
		String name = event.getPlayer().getName();
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		String world = block.getWorld().getName();
		byte broken = 0;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockPlace( BlockPlaceEvent event ) {
		String name = event.getPlayer().getName();
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		String world = block.getWorld().getName();
		byte broken = 1;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockFade( BlockFadeEvent event ) {
		String name = "Natural Causes";
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		String world = block.getWorld().getName();
		byte broken = 0;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockForm( BlockFormEvent event ) {
		String name = "Natural Causes";
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		String world = block.getWorld().getName();
		byte broken = 1;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onLeafDecay( LeavesDecayEvent event ) {
		String name = "Natural Causes";
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		String world = block.getWorld().getName();
		byte broken = 0;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockIgnite( BlockIgniteEvent event ) {
		String name = event.getPlayer().getName();
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		String world = block.getWorld().getName();
		byte broken = 2;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPistonExtend ( BlockPistonExtendEvent event ) {
		List<Block> Extend = event.getBlocks();
		ListIterator<Block> E = Extend.listIterator();
		while (E.hasNext()) {
			int iE = E.nextIndex();
			Block next = Extend.get(iE);
			String name = "Piston";
			int x = next.getX();
			int y = next.getY();
			int z = next.getZ();
			int type = next.getTypeId();
			byte data = next.getData();
			String world = next.getWorld().getName();
			byte broken = 3;
			Logging.addNewBlock(name, type, data, broken, x, y, z, world);
			E.next();
		}
	}
	
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

}
