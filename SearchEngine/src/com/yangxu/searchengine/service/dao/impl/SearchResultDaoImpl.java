/* @author Xu Yang;
   @time 2013-5-17
*/
package com.yangxu.searchengine.service.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;

import com.yangxu.searchengine.bo.SearchResult;
import com.yangxu.searchengine.config.PropertyConfiguration;
import com.yangxu.searchengine.service.dao.SearchResultDao;

public class SearchResultDaoImpl implements SearchResultDao{

	public SearchResult getSearchResultById(String fileName,String searchField) {
		final SearchResult sr = new SearchResult();
		final String filePath = PropertyConfiguration.getCsdnProcessedDir() + fileName +".txt";
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(filePath));
			LineNumberReader reader = new LineNumberReader(
					new InputStreamReader(fis, "UTF-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				// int lineNumber = reader.getLineNumber();
				switch (reader.getLineNumber()) {
				case 1:
					sr.setUrl(line);
					break;
				case 2:
					break;
				case 3:
					sr.setTitle(line.split(":")[1]);
					break;
				default:
					sb.append(line);
					break;
				}
			}
			reader.close();
			//sr.setContent(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return sr;
	}

	@Override
	public SearchResult getSearchResultById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
