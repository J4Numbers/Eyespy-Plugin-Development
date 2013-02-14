package en.m477.EyeSpy.Listeners;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.*;
import org.bukkit.event.Listener;

import en.m477.EyeSpy.Logging.Logging;

public class BlockListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockBreak( BlockBreakEvent event ) {
		Player player = event.getPlayer();
		String name = player.getName();
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		World world1 = block.getWorld();
		String world = world1.getName();
		byte broken = 0;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onBlockPlace( BlockPlaceEvent event ) {
		Player player = event.getPlayer();
		String name = player.getName();
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		int type = block.getTypeId();
		byte data = block.getData();
		World world1 = block.getWorld();
		String world = world1.getName();
		byte broken = 1;
		Logging.addNewBlock(name, type, data, broken, x, y, z, world);
	}

}
