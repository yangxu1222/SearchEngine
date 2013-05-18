package com.yangxu.searchengine.bo;

public class SearchResult {

	private String abstractContent;


	private String url;

	private String title;

	private String indexCreateTime;

	private String upLoadTime;

	public SearchResult() {

	}

	public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}



	public String getIndexCreateTime() {
		return indexCreateTime;
	}

	public void setIndexCreateTime(String indexCreateTime) {
		this.indexCreateTime = indexCreateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("title:"+title+"\n");
		sb.append("abstractContent:"+abstractContent+"\n");
		sb.append("url:"+url+"\n");
		sb.append("indexCreateTime:"+indexCreateTime+"\n");
		sb.append("]");
		
		return sb.toString();
	}

	public String getUpLoadTime() {
		return upLoadTime;
	}

	public void setUpLoadTime(String upLoadTime) {
		this.upLoadTime = upLoadTime;
	}

	
}
