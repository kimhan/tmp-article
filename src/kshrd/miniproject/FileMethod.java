package kshrd.miniproject;

import java.io.*;
import java.util.*;

import kshrd.raw.Article;

public class FileMethod {

	/**
	 * Use ObjectInputStream  to read data from a file 
	 * Use ObjectOutputStream to write
	 * data into a file final fileName is use for specific path that store the
	 * file permanently 
	 * fileName : file location that store all record
	 * in : object for read file
	 * out : object for write file
	 */
	private final static String fileName;
	static ObjectInputStream in;
	static ObjectOutputStream out;

	/**
	 * Provide initialization for static member
	 */
	static {
		fileName = FileMethod.class.getResource("").getPath() + "article.txt";
	}

	/**
	 * Use ObjectInputStream including FileInputStream to read all data from a file
	 * for the whole collection
	 * @return all the data that is read from the specific fileName member above
	 */
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

	/**
	 * write a collection into a specific fileName
	 * use ObjectOutputStream including FileOutputStream to do the operation
	 * @param tmp : collection for writing into file
	 */
	public static void writeDataIntoFile(ArrayList<Article> tmp) {
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write an article into a file for Permanently storage, all new article will
	 * append to the old data
	 * @param data : an article or all article provided by user
	 */
	public static void writeDataIntoFile(Article tmp) {
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName, true));
			out.writeObject(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
