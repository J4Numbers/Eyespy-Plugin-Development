package uk.co.m4numbers.EyeSpy.Util;

public class ArgProcessing {

	/**
	 * Return the epoch date in seconds
	 * @return date : Return the date in seconds from the epoch
	 */
    public static Long getDateTime() {
    	long date = System.currentTimeMillis()/1000;
        return date;
    }

}