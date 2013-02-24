package en.m477.EyeSpy.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import en.m477.EyeSpy.EyeSpy;
import en.m477.EyeSpy.Util.ArgProcessing;

	/**
	 * Main logging class... logs stuff.
	 * @author M477h3w1012
	 * 
	 */

	/* Notes for creation
	 * TODO Create a connection class
	 * TODO Create a class for adding chest entries
	 */

public class Logging implements Runnable {
	
	public static Connection conn;
	private static String host;
	private static String database;
	public static boolean sql;
	
	/**
	 * Gets the information required for connecting.
	 * @param host Sets the host address of the server
	 * @param database Sets the database to use
	 * @param plugin
	 */
    public Logging(String host, String database, EyeSpy plugin) {
        Logging.host = EyeSpy.host;
        Logging.database = EyeSpy.database;
    }
	
    /**
     * This is called once, upon startup of the server, and performs the checks to make sure that the plugin is ready to go.
     */
	public void startSql() {
		startConnection();
		if (sql) {
		  createTables();
		  }
		
	}
	
	/**
	 * This is called upon startup and logs into the database using the information found in the config file.
	 */
    protected void startConnection() {
    	sql = true;
        String sqlUrl = String.format("jdbc:mysql://%s/%s", host, database);
        
	    String username = EyeSpy.username;
	    String password = EyeSpy.password;
	    
        Properties sqlStr = new Properties();
        sqlStr.put("user", username);
        sqlStr.put("password", password);
        try {
            conn = DriverManager.getConnection(sqlUrl, sqlStr);
        } catch (SQLException e) {
            EyeSpy.printSevere("A MySQL connection could not be made");
            sql = false;
        }
    }
    
