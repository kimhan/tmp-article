package kshrd.miniproject;

import java.io.*;
import java.util.*;

import kshrd.raw.Article;

public class FileMethod {

	/**
	 * Use BufferedReader to read data from a file Use BufferedWriter to write
	 * data into a file final fileName is use for specific path that store the
	 * file
	 */
	private final static String fileName;

	// static BufferedReader in;
	// static BufferedWriter out;

	static ObjectInputStream in;
	static ObjectOutputStream out;

	/**
	 * Provide initialization for static member
	 */
	static {
		fileName = FileMethod.class.getResource("").getPath() + "article.txt";
		// try {
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Use Buffered Reader including FileReader to read all data from a file
	 * line by line
	 * 
	 * @return all the data that is read from the specific fileName member above
	 */
	// public static String readDataFromFile() {
	// String tmp = "", line;
	// try {
	// in = new BufferedReader(new FileReader(fileName));
	// while((line = in.readLine()) != null) {
	// tmp += line;
	// }
	// in.close();
	// } catch(FileNotFoundException e) {
	// e.printStackTrace();
	// } catch(IOException e) {
	// e.printStackTrace();
	// }
	// return tmp;
	// }

	/**
	 * Transfer read data from a file to ArrayList to manipulate data
	 * 
	 * @return ArrayList that contain all article that is read from the file
	 */
	// public static ArrayList<Article> fileToArrayList() {
	// ArrayList<Article> tmp = new ArrayList<Article>();
	// StringTokenizer st = new StringTokenizer(readDataFromFile(), ";");
	// while(st.hasMoreTokens()) {
	// StringTokenizer st1 = new StringTokenizer(st.nextToken(), "|");
	// int id = Integer.parseInt(st1.nextToken());
	// String title = st1.nextToken();
	// String author = st1.nextToken();
	// String date = st1.nextToken();
	// String detail = st1.nextToken();
	// Article art = new Article(id, title, author, date, detail);
	// tmp.add(art);
	// }
	// return tmp;
	// }

	/**
	 * Write article into a file for Permanently storage, all new article will
	 * append to the old data
	 * 
	 * @param data
	 *            : an article or all article provided by user
	 */
	// public static void writeDataIntoFile(String data) {
	// try {
	// out = new BufferedWriter(new FileWriter(fileName, true));
	// out.write(data);
	// out.close();
	// } catch(IOException e) {
	// e.printStackTrace();
	// }
	// }

	@SuppressWarnings("unchecked")
	public static ArrayList<Article> readDataFromFile() {
		ArrayList<Article> tmp = new ArrayList<Article>();
		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			tmp = (ArrayList<Article>) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public static void writeDataIntoFile(ArrayList<Article> tmp) {
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeDataIntoFile(Article tmp) {
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName, true));
			out.writeObject(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
