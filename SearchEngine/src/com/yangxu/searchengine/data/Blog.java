/* @author Xu Yang;
   @time 2013-5-17
*/
package com.yangxu.searchengine.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Blog {
	
	private String title;
	
	private String content;
	// 用于表示在搜索结果列表中的信息摘要
	private String summary;
	
	private String url;

	//最后更新时间
	private String time; 
	
	//索引得分
	private double score;
	
	
	public Blog(String title, String content, String summary,String url,double score) {
		
		this.title = title;
		this.content = content;
		this.summary = summary;
		this.url = url;
		this.score = score;
	}

	/**
	 * Sorts list in descending order of score value.
	 */
	public static void sortByScore(List<Blog> values) {
		Collections.sort(values, new Comparator<Blog>() {
			public int compare(Blog r1, Blog r2) {
				int result = 0;
				// sort based on score value
				if (r1.getScore() < r2.getScore()) {
					result = 1; // sorting in descending order
				} else if (r1.getScore() > r2.getScore()) {
					result = -1;
				} else {
					result = 0;
				}
				return result;
			}
		});
	}
	/**
	 * Sorts array in descending order of score value.
	 */
	public static void sortByScore(Blog[] values) {
		Arrays.sort(values, new Comparator<Blog>() {
			public int compare(Blog r1, Blog r2) {
				int result = 0;
				// sort based on score value
				if (r1.getScore() < r2.getScore()) {
					result = 1; // sorting in descending order
				} else if (r1.getScore() > r2.getScore()) {
					result = -1;
				} else {
					result = 0;
				}
				return result;
			}
		});
	}
		
	public String toString() {
		StringBuilder strB = new StringBuilder();
		// strB.append("Document ID    : ").append(docId).append("\n");
		strB.append("Document Title : ").append(title).append("\n");
		//strB.append("Document Summay : ").append(summary).append("\n");
		strB.append("Document URL: ").append(url).append("  -->  ");
		strB.append("Relevance Score: ").append(score).append("\n");	
		return strB.toString();
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @return document title if available
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param docId
	 *            the docId to set
	 */

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @param title
	 *            document title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

}
