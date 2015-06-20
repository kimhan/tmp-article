package kshrd.miniproject;

import java.util.*;

import kshrd.raw.*;

public class Pagination {
	
	/**
	 * currentPage : current display page depending on number of page per row
	 * recordPerPage : max number of record per page
	 * recordStart : first index of record per page that display in each page
	 * recordStop : last index of record per page that display in each page
	 */
	static int currentPage = 1;
	static int recordPerPage = 5;
	static int recordStart;
	static int recordStop;
	
	/**
	 * the main display use to display the record depending of the pagination 
	 */
	public static void displayAllRecord(ArrayList<Article> tmp) {
		numberRecord(currentPage, tmp);
		Display.generateHeaderView();
		IO.println(Article.showTitle(Run.columnPerPage));
		for(int i=recordStart;i>recordStop;i--) 
			IO.println(tmp.get(i).showRecord(Run.columnPerPage));
		IO.println(Article.showPage("Page: " + currentPage + "/" + getTotalPage(tmp) , "Total Record: " + tmp.size()));
	}
	
	/**
	 * Use to set the number of record per page, start display record, stop display record, total record
	 * @param pageNumber : is use to focus which page is the record are displaying
	 */
	public static void numberRecord(int pageNumber, ArrayList<Article> tmp) {
		currentPage = pageNumber; 
		if(getRemainRecord(tmp) == 0) {
			recordStart = (getTotalPage(tmp)-currentPage) * recordPerPage -1 + recordPerPage;
			recordStop = recordStart - recordPerPage;
		}
		else {
			if(currentPage == getTotalPage(tmp)) {
				recordStart = (getTotalPage(tmp)-currentPage) * recordPerPage -1 + getRemainRecord(tmp);
				recordStop = recordStart - getRemainRecord(tmp);
			}
			else {
				recordStart = (getTotalPage(tmp)-currentPage) * recordPerPage - 1 + getRemainRecord(tmp);
				recordStop = recordStart - recordPerPage;
			}
		}
	}
	
	/**
	 * overloading method of numberRecord
	 * @param arr : the array contain all the record
	 * @param pageNumber : the recent page number
	 */
	public static void numberRecord(ArrayList<Article> tmp, int pageNumber) {
		recordPerPage = 5; 
		currentPage = pageNumber; 
		if(currentPage == getTotalPage(tmp)) {
			recordStart = (getTotalPage(tmp) - currentPage) * recordPerPage - 1 + getRemainRecord(tmp);
			recordStop = recordStart - getRemainRecord(tmp);
		}
		else {
			recordStart = (getTotalPage(tmp) - currentPage) * recordPerPage - 1 + getRemainRecord(tmp);
			recordStop = recordStart - recordPerPage;
		}
	}
	
	/**
	 * use to move the record to the next page
	 */
	public static void moveNext(ArrayList<Article> tmp) {
		if(currentPage == (getTotalPage(tmp) - 1) || currentPage == getTotalPage(tmp)) 
			moveLast(tmp);
		else {
			currentPage += 1; 
			numberRecord(currentPage, tmp);
		}
	}
	
	/**
	 * use to move the record to the previous page
	 */
	public static void movePrevious(ArrayList<Article> tmp) {
		if(currentPage == 1) 
			moveFirst(tmp);
		else 
			numberRecord(currentPage - 1, tmp);
	}
	
	/**
	 * use to move the record to the first page
	 */
	public static void moveFirst(ArrayList<Article> tmp) {
		currentPage = 1;
		numberRecord(currentPage, tmp);
	}
	
	/**
	 * use to move the record to the last page
	 */
	public static void moveLast(ArrayList<Article> tmp) {
		numberRecord(getTotalPage(tmp), tmp);
	}
	
	/**
	 * @return total page number
	 */
	public static int getTotalPage() {
		return getTotalPage(Run.article);
	}
	
	/**
	 * return total page depend on the array argument provided
	 */
	public static int getTotalPage(ArrayList<Article> tmp) {
		return (int)Math.ceil(tmp.size() / (double)recordPerPage);
	}
	
	/**
	 * @return the number of remain record 
	 */
	public static int getRemainRecord(ArrayList<Article> tmp) {
		return tmp.size() % recordPerPage;
	}
	
}
