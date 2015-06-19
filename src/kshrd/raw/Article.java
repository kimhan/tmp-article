package kshrd.raw;

import java.io.Serializable;

public class Article implements Serializable, Comparable<Article> {
	private int id;
	private String title;
	private String author;
	private String date;
	private String detail;

	public Article() {
		this(0,"Noname","Other");
	}
	
	public Article(int id) {
		this(id, "Noname", "NoAuthor");
	}
	
	public Article(int id,String title, String author) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setDate(IO.date);
	}
	
	public Article(int id, String title, String author, String content) {
		this(id,title,author);
		setDetail(content); 
	}
	
	public Article(int id, String title, String author, String date, String content) {
		this(id, title, author, content);
		setDate(date);
	}
	
	public static String showTitle(int col) {
		String array[] = null;
		switch(col) {
			case 2:
				array = new String[col];
				array[0] = "ID";
				array[1] = "TITLE";
				break;
			case 3:
				array = new String[col];
				array[0] = "ID";
				array[1] = "TITLE";
				array[2] = "AUTHOR";
				break;
			case 4:
				array = new String[col];
				array[0] = "ID";
				array[1] = "TITLE";
				array[2] = "AUTHOR";
				array[3] = "DATE";
				break;
		}
		if((col%2)!=0)
			return Display.showEqual(98, "=") + "\n|" + Display.showTitle(col, array) + "\n" + Display.showEqual(98, "=");
		else
			return Display.showEqual(99, "=") + "\n|" + Display.showTitle(col, array) + "\n" + Display.showEqual(99, "=");
	}
	
	public String showRecord(int col) {
		String array[]=null;
		switch(col) {
			case 2:
				array = new String[col];
				array[0] = getId() + "";
				array[1] = getTitle();
				break;
			case 3:
				array = new String[col];
				array[0] = getId() + "";
				array[1] = getTitle();
				array[2] = getAuthor();
				break;
			case 4:
				array = new String[col];
				array[0] = getId() + "";
				array[1] = getTitle();
				array[2] = getAuthor();
				array[3] = getDate();
				break;
		}
		return Display.showInformation(col, array);
	}
	
	public static String showPage(String string1, String string2) {
		String arr[] = {string1, string2};
		return Display.showEqual(99, "=") + "\n|" + Display.showTitle(2, arr) + "\n" + Display.showEqual(99, "=");
	}
	
	public String toString() {
		return getId() + "|" + getTitle() + "|" + getAuthor() + "|" + getDate() + "|" + getDetail() + ";\n";
	}

	public int getId() {
		return id;
	} 

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public int compareTo(Article o) {
		// TODO Auto-generated method stub
		return (int) (getId()-o.getId());
	}
	

}
