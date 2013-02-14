package en.m477.EyeSpy.Util;

import java.sql.Timestamp;

public class ArgProcessing {

    public static Timestamp getDateTime() {
        java.sql.Timestamp date;
        java.util.Date today = new java.util.Date();
        date = new java.sql.Timestamp(today.getTime());
        return date;
    }
	
}
