package kshrd.raw;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IO {
	
	public static boolean start = true;

	/**
	 * use to display message in a line
	 * @param message : display message
	 */
	public static void print(String message) {
		System.out.print(message);
	}
	
	/**
	 * use to display message in many line
	 * @param message : message
	 */
	public static void println(String message) {
		System.out.println(message);
	}
	
	/**
	 * use to read String from keyboard
	 * @param message : display message
	 * @return String value
	 */
	@SuppressWarnings("resource")
	public static String readString(String message) {
		print(message);
		return new Scanner(System.in).next();
	}
	
	/**
	 * use to read integer from keyboard
	 * @param message : display message
	 * @return integer value
	 */
	public static int readInt(String message) {
		return Integer.parseInt(readString(message));
	}
	
	/**
	 * use to read long value from keyboard
	 * @param message : display message
	 * @return long value 
	 */
	public static long readLong(String message) { 
		return Long.parseLong(message);
	}
	
	/**
	 * use to read integer from keyborad in [min , max]
	 * @param message : display message
	 * @param min : min value
	 * @param max : max value
	 * @return min<=integer value<=max
	 */
	public static int readInt(String message, int min, int max) {
		int number=0;
		do {
			number = readInt(message);
		}	while(number<min || number>max);
		return number;
	}
	
	/**
	 * use to read double from keyboard
	 * @param message : display message
	 * @return double value
	 */
	public static double readDouble(String message) {
		return Double.parseDouble(readString(message));
	}
	
	/**
	 * use to read a character from keyboard
	 * @param message : display message
	 * @return character value
	 */
	public static char readChar(String message) {
		return readString(message).charAt(0);
	}
	
	
	/**
	 * use to press enter key to continue
	 */
	public static void pressEnterContinue() {
		IO.print("Press Enter to continue ...");
		try{
			System.in.read();
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
	}
	
	/**
	 * get current date from machine
	 * @return	current date
	 */
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date).toString();
	}
	
	
	
}
