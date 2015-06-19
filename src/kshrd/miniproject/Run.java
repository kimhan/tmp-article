package kshrd.miniproject;

import java.io.IOException;
import java.util.*;

import kshrd.raw.Article;
import kshrd.raw.Display;
import kshrd.raw.IO;

public class Run {

	public static ArrayList<Article> article;
	//private LinkedList<Article> bookTmp;
	private Pagination pagination;
	private int column;
	
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		new Run();
	}
	
	/**
	 * constructor
	 */
	Run() {
		article = new ArrayList<Article>();
		addToArticle(5002);
//		IO.println("done");
//		Run.article = FileMethod.fileToArrayList();
//		try {
//			FileMethod.in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(FileMethod.readDataFromFile());
//		System.out.println(Run.article.get(0).toString());
		pagination = new Pagination(article, 5);
		setColumn(4);
		while(IO.start) {
			pagination.displayAllRecord(getColumn());
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
	public void choice() {
		Display.showMenu("<<< Menu >>>", "1:Mover First | 2:Move Next | 3:Move Previous | 4:Move Last", "5:read | 6:write | 7:search | 8:update | 9:delete | G:go to | p:set page | c: set Cols | 0:exit");
		int key = IO.readChar("Make your choice: ");
		switch (key) {
		case '1':
			getPagination().moveFirst();
			break;
		case '2':
			getPagination().moveNext();
			break;
		case '3':
			getPagination().movePrevious();
			break;
		case '4':
			getPagination().moveLast(); 
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
			Manupulate.performUpdate(); 
			getPagination().moveFirst();
			break;
		case '9':
			Manupulate.performDelete(); 
			break;
		case 'G': case 'g':
			getPagination().numberRecord(IO.readInt("Go to page: ", 0, getPagination().getTotalPage()));
			break;
		case 'p':
			getPagination().setRecordPerPage(IO.readInt("Number of Record per page: "));
			getPagination().moveFirst();
			break;
		case 'c':
			setColumn(IO.readInt("Set Column: ",2,4));
			break;
		case '0':
			IO.start = false;
			break;
		}
		
	}
	
	/**
	 * add article to the array
	 * @param numberOfArticle : number of article contain in array
	 * @throws IOException 
	 */
	public void addToArticle(int numberOfArticle) {
//		ArrayList<Article> tmp = new ArrayList<Article>();
//		for(int i=0;i<numberOfArticle;i++) {
////			data += new Article(i+1).toString();
//			tmp.add(new Article(i+1));
////			FileMethod.writeDataIntoFile(new Article(i+1).toString());
//		}
//		FileMethod.writeDataIntoFile(tmp);
		Run.article = FileMethod.readDataFromFile();
	}

	// setter and getter
	/**
	 * 
	 * @return	return pagination object
	 */
	public Pagination getPagination() {
		return pagination;
	}
	
	/**
	 * 
	 * @param pagination : pagination value
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	// end of setter and getter
	
}
