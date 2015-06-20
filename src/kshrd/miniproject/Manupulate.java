package kshrd.miniproject;

import java.util.*;

import kshrd.raw.Article;
import kshrd.raw.Display;
import kshrd.raw.IO;

public class Manupulate {

	/**
	 * tmpSearch : use to store temporary record after searching
	 */
	static ArrayList<Article> tmpSearch;

	/**
	 * read a record from arraylist according to the read id the id should be
	 * relevance to the one in the array if there is no id it will load a
	 * message to inform that there is no such a record anyway the wanted record
	 * will be shown.
	 */
	public static void performRead() {
		int search = Manupulate.searchById(IO.readInt("Read ID: "));
		if (Manupulate.isExist(search)) {
			Article a = Run.article.get(search);
			IO.println("ID: " + a.getId());
			IO.println("Title: " + a.getTitle());
			IO.println("Author: " + a.getAuthor());
			IO.println("Content:");
			IO.println(a.getDetail());
		} else
			IO.println("No record with this id !!!");
		IO.pressEnterContinue();
	}

	/**
	 * write a record into a file it also was add to the array also
	 * unless y is pressed
	 */
	@SuppressWarnings("resource")
	public static void performWrite() {
		Article tmp = null;
		do {
			String title = IO.readString("Add Title: ");
			String author = IO.readString("Add Author: ");
			StringBuffer sb = new StringBuffer();
			System.out.println("Plz enter content : ");
			System.out.println("*Note(Press Enter + '.' + Enter to Finish) => ");
			String content = "";
			Scanner input = new Scanner(System.in);
			while (true) {
				content = input.nextLine();
				if (content != null && content.trim().length() == 1	&& content.trim().charAt(0) == '.')
					break;
				if (content == null)
					content = "";
				sb.append(content + "\n");
			}
			if (IO.readChar("want to save?[y/n]") == 'y') {
				tmp = new Article((Run.article.get(Run.article.size() - 1).getId() + 1), title, author, content);
				Run.article.add(tmp);
				FileMethod.writeDataIntoFile(tmp);
			}
		} while (IO.readChar("Successfully added. Add one more record?[y/n]: ") == 'y');
		IO.pressEnterContinue();
	}

	/**
	 * delete one record from the file the execution can be perform unless the
	 * provided id is valid and y is pressed
	 */
	public static void performDelete() {
		int search = Manupulate.searchById(IO.readInt("Delete ID: "));
		if (!Manupulate.isExist(search))
			IO.println("No record with this id " + search + "!!!");
		else {
			if (IO.readChar("'y' to delete: ") == 'y')
				Run.article.set(search, null);
		}
		IO.pressEnterContinue();
	}

	/**
	 * update a record by provided valid id there are three kind of updation: 1>
	 * update title 2> update author 3> update content
	 */
	public static void performUpdate(ArrayList<Article> tmp) {
		int search = Manupulate.searchById(tmp, IO.readInt("Update ID: "));
		if (!Manupulate.isExist(search)) 
			IO.println("No record with this id !!!");
		else {
			Article a = Run.article.get(search);
			switch (IO.readInt("1. titel, 2. author, 3. content ", 1, 3)) {
			case 1:
				a.setTitle(IO.readString("new title: "));
				break;
			case 2:
				a.setAuthor(IO.readString("new author: "));
				break;
			case 3:
				a.setDetail(IO.readString("new content: "));
				break;
			}
			Run.article.set(search, a);
			FileMethod.writeDataIntoFile(Run.article);
			IO.println("Successfully added my friend !!!");
		}
		IO.pressEnterContinue();
	}

	/**
	 * binary search number only
	 * @param tmp : explore array for match
	 * @param key : needed number for searching
	 * @return	index of array that store the key
	 */
	public static int searchById(ArrayList<Article> tmp, int key) {
		int position;
		int lowerbound = 0;
		int upperbound = tmp.size() - 1;
		position = (lowerbound + upperbound) / 2;	// To start, find the subscript of the middle position.
		while ((tmp.get(position).getId() != key) && (lowerbound <= upperbound)) {
			if (tmp.get(position).getId() > key) {  // If the number is > key, ..
				upperbound = position - 1; 			// decrease position by one.
			} else {
				lowerbound = position + 1; 			// Else, increase position by one.
			}
			position = (lowerbound + upperbound) / 2;
		}
		if (lowerbound <= upperbound) {
			return position;
		} else
			return -1;
	}

