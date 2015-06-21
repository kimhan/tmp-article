package kshrd.raw;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Article implements Serializable, Comparable<Article> {
	
	/**
	 * id : record id as long
	 * title : article title as String
	 * author : post author as String
	 * date : post date as String
	 * detail : content post as String
	 */
	private long id;  
	private String title;
	private String author;
	private String date;
	private String detail;

	/**
	 * no parameter constructor
	 */
	public Article() {
		this(0,"Noname","Other");
	}
	
	/**
	 * one parameter constructor
	 * @param id
	 */
	public Article(long id) {
		this(id, "Noname", "NoAuthor");
	}
	
	/**
	 * three parameter constructor
	 * @param id
	 * @param title
	 * @param author
	 */
	public Article(long id,String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.date = IO.getDate();
//		setId(id);
//		setTitle(title);
//		setAuthor(author);
//		setDate(IO.getDate());
	}
	
	/**
	 * four parameter constructor
	 * @param id
	 * @param title
	 * @param author
	 * @param content
	 */
	public Article(long id, String title, String author, String content) {
		this(id,title,author);
		this.detail = content;
//		setDetail(content); 
	}
	
	/**
	 * five parameter constructor
	 * @param id
	 * @param title
	 * @param author
	 * @param date
	 * @param content
	 */
	public Article(long id, String title, String author, String date, String content) {
		this(id, title, author, content);
//		setDate(date);
	}
	
	/**
	 * display record title
	 * @param col
	 * @return
	 */
	public static String showTitle(int col) {
		String array[] = new String[col];
		switch(col) {
			case 2:
				array[0] = "ID";
				array[1] = "TITLE";
				break;
			case 3:
				array[0] = "ID";
				array[1] = "TITLE";
				array[2] = "AUTHOR";
				break;
			case 4:
				array[0] = "ID";
				array[1] = "TITLE";
				array[2] = "AUTHOR";
				array[3] = "DATE";
				break;
			
		}
		if((col%2) != 0)
			return Display.showEqual(98, "=") + "\n|" + Display.showTitle(col, array) + "\n" + Display.showEqual(98, "=");
		else
			return Display.showEqual(99, "=") + "\n|" + Display.showTitle(col, array) + "\n" + Display.showEqual(99, "=");
	}
	
	/**
	 * display record value
	 * @param col
	 * @return
	 */
	public String showRecord(int col) {
		String array[] = new String[col];
		switch(col) {
			case 2:
				array[0] = getId() + "";
				array[1] = getTitle();
				break;
			case 3:
				array[0] = getId() + "";
				array[1] = getTitle();
				array[2] = getAuthor();
				break;
			case 4:
				array[0] = getId() + "";
				array[1] = getTitle();
				array[2] = getAuthor();
				array[3] = getDate();
				break;
		}
		return Display.showInformation(col, array);
	}
	
	/**
	 * display page number and total record
	 * @param string1
	 * @param string2
	 * @return
	 */
	public static String showPage(String string1, String string2) {
		String arr[] = {string1, string2};
		return Display.showEqual(99, "=") + "\n|" + Display.showTitle(2, arr) + "\n" + Display.showEqual(99, "=");
	}
	
	/**
	 * convert a record to string
	 */
	public String toString() {
		return getId() + "|" + getTitle() + "|" + getAuthor() + "|" + getDate() + "|" + getDetail() + ";\n";
	}

	/**
	 * get id of a record
	 * @return id
	 */
	public long getId() {
		return id;
	} 

	/**
	 * set a value for id
	 * @param id : new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get title value
	 * @return title value
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set new title value
	 * @param title : new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
 
	/**
	 * get old author value
	 * @return author value
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * set new author value
	 * @param author : new author value
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * get old date value
	 * @return date value
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * set new date value
	 * @param date : new date value
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * get old content value
	 * @return content value
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * set new content value
	 * @param detail : new content value
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * compare id for arrays.sort()
	 */
	@Override
	public int compareTo(Article o) {
		// TODO Auto-generated method stub
		return (int) (getId()-o.getId());
	}
	

}
