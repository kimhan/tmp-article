package kshrd.miniproject;

import java.util.ArrayList;

import kshrd.raw.Article;
import kshrd.raw.Display;
import kshrd.raw.IO;

public class Run {

	/**
	 * article : array store all record after reading from a file
	 * pagination : display record in page according to number of record and max row per page
	 * columnPerPage : display column per page
	 */
	public static ArrayList<Article> article;
	static Pagination pagination;
	static int columnPerPage;
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		new Run();
	}
	
	/**
	 * constructor
	 * initialize array list to store all record from file
	 * initialize pagination for display all record depending on array and max row per page
	 * initialize number of column
	 * start switch case
	 */
	Run() {
		article = new ArrayList<Article>();
//		addToArticle(10002);
		Run.article = FileMethod.readDataFromFile();
		pagination = new Pagination(article, 5);
		columnPerPage = 4;
		myChoice();
	}
	
	/**
	 * start switch case
	 */
	public static void myChoice() {
		while(IO.start) {
			pagination.displayAllRecord(article);
			choice();
		}
	}
	
	/**
	 * switch case 
	 * case 1: move First
	 * case 2: move Next
	 * case 3: move Previous
	 * case 4: move Last
	 * case 5: Read a Record
	 * case 6: Write a record
	 * case 7: search record
	 * case 8: update a record
	 * case 9: delete a record
	 * case g: go to any page
	 * case #: set number of record per page
	 * case 0: exit the program
	 */
	public static void choice() {
		Display.showMenu("<<< Menu >>>", "1:Mover First | 2:Move Next | 3:Move Previous | 4:Move Last", "5:read | 6:write | 7:search | 8:update | 9:delete | G:go to | R:set Row | C:set Column | 0:exit");
		int key = IO.readChar("Make your choice: ");
		switch (key) {
		case '1':
			pagination.moveFirst(article);
			break;
		case '2':
			pagination.moveNext(article);
			break;
		case '3':
			pagination.movePrevious(article);
			break;
		case '4':
			pagination.moveLast(article); 
			break;
		case '5':
			Manupulate.performRead();
			break;
		case '6':
			Manupulate.performWrite();
			break;
		case '7':
			Manupulate.search();
			break;
		case '8':
			Manupulate.performUpdate(article); 
			pagination.moveFirst(article);
			break;
		case '9':
			Manupulate.performDelete(); 
			break;
		case 'G': case 'g':
			pagination.numberRecord(IO.readInt("Go to page: ", 0, pagination.getTotalPage()), article);
			break;
		case 'r': case 'R':
			pagination.setRecordPerPage(IO.readInt("Number of Record per page: "));
			pagination.moveFirst(article);
			break;
		case 'c': case 'C':
			columnPerPage = IO.readInt("set Colum[2, 4]: ", 2, 4);
			break;
		case '0':
			IO.start = false;
			break;
		}
		
	}
	
	/**
	 * add article to the array
	 * @param numberOfArticle : number of article contain in array
	 */
	public void addToArticle(int numberOfArticle) {
		ArrayList<Article> tmp = new ArrayList<Article>();
		for(int i=0;i<numberOfArticle;i++) {
			tmp.add(new Article(i+1));
		}
		FileMethod.writeDataIntoFile(tmp);

	}
	
}
