package kshrd.miniproject;

import java.util.ArrayList;
import java.util.Scanner;

import kshrd.raw.Article;
import kshrd.raw.Display;
import kshrd.raw.IO;

public class Manupulate {
	
	static Pagination p;
	
	/**
	 * read a record from arraylist according to the read id
	 * the id should be relevance to the one in the array
	 * if there is no id it will load a message to inform that there is no such a record
	 * anyway the wanted record will be shown.
	 */
	public static void performRead() {
		int search = Manupulate.searchById(IO.readInt("Read ID: "));
		if(Manupulate.isExist(search))  {
			Article a = Run.article.get(search);
			IO.println("ID: " + a.getId());
			IO.println("Title: " + a.getTitle());
			IO.println("Author: " + a.getAuthor());
			IO.println("Content:");
			IO.println(a.getDetail());
		}
		else 
			IO.println("No record with this id !!!");
		IO.pressEnterContinue();
	}
	
	/**
	 * write a record into a file
	 * it also was add to the array also the file unless y is pressed 
	 */
	public static void performWrite() {
		String title = IO.readString("Add Title: ");
		String author = IO.readString("Add Author: ");

	    StringBuffer sb= new StringBuffer();
	    System.out.println("Plz enter content : ");
	    System.out.println("*Note(Press Enter + '.' + Enter to Finish) "+" => ");
	     
	    String content = "";
	    Scanner input = new Scanner(System.in);
	    while (true) {
	    	content = input.nextLine();
	 		if(content!=null && content.trim().length()==1 && content.trim().charAt(0)=='.') break;
	 		if(content == null) content="";
	 		sb.append(content+"\n");
	    }  
	    if(IO.readChar("want to save?[y/n]") == 'y')
	    	Run.article.add(new Article((Run.article.get(Run.article.size()-1).getId() + 1), title, author, content));
	}
	
	/**
	 * delete one record from the file
	 * the execution can be perform unless the provided id is valid and y is pressed
	 */
	public static void performDelete() {
		int search = Manupulate.searchById(IO.readInt("Delete ID: "));
		if(!Manupulate.isExist(search)) 
			IO.println("No record with this id " + search + "!!!");
		else {
			if(IO.readChar("'y' to delete: ") == 'y') 
				Run.article.remove(search);
		}
		IO.pressEnterContinue();
	}
	
	/**
	 * update a record by provided valid id
	 * there are three kind of updation:
	 * 1> update title
	 * 2> update author
	 * 3> update content
	 */
	public static void performUpdate() {
		int search = Manupulate.searchById(IO.readInt("Update ID: "));
		if(!Manupulate.isExist(search))
			IO.println("No record with this id !!!");
		else {
			Article a = Run.article.get(search);
			switch(IO.readInt("1. titel, 2. author, 3. content ", 1, 3)) {
				case 1:
					a.setTitle(IO.readString("new title: "));
					Run.article.set(search, a);
					break;
				case 2:
					a.setAuthor(IO.readString("new author: "));
					Run.article.set(search, a);
					break;
				case 3:
					a.setDetail(IO.readString("new content: "));
					Run.article.set(search, a);
					break;
			}
		}
	}
	
	/**
	 * search a record with a provided id
	 * @param searchId: the value of id that need to search
	 * @return	if id is found, it will return +, if not, it will return -
	 */
	public static int searchById(int key) {
        int lo = 0;
        int hi = Run.article.size() - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < Run.article.get(mid).getId()) hi = mid - 1;
            else if (key > Run.article.get(mid).getId()) lo = mid + 1;
            else return mid;
        }
        return -1;
    }


	/**
	 * check if an is is exist in the file or not
	 * @param id: search id provided by user
	 * @return	true: exist, false: not exist
	 */
	public static boolean isExist(int id) {
		if(id == -1)
			return false;
		return true;
	}
	
	public static ArrayList<Article> searchByString(String stringSearch) {
		ArrayList<Article> arr = new ArrayList<Article>();
//		String start = stringSearch.substring(0, stringSearch.length()/3);
//		String contain = stringSearch.substring(start.length(), (stringSearch.length()/3) * 2);
//		String end = stringSearch.substring(contain.length());
		String compare = "";
		Article a;
		for(int i=0;i<Run.article.size();i++) {
			a = Run.article.get(i);
			compare = a.getTitle();
			if(compare.equalsIgnoreCase(stringSearch))
				arr.add(a);
		}
		return arr;
	}
	
	/**
	 * search any exist and like
	 * @param stringSearch : value that is need provided by user for searching
	 */
	public static void mySearch(String stringSearch) {
		ArrayList<Article> arr = searchByString(stringSearch); 
		p = new Pagination(arr, 5);
		while(IO.start) {
			p.displayAllRecord(arr, 4);
			Display.showMenu("<<< Menu >>>", "0:exit search", "| 1:Mover First | 2:Move Next | 3:Move Previous | 4:Move Last |");
			int key = IO.readInt("Make your choice: ");
			switch (key) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 0:
					return;
			}
		}
	}
	
	/**
	 * simple search or specific search only
	 * it means that if have found. if not no exist
	 */
	public static void search() {
		ArrayList<Article> arr = new ArrayList<Article>();
		Article a;
		String search = "";
		switch(IO.readInt("1. Search by title, 2. by Author ", 1, 2)) {
			case 1:
				search = IO.readString("Search Title: ");
				for(int i=0;i<Run.article.size();i++) {
					a = Run.article.get(i);
					if(a.getTitle().equalsIgnoreCase(search))
						arr.add(a);
				}
				if(arr.size() < 0) 
					IO.println("Not found !!!");
				else 
					Pagination.displayAllRecord(arr, 4); 
				break;
			case 2:
				search = IO.readString("Search Author: ");
				for(int i=0;i<Run.article.size();i++) {
					a = Run.article.get(i);
					if(a.getAuthor().equalsIgnoreCase(search))
						arr.add(a);
				}
				if(arr.size() < 0) 
					IO.println("Not found !!!");
				else 
					Pagination.displayAllRecord(arr, 4); 
				break;
		}
		IO.pressEnterContinue();
	}
	
	/**
	 * the result of search if exist
	 * @param arr: store all found record for display
	 */
	public static void resultSearch(ArrayList<Article> arr) {
		IO.println("Search Result:");
		for(int i=0;i<arr.size();i++) 
			IO.println(arr.get(i).toString());
	}
	
	/**
	 * alter for searching
	 * 1> search by title
	 * 2> search by author
	 */
	public static void switchSearch() {
		switch(IO.readInt("1. Search by title, 2. by Author ", 1, 2)) {
			case 1:
				mySearch(IO.readString("Search Title: "));
				break;
			case 2:
				mySearch(IO.readString("Search Author: "));
				break;
		}
	}
	
	/**
	 * check whether the content start with what we are going to search
	 * @param compare: Exist string for searching
	 * @param match: data for searching
	 * @return true: exist, false: no exist
	 */
	public static boolean startWith(String compare, String match) {
		if(compare.startsWith(match))
			return true;
		return false;
	}
	
	/**
	 * check whether the content contain what we are going to search
	 * @param compare: Exist string for searching
	 * @param match: data for searching
	 * @return true: exist, false: no exist
	 */
	public static boolean contain(String compare, String match) {
		if(compare.contains(match))
			return true;
		return false;
	}
	
	/**
	 * check whether the content end with what we are going to search
	 * @param compare: Exist string for searching
	 * @param match: data for searching
	 * @return true: exist, false: no exist
	 */
	public static boolean endWith(String compare, String match) {
		if(compare.endsWith(match))
			return true;
		return false;
	}

}