	/**
	 * search a record with a provided id
	 * @param searchId : the value of id that need to search
	 * @return if id is found, it will return +, if not, it will return -
	 */
	public static int searchById(int searchId) {
		for (int i = 0; i < Run.article.size(); i++) {
			if (Run.article.get(i).getId() == searchId) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * check if an is is exist in the file or not
	 * @param id : search id provided by user
	 * @return true: exist, false: not exist
	 */
	public static boolean isExist(int id) {
		if (id == -1)
			return false;
		return true;
	}

	/**
	 * specific search : searched by title, search by author
	 * other search : if contain
	 */
	public static void search() {
		tmpSearch = new ArrayList<Article>();
		switch (IO.readInt("1. Search by title\n2. by Author\n3. other\nplease your choice: ", 1, 3)) {
			case 1:
				searchSpecific(IO.readString("Search Title: "), tmpSearch, "title");
				break;
			case 2:
				searchSpecific(IO.readString("Search Author: "), tmpSearch, "author");
				break;
			case 3:
				searchLike(IO.readString("Search All: "), tmpSearch);
				break;
		}
		if(tmpSearch.size() <= 0) {
			IO.println("Sorry my friend, search not found !!!");
			IO.pressEnterContinue();
		} else {
			while (IO.start) {
				Run.pagination.displayAllRecord(tmpSearch);
				choice();
			}
			IO.start = true;
			Run.myChoice();
		}
	}
	
	/**
	 * specific search item : search by title and search by author
	 * @param search : specific search string
	 * @param tmp : explore array for searching
	 * @param criteria : specific criteria whether title or author
	 */
	public static void searchSpecific(String search, ArrayList<Article> tmp, String criteria) {
		Article a;
		String compare = "";
		for(int i=0;i<Run.article.size();i++) {
			a = Run.article.get(i);
			switch(criteria) {
				case "title":
					compare = a.getTitle();
					break;
				case "author":
					compare = a.getAuthor();
					break;
			}
			if (compare.equalsIgnoreCase(search))
				tmpSearch.add(a);
		}
	}
	
	/**
	 * search any field if contains including title, author, date, and content
	 * @param search : search string
	 * @param tmp : explore array for searching
	 */
	public static void searchLike(String search, ArrayList<Article> tmp) {
		Article a;
		for(int i=0;i<Run.article.size();i++) {
			a = Run.article.get(i);
			if(a.getDate().toLowerCase().contains(search.toLowerCase()) || 
					a.getTitle().toLowerCase().contains(search.toLowerCase()) || 
					a.getAuthor().toLowerCase().contains(search.toLowerCase()) || 
					a.getDetail().toLowerCase().contains(search.toLowerCase()))
				tmp.add(a);
		}
	}

	/**
	 * after search result
	 * we can check all the record
	 * we can do some operation like update and delete
	 * 1. moveFirst
	 * 2. moveNext
	 * 3. movePrevious
	 * 4. moveLast
	 * 5. Update a record
	 * 6. Delete a record
	 * g. go to any page
	 * r. set number of display row
	 * c. set number of display column
	 * o. back to main menu
	 */
	public static void choice() {
		Display.showMenu("<<< Menu >>>",
				"1:Mover First | 2:Move Next | 3:Move Previous | 4:Move Last",
				"5:update | 6:delete | G:go to | R:set Row | C:set Column | 0:Main Menu");
		int key = IO.readChar("Make your choice: ");
		switch (key) {
		case '1':
			Run.pagination.moveFirst(tmpSearch);
			break;
		case '2':
			Run.pagination.moveNext(tmpSearch);
			break;
		case '3':
			Run.pagination.movePrevious(tmpSearch);
			break;
		case '4':
			Run.pagination.moveLast(tmpSearch);
			break;
		case '5':
			Manupulate.performUpdate(tmpSearch);
			Run.pagination.moveFirst(tmpSearch);
			break;
		case '6':
			Manupulate.performDelete();
			break;
		case 'G':
		case 'g':
			Run.pagination.numberRecord(IO.readInt("Go to page: ", 0,
					Run.pagination.getTotalPage()), tmpSearch);
			break;
		case 'r':
		case 'R':
			Run.pagination.setRecordPerPage(IO
					.readInt("Number of Record per page: "));
			Run.pagination.moveFirst(tmpSearch);
			break;
		case 'c':
		case 'C':
			Run.columnPerPage = IO.readInt("set Colum[2, 4]: ", 2, 4);
			break;
		case '0':
			IO.start = false;
			break;
		}
	}

	/**
	 * the result of search if exist
	 * @param arr : store all found record for display
	 */
	public static void resultSearch(ArrayList<Article> arr) {
		IO.println("Search Result:");
		for (int i = 0; i < arr.size(); i++)
			IO.println(arr.get(i).toString());
	}

	/**
	 * check whether the content start with what we are going to search
	 * @param compare : Exist string for searching
	 * @param match : data for searching
	 * @return true: exist, false: no exist
	 */
	public static boolean startWith(String compare, String match) {
		if (compare.startsWith(match))
			return true;
		return false;
	}

	/**
	 * check whether the content contain what we are going to search
	 * @param compare : Exist string for searching
	 * @param match : data for searching
	 * @return true: exist, false: no exist
	 */
	public static boolean contain(String compare, String match) {
		if (compare.contains(match))
			return true;
		return false;
	}

	/**
	 * check whether the content end with what we are going to search
	 * @param compare : Exist string for searching
	 * @param match : data for searching
	 * @return true: exist, false: no exist
	 */
	public static boolean endWith(String compare, String match) {
		if (compare.endsWith(match))
			return true;
		return false;
	}

}
