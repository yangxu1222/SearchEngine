package com.yangxu.searchengine.service;

import com.yangxu.searchengine.bo.SearchRequest;
import com.yangxu.searchengine.bo.SearchResult;
import com.yangxu.searchengine.bo.SearchResults;


public interface SearchService {
	public abstract SearchResults getSearchResults(SearchRequest request,String searchField);

	//public abstract SearchResult getSearchResultById(String fileName);
}
