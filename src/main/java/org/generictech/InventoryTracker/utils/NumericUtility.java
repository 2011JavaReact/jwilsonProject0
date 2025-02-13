package org.generictech.InventoryTracker.utils;

/**
 * Class with utility methods dealing with numbers. 
 * Specifically seeing if strings are numbers
 * @author Jaden Wilson
 *
 */
public class NumericUtility {

	/**
	 * Utility method to check if a string is an integer. 
	 * @param in
	 * @return boolean stating if string represents an integer or not
	 */
	public static boolean isInt(String in) {
		try {
			Integer.parseInt(in);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