    /**
     * This is called whenever the plugin runs, it will systematically run through the database, making sure that all tables exist, if it finds a missing table, it will add it.
     */
    protected void createTables() {
        try {
            //Blocks
            EyeSpy.printInfo("Searching for Blocks table");
            ResultSet rs = conn.getMetaData().getTables(null, null, "blocks", null);
            if (!rs.next()) {
                EyeSpy.printWarning("No 'blocks' table found, attempting to create one...");
                PreparedStatement ps = conn
                		.prepareStatement("CREATE TABLE IF NOT EXISTS `blocks` ( "
                				+ "`spy_id` int unsigned not null auto_increment, "
                				+ "`date` DATETIME not null, "
                				+ "`player_id` mediumint unsigned not null, "
                				+ "`world_id` tinyint not null, "
                				+ "`blockname` mediumint unsigned not null, "
                				+ "`blockdata` tinyint unsigned not null, "
                				+ "`x` mediumint signed not null, "
                				+ "`y` mediumint unsigned not null, "
                				+ "`z` mediumint signed not null, "
                				+ "`place/break` boolean, "
                				+ "primary key (`spy_id`));" );
                ps.executeUpdate();
                ps.close();
                EyeSpy.printWarning("'blocks' table created!");
            } else { 
            	EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            //Chat
            EyeSpy.printInfo("Searching for Chat table");
            rs = conn.getMetaData().getTables(null, null, "chat", null);
            if (!rs.next()) {
                EyeSpy.printWarning("No 'chat' table found, attempting to create one...");
                PreparedStatement ps = conn
                        .prepareStatement("CREATE TABLE IF NOT EXISTS `chat` ( "
                                + "`chat_id` mediumint unsigned not null auto_increment, "
                                + "`player_id` mediumint unsigned not null, "
                                + "`date` DATETIME not null, "
                                + "`ch_id` tinyint unsigned not null, "
                                + "`message` varchar(255) not null, "
                                + "primary key (`chat_id`));" );
                ps.executeUpdate();
                ps.close();
                EyeSpy.printWarning("'chat' table created!");
            } else {
                EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            //Chat Channels
            EyeSpy.printInfo("Searching for Chat Channels table");
            rs = conn.getMetaData().getTables(null, null, "chatchannels", null);
            if (!rs.next()) {
            	EyeSpy.printWarning("No 'chatchannels' table found, attempting to create one...");
            	PreparedStatement ps = conn
            			.prepareStatement("CREATE TABLE IF NOT EXISTS `chatchannels` ( "
            					+ "`ch_id` tinyint unsigned not null auto_increment, "
            					+ "`ch_name` varchar(30) not null, "
            					+ "primary key (`ch_id`));" );
            	ps.executeUpdate();
            	ps.close();
            	EyeSpy.printWarning("'chatchannels' table created!");
            } else {
            	EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            //Chests
            EyeSpy.printInfo("Searching for Chests table");
            rs = conn.getMetaData().getTables(null, null, "chests", null);
            if (!rs.next()) {
            	EyeSpy.printWarning("No 'chests' table found, attempting to create one...");
            	PreparedStatement ps = conn
            			.prepareStatement("CREATE TABLE IF NOT EXISTS `chests` ( "
            					+ "`access_id` mediumint unsigned not null auto_increment, "
            					+ "`date` DATETIME not null, "
            					+ "`player_id` mediumint unsigned not null, "
            					+ "`x` mediumint signed not null, "
            					+ "`y` mediumint unsigned not null, "
            					+ "`z` mediumint signed not null, "
            					+ "`blockchanged` mediumint unsigned not null, "
            					+ "`quantity` mediumint signed not null, "
            					+ "primary key (`access_id`));" );
            	ps.executeUpdate();
            	ps.close();
            	EyeSpy.printWarning("'chest' table created!");
            } else {
            	EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            //Commands
            EyeSpy.printInfo("Searching for Commands table");
            rs = conn.getMetaData().getTables(null, null, "commands", null);
            if (!rs.next()) {
            	EyeSpy.printWarning("No 'command' table found, attempting to create one...");
            	PreparedStatement ps = conn
            			.prepareStatement("CREATE TABLE IF NOT EXISTS `commands` ( "
            					+ "`cmd_id` mediumint unsigned not null auto_increment, "
            					+ "`player_id` mediumint unsigned not null, "
            					+ "`date` DATETIME not null, "
            					+ "`command` varchar(255) not null, "
            					+ "primary key (`cmd_id`));" );
            	ps.executeUpdate();
            	ps.close();
            	EyeSpy.printWarning("'command' table created!");
            } else {
            	EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            //Players
            EyeSpy.printInfo("Searching for players table");
            rs = conn.getMetaData().getTables(null, null, "players", null);
            if (!rs.next()) {
            	EyeSpy.printWarning("No 'players' table found, attempting to create one...");
            	PreparedStatement ps = conn
            			.prepareStatement("CREATE TABLE IF NOT EXISTS `players` ( "
            					+ "`player_id` mediumint unsigned not null auto_increment, "
            					+ "`pl_name` varchar(30) not null, "
            					+ "primary key (`player_id`));" );
            	ps.executeUpdate();
            	ps.close();
            	EyeSpy.printWarning("'players' table created!");
            } else {
            	EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            //World
            EyeSpy.printInfo("Searching for Worlds table");
            rs = conn.getMetaData().getTables(null, null, "world", null);
            if (!rs.next()) {
            	EyeSpy.printWarning("No 'world' table found, attempting to create one...");
            	PreparedStatement ps = conn
            			.prepareStatement("CREATE TABLE IF NOT EXISTS `world` ( "
            					+ "`world_id` tinyint unsigned not null auto_increment, "
            					+ "`wld_name` varchar(30) not null, "
            					+ "primary key (`world_id`));" );
            	ps.executeUpdate();
            	ps.close();
            	EyeSpy.printWarning("'world' table created!");
            } else {
            	EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            //Users
            EyeSpy.printInfo("Searching for Users table");
            rs = conn.getMetaData().getTables(null, null, "users", null);
            if (!rs.next()) {
            	EyeSpy.printWarning("No 'users' table found, attempting to create one...");
            	PreparedStatement ps = conn
            			.prepareStatement("CREATE TABLE IF NOT EXISTS `users` ( "
            					+ "`user_id` mediumint unsigned not null auto_increment, "
            					+ "`username` varchar(30) not null, "
            					+ "`password` varchar(50) not null, "
            					+ "primary key (`user_id`));" );
            	ps.executeUpdate();
            	ps.close();
            	EyeSpy.printWarning("'users' table created!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Thanks to Serubin for his use of this maintainConnection class to stop the database from timing out and ending the connection needed to run smoothly.
     * @author Serubin323, Solomon Rubin
     */
    public static void maintainConnection() {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT count(*) FROM `chat` limit 1;");
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        EyeSpy.self.log.info("EyeSpy has checked in with database");
    }
    
    /**
     * Adds the new block events found in the en.m477.EyeSpy.Listeners.BlockListener class. Each one fulfils the requirements for a standard SQL string.
     * @param name The name of the entity that triggered the block change
     * @param type The block that was changed, in ID form. This will be translated within the website
     * @param data Any additional data that the block possesses, such as direction
     * @param broken The state of the block, 0 is broken, 1 is placed, 2 is ignited, 3 is moved
     * @param x X Co-ordinate of the block
     * @param y Y Co-ordinate of the block
     * @param z Z Co-ordinate of the block
     * @param world The world that the block resides in
     * @throws e.printStackTrace should the SQL string fail
     */
    public static void addNewBlock(String name, int type, byte data, byte broken, int x, int y, int z, String world) {
    	try {
    		EyeSpy.printInfo("Block Hit!");
    		PreparedStatement ps = conn
    				.prepareStatement("INSERT INTO `blocks` (`date`, `player_id`, `world_id`, `blockname`, `blockdata`, "
    						+ "`x`, `y`, `z`, `place/break`) VALUES ( '"
    						+ ArgProcessing.getDateTime() + "', '"
    						+ playerExists(name) + "', '"
    						+ worldExists(world) + "', '"
    						+ type + "', '"
    						+ data + "', '"
    						+ x + "', '"
    						+ y + "', '"
    						+ z + "', '"
    						+ broken + "'); ");
    		ps.executeUpdate();
    		ps.close();
    		EyeSpy.printInfo("Block Added!");
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    /**
     * Adds any chatter that occurs on the server into the database. This requires that the server is running HeroChat
     * @param name The name of the player sending the chatter
     * @param ch_name The name of the channel the chatter is occuring within
     * @param Message The actual chatter
     * @throws e.printStackTrace If the command fails
     */
    public static void addNewChat(String name, String ch_name, String Message) {
    	try {
    		EyeSpy.printInfo("Chat Started");
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `chat` (`player_id`, `ch_id`, `date` , `message`) VALUES ('"
						+ playerExists(name) + "', '"
						+ channelExists(ch_name) + "', '"
						+ ArgProcessing.getDateTime() + "', '"
						+ Message + "');");
			ps.executeUpdate();
			ps.close();
			EyeSpy.printInfo("Chat Successful!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**
     * Adds any commands used on the server into the database.
     * @param name This is the name of the entity sending the command
     * @param Message This is the command itself
     * @throws e.printStackTrace If the command should fail
     */
    public static void addNewCommand(String name, String Message) {
    	try {
    		EyeSpy.printInfo("Command Started");
    		PreparedStatement ps = conn
    				.prepareStatement("INSERT INTO `commands` (`player_id`, `date`, `command`) VALUES ( '"
    						+ playerExists(name) + "', '"
    						+ ArgProcessing.getDateTime() + "', '"
    						+ Message + "');");
    		ps.executeUpdate();
    		ps.close();
    		EyeSpy.printInfo("Command Log Successful!");
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    /**
     * Stub for keeping the connection open, will add a timer to this soon.
     */
	public void run() {
		maintainConnection();
	}
	
	/**
	 * Checks within the chatchannels database for any existing channels of that name. If it doesn't find it, it will add it to the table, and return the id no matter what
	 * @param ch_name The name of the channel that is being checked
	 * @return chId This is the ID of the channel in the chatchannel table, passed back to the chat table
	 * @throws e.printStackTrace If the command should fail
	 */
	public static int channelExists(String ch_name) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		int chId = 0;
		try {
			ps = conn.prepareStatement("SELECT `ch_name` FROM `chatchannels` WHERE (ch_name = '" + ch_name + "');" );
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement("INSERT INTO `chatchannels` (`ch_name`) VALUES ( '" + ch_name + "' );" );
				ps.executeUpdate();
				ps.close();
				EyeSpy.printInfo(ch_name + " added to the channels table");
			}
			ps = conn.prepareStatement("SELECT * FROM `chatchannels` WHERE (ch_name = '" + ch_name + "');" );
			rs = ps.executeQuery();
			rs.first();
			chId = rs.getInt("ch_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chId;
	}
	
	/**
	 * Checks within the players table for any existing players that match the parameter. If there are no matching names, the player will be added and their ID returned no matter what.
	 * @param name This is the players name which is being checked
	 * @return plId This is the ID of the player that was checked
	 * @throws e.printStackTrace If the command should fail
	 */
	public static int playerExists(String name) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		int plId = 0;
		try {
			ps = conn.prepareStatement("SELECT `pl_name` FROM `players` WHERE (pl_name = '" + name + "');" );
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement("INSERT INTO `players` (`pl_name`) VALUES ( '" + name + "');" );
				ps.executeUpdate();
				ps.close();
				EyeSpy.printInfo(name + " added to the players table");
			}
			ps = conn.prepareStatement("SELECT * FROM `players` WHERE (pl_name = '" + name + "');" );
			rs = ps.executeQuery();
			rs.first();
			plId = rs.getInt("player_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plId;
	}
	
	/**
	 * This checks the worlds table for any existing worlds with that name. If no worlds are found which match the parameter, a new row will be inserted into the table, the ID will be returned no matter what.
	 * @param world This is the name of the world that is to be checked
	 * @return wlId This is the ID of the world that was checked
	 * @throws e.printStackTrace If the command should fail
	 */
	public static int worldExists(String world) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		int wlId = 0;
		try {
			ps = conn.prepareStatement("SELECT `wld_name` FROM `world` WHERE (wld_name = '" + world + "');" );
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement("INSERT INTO `world` (`wld_name`) VALUES ( '" + world + "' );" );
				ps.executeUpdate();
				ps.close();
				EyeSpy.printInfo(world + " added to the worlds table");
			}
			ps = conn.prepareStatement("SELECT * FROM `world` WHERE (wld_name = '" + world + "');" );
			rs = ps.executeQuery();
			rs.first();
			wlId = rs.getInt("world_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wlId;
	}
}
