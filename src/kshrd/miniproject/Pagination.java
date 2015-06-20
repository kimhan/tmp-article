package kshrd.miniproject;

import java.util.ArrayList;

import kshrd.raw.Article;
import kshrd.raw.Display;
import kshrd.raw.IO;

public class Pagination {
	
	private int currentPage;
	private int recordPerPage;
	private int recordStart;
	private int recordStop;
	
	/**
	 * Constructor 
	 * @param article : store all article object 
	 * @param recordPerPage : number of record per page
	 */
	public Pagination(ArrayList<Article> article, int recordPerPage) {
		setCurrentPage(1);
		setRecordPerPage(recordPerPage); 
		numberRecord(getCurrentPage(), article);
	}
	
	/**
	 * Use for display the record depending of the argument as ArrayList<Article>
	 * @param arr : the array that provide record for display
	 */
//	public static void displayAllRecord(ArrayList<Article> arr) {
//		
//		IO.println(Article.showTitle(Run.col));
//		for(int i=0;i<arr.size();i++) 
//			IO.println(arr.get(i).showRecord(Run.col));
//		IO.println(Article.showPage("Page: 1/1", "Total Record: " + arr.size()));
//	}
	/**
	 * the main display use to display the record depending of the pagination 
	 */
	public void displayAllRecord(ArrayList<Article> tmp) {
		numberRecord(getCurrentPage(), tmp);
		Display.generateHeaderView();
		IO.println(Article.showTitle(Run.col));
		for(int i=getRecordStart();i>getRecordStop();i--) 
			IO.println(tmp.get(i).showRecord(Run.col));
		IO.println(Article.showPage("Page: " + getCurrentPage() + "/" + getTotalPage(tmp) , "Total Record: " + tmp.size()));
	}
	
	/**
	 * Use to set the number of record per page, start display record, stop display record, total record
	 * @param pageNumber : is use to focus which page is the record are displaying
	 */
	public void numberRecord(int pageNumber, ArrayList<Article> tmp) {
		setRecordPerPage(getRecordPerPage()); 
		setCurrentPage(pageNumber); 
		if(getRemainRecord(tmp) == 0) {
			setRecordStart((getTotalPage(tmp)-getCurrentPage())*getRecordPerPage() -1 + getRecordPerPage());
			setRecordStop(getRecordStart() - getRecordPerPage());
		}
		else {
			if(getCurrentPage() == getTotalPage(tmp)) {
				setRecordStart((getTotalPage(tmp)-getCurrentPage())*getRecordPerPage() -1 + getRemainRecord(tmp));
				setRecordStop(getRecordStart() - getRemainRecord(tmp));
			}
			else {
				setRecordStart((getTotalPage(tmp)-getCurrentPage())*getRecordPerPage() - 1 + getRemainRecord(tmp));
				setRecordStop(getRecordStart() - getRecordPerPage());
			}
		}
	}
	
	/**
	 * overloading method of numberRecord
	 * @param arr : the array contain all the record
	 * @param pageNumber : the recent page number
	 */
	public void numberRecord(ArrayList<Article> tmp, int pageNumber) {
		setRecordPerPage(5); 
		setCurrentPage(pageNumber); 
		if(getCurrentPage() == getTotalPage(tmp)) {
			setRecordStart((getTotalPage(tmp)-getCurrentPage())*getRecordPerPage() - 1 + getRemainRecord(tmp));
			setRecordStop(getRecordStart() - getRemainRecord(tmp));
		}
		else {
			setRecordStart((getTotalPage(tmp)-getCurrentPage())*getRecordPerPage() - 1 + getRemainRecord(tmp));
			setRecordStop(getRecordStart() - getRecordPerPage());
		}
	}
	
	/**
	 * use to move the record to the next page
	 */
	public void moveNext(ArrayList<Article> tmp) {
		if(getCurrentPage() == (getTotalPage(tmp) - 1) || getCurrentPage() == getTotalPage(tmp)) 
			moveLast(tmp);
		else {
			setCurrentPage(getCurrentPage()+1); 
			numberRecord(getCurrentPage(), tmp);
		}
	}
	
	/**
	 * use to move the record to the previous page
	 */
	public void movePrevious(ArrayList<Article> tmp) {
		if(getCurrentPage() == 1) 
			moveFirst(tmp);
		else 
			numberRecord(getCurrentPage() - 1, tmp);
	}
	
	/**
	 * use to move the record to the first page
	 */
	public void moveFirst(ArrayList<Article> tmp) {
		setCurrentPage(1);
		numberRecord(getCurrentPage(), tmp);
	}
	
	/**
	 * use to move the record to the last page
	 */
	public void moveLast(ArrayList<Article> tmp) {
		numberRecord(getTotalPage(tmp), tmp);
	}
	
	/**
	 * @return total page number
	 */
	public int getTotalPage() {
		return getTotalPage(Run.article);
	}
	
	/*
	 * return total page depend on the array argument provided
	 */
	public int getTotalPage(ArrayList<Article> tmp) {
		return (int)Math.ceil(tmp.size() / (double)getRecordPerPage());
	}
	
	/**
	 * 
	 * @return the number of remain record 
	 */
	public int getRemainRecord(ArrayList<Article> tmp) {
		return tmp.size() % getRecordPerPage();
	}

	/**
	 * 
	 * @return number of record per page
	 */
	public int getRecordPerPage() {
		return recordPerPage;
	}

	/**
	 * 
	 * @param set number of record Per Page
	 */
	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	/**
	 * 
	 * @return current page that we are on
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 
	 * @param current Page to display
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 
	 * @return start record that display in a page
	 */
	public int getRecordStart() {
		return recordStart;
	}

	/**
	 * 
	 * @param recordStart : index of record start
	 */
	public void setRecordStart(int recordStart) {
		this.recordStart = recordStart;
	}

	/**
	 * 
	 * @return end index of record that finish in display
	 */
	public int getRecordStop() {
		return recordStop;
	}

	/**
	 * 
	 * @param recordStop : index of record stop
	 */
	public void setRecordStop(int recordStop) {
		this.recordStop = recordStop;
	}
	
}
