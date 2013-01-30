package en.m477.EyeSpy.Logging;

import java.sql.*;
import java.util.Properties;

import org.bukkit.entity.Player;

import en.m477.EyeSpy.EyeSpy;

	/**
	 * 
	 * @author M477h3w1012
	 *
	 */

	/* Notes for creation
	 * TODO Create a connection class
	 * TODO Create a class for adding chat entries
	 * TODO Create a class for adding block entries
	 * TODO Create a class for adding chest entries
	 * TODO Create a procedure for sorting between chat and commands
	 */

public class Logging {
	
	public static Connection conn;
	
	private static String host;
	private static String username;
	private static String password;
	private static String database;
	
    public Logging(String host, String username, String password,
            String database, EyeSpy plugin) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;
    }
	
	public static void startSql() {
		StartConnection();
		CreateTables();
	}
	
    protected static void StartConnection() {
        String sqlUrl = String.format("jdbc:mysql://%s/%s", host, database);

        Properties sqlStr = new Properties();
        sqlStr.put("user", username);
        sqlStr.put("password", password);
        try {
            conn = DriverManager.getConnection(sqlUrl, sqlStr);
        } catch (SQLException e) {
            EyeSpy.printSevere("A MySQL connection could not be made");
            e.printStackTrace();
        }
    }
    
    protected static void CreateTables() {
        try {
            EyeSpy.printInfo("Searching for Chat table");
            ResultSet rs = conn.getMetaData().getTables(null, null, "chat", null);
            if (!rs.next()) {
                EyeSpy.printWarning("No 'chat' table found, Attempting to create one...");
                PreparedStatement ps = conn
                        .prepareStatement("CREATE TABLE IF NOT EXISTS `chat` ( "
                                + "`id` mediumint unsigned not null auto_increment, "
                                + "`player_id` mediumint unsigned not null, "
                                + "`date` DATETIME not null, "
                                + "`message` varchar(255) not null, "
                                + "primary key (`id`));");
                ps.executeUpdate();
                ps.close();
                EyeSpy.printWarning("'chat' table created!");
            } else {
                EyeSpy.printInfo("Table found");
            }
            rs.close();
            
            EyeSpy.printInfo("Searching for Commands table");
            rs = conn.getMetaData().getTables(null, null, "commands", null);
            if (!rs.next()) {
            	EyeSpy.printWarning("No 'command' table found, attempting to create one...");
            	PreparedStatement ps = conn
            			.prepareStatement("CREATE TABLE IF NOT EXISTS `commands` ( "
            					+ "`id` mediumint unsigned not null auto_increment"")
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void maintainConnection() {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT count(*) FROM bans limit 1;");
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        EyeSpy.self.log.info("EyeSpy has checked in with database");
    }
    
    public static void addNewChat(Player player, String Message) {
    	try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `chat` VALUES ( "
						+ player + ", "
						+ Message + ");");
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static void addNewCommand(Player player, String Message) {
    	try {
    		PreparedStatement ps = conn
    				.prepareStatement("INSERT INTO `commands` VALUES ( "
    						+ player + ", "
    						+ Message + ");");
    		ps.executeUpdate();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
}
