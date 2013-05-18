package com.yangxu.searchengine.bo;

import java.util.ArrayList;

public class SearchResults {
	
	private ArrayList<SearchResult> results = new ArrayList<SearchResult>();
	// 当前页的起始的索引号
	private int startindex;
	// 当前页面中所要显示的最小页
	private int minpage;
	// 当前页面中索要显示的最大页
	private int maxpage;
	// 还有比maxpage更大的页面吗
	private int hasnext;

	public int getHasnext() {
		return hasnext;
	}

	public void setHasnext(int hasnext) {
		this.hasnext = hasnext;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public int getMinpage() {
		return minpage;
	}

	public void setMinpage(int minpage) {
		this.minpage = minpage;
	}

	public ArrayList<SearchResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<SearchResult> results) {
		this.results = results;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

}
