package kshrd.miniproject;

import java.io.IOException;
import java.util.*;

import kshrd.raw.*;

public class Run {

	/**
	 * article : array store all record after reading from a file
	 * Pagination : display record in page according to number of record and max row per page
	 * columnPerPage : display column per page
	 */
	public static ArrayList<Article> article;
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
	 * initialize Pagination for display all record depending on array and max row per page
	 * initialize number of column
	 * start switch case
	 */
	@SuppressWarnings("static-access")
	Run() {
		article = new ArrayList<Article>();
		String []load = {"L", "O", "A", "D", "I", "N", "G"};
		try {
//			addToArticle(10000002);
			FileMethod.readDataFromFile();
			IO.print(Display.whiteSpace(45) + "+ ");
			for(int i=0;i<load.length;i++) {
				IO.print(load[i]+"");
				Thread.currentThread().sleep(500);
			}
			IO.println(" +");
        } catch(Exception e) {
        	e.printStackTrace();
        }
		columnPerPage = 4;
		myChoice();
	}
	
	/**
	 * start switch case
	 */
	public static void myChoice() {
		while(IO.start) {
			Pagination.displayAllRecord(article);
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
			Pagination.moveFirst(article);
			break;
		case '2':
			Pagination.moveNext(article);
			break;
		case '3':
			Pagination.movePrevious(article);
			break;
		case '4':
			Pagination.moveLast(article); 
			break;
		case '5':
			Manipulate.performRead();
			break;
		case '6':
			Manipulate.performWrite();
			break;
		case '7':
			Manipulate.search();
			break;
		case '8':
			Manipulate.performUpdate(article, false);
			IO.println("Successfully updated my friend !!!");
			IO.pressEnterContinue();
			//Pagination.moveFirst(article);
			break;
		case '9':
			Manipulate.performDelete(article, false); 
			IO.println("Congratulation, Record reset successfully !!!");
			IO.pressEnterContinue();
			break;
		case 'G': case 'g':
			Pagination.numberRecord(IO.readInt("Go to page: ", 0, Pagination.getTotalPage()), article);
			break;
		case 'r': case 'R':
			Pagination.recordPerPage = IO.readInt("Number of Record per page: ");
			Pagination.moveFirst(article);
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
		Article a = new Article();
		for(int i=0;i<numberOfArticle;i++) {
			a.setId(i+1);
			article.add(article.size(), a);
		}
		FileMethod.writeDataIntoFile(article);
	}
	
}
