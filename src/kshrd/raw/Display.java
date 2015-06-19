package kshrd.raw;


public class Display {
	static int numPrint = 94;
	
	public static void main(String[] args) {
		new Display().showWelcome();
	}

	/**
	 * use to display welcome screen
	 */
	public static void showWelcome(){
		System.out.println("");
		System.out.println("             ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗");
		System.out.println("             ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝");
		System.out.println("             ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗  ");
		System.out.println("             ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝  ");
		System.out.println("              ███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗");
		System.out.println("              ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝");
		System.out.println("");
	}
	
	/**
	 * display header view
	 */
	public static void generateHeaderView () {
		String title = "Korea Software HRD Center";
		String welcome = "Welcome to Student Management System";
		String group = "Group 2 of Pnom Penh class.";
		String memberl1 = "1. But Mathearo    2. Houn Kimhan";
		String memberl2 = "3. Torn Longdy   4. Pann Vandet   5. Khoeurn Rotha";
		
		int sign = (numPrint-title.length())/2;
		int space = (numPrint-welcome.length())/2;
		int space1 = (numPrint-group.length())/2;
		int space2 = (numPrint-memberl1.length())/2;
		int space3 = (numPrint-memberl2.length())/2;

		IO.println("[+]" + showEqual("=", numPrint + 1) + "[+]");
		IO.println("[+]" + showEqual(" ", numPrint + 1) + "[+]");
		IO.println("[+]" + showEqual("_", sign + 1) + title + showEqual("_", sign + 1) + "[+]");
		IO.println("[+]" + showEqual(" ", numPrint + 1) + "[+]");
		IO.println("[+]" + showEqual("=", numPrint + 1) + "[+]");
		
		IO.println(showEqual(" ", space) + welcome);
		IO.println(showEqual(" ", space1) + group);
		IO.println(showEqual(" ", space2) + memberl1);
		IO.println(showEqual(" ", space3) + memberl2);
		
	}
	/**
	 * use to display the information of the record depending on argument like number of column and array string
	 * @param space : number of column
	 * @param array : array of string
	 * @return
	 */
	public static String showInformation(int space, String array[]) {
		int col = 100/space;
		String data = "|";
		for(String tmp:array) {
			data += showLimit(col, tmp);
		}
		if((100 % space)!=0)
			return data += "\n" + showEqual(98, "-");
		else 
			return data += "\n" + showEqual(99, "-");
	}
	
	/**
	 * use to display title of record depending of number of column and array string
	 * @param length : number of column
	 * @param arr : array string
	 * @return
	 */
	public static String showTitle(int length, String arr[]) {
		int col = 100/length;
		String data = "";
		for(String tmp:arr) {
			data += showLimit(col, displayCenter(col, tmp));//showLimit(col, tmp);
		}
		return data;
	}
	
	/**
	 * show any charactor in a row depending on argument length of display and charactor to display
	 * @param equal : length to display
	 * @param s : display charactor
	 * @return
	 */
	public static String showEqual(int equal, String s) {
		return "+" + showEqual(s, equal) + "+";
	}
	
	/**
	 * show any charactor in a row depending on argument length of display and charactor to display
	 * @param s : display charactor
	 * @param equal : length to display
	 * @return
	 */
	public static String showEqual(String s, int equal) {
		String data = "";
		for(int i=0;i<equal;i++)
			data += s;
		return data;
	}
	
	/**
	 * 
	 * @param length
	 * @param message
	 */
	public static void displayRight(int length, String message) {
		
	}
	
	/**
	 * use to display text in center align
	 * @param length : display length
	 * @param message : display message
	 * @return
	 */
	public static String displayCenter(int length, String message) {
		return whiteSpace(spaceLength(length, message.length())-2) + message + whiteSpace(spaceLength(length, message.length())-2);
	}
	
	/**
	 * use to display message with limit width
	 * @param limit : limit length
	 * @param data : message
	 * @return
	 */
	public static String showLimit(int limit, String data) {
		if(data.length() > limit) 
			return data.substring(0, limit - 6) + " ... |";
		else
			return data + whiteSpace(limit - data.length() - 2) + " |";
	}
	
	/**
	 * use to show menu
	 * @param menu : top message
	 * @param page : middle message
	 * @param other : last message
	 */
	public static void showMenu(String menu, String page, String other) {
		int spaceMenu = spaceLength(100, menu.length());
		int spacePage = spaceLength(100, page.length());
		int otherSpace = spaceLength(100, other.length());
		IO.println("\n " + underScore(spaceMenu) + menu + underScore(spaceMenu-1) + "\n|" + whiteSpace(spacePage) + page + whiteSpace(spacePage) + "|\n|" + whiteSpace(otherSpace) + other + whiteSpace(otherSpace) + "|");
		IO.println("|" + underScore(99) + "|\n"); 
	}
	
	/**
	 * use to display space
	 * @param lengthMax : width for display
	 * @param lengthMin : width message
	 * @return
	 */
	public static int spaceLength(int lengthMax, int lengthMin) {
		return (lengthMax - lengthMin) / 2;
	}
	
	/**
	 * use to display whitespaec
	 * @param spaceAmount : length of whitespace
	 * @return
	 */
	public static String whiteSpace(int spaceAmount) {
		String space = "";
		for(int i=0;i<spaceAmount;i++) {
			space += " ";
		}
		return space;
	}
	public static String underScore(int underScoreAmount) {
		String space = "";
		for(int i=0;i<underScoreAmount;i++) {
			space += "_";
		}
		return space;
	}
	
}
