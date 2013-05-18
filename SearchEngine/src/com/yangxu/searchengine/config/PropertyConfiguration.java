package com.yangxu.searchengine.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyConfiguration {

	/* 属性文件前缀 */
	private static String CONFIG_FILE = "app";

	private static ResourceBundle bundle;

	static {
		try {
			bundle = ResourceBundle.getBundle(CONFIG_FILE);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
	}

	private static String getValue(String key) {
		return bundle.getString(key);
	}

	public static String getDBUrl() {
		String val = getValue("jdbc.url");
		return val;
	}

	public static String getDBUsr() {
		String val = getValue("jdbc.username");
		return val;
	}

	public static String getDBPwd() {
		String val = getValue("jdbc.password");
		return val;
	}

	public static String getProductDir() {
		String val = getValue("product.directory");
		return val;
	}

	public static String getIndexStorePath() {
		String val = getValue("product.index.directory");
		return val;
	}

	public static String getProductMobileDir() {
		String val = getValue("product.mobile.directory");
		return val;
	}

	public static String getProductImageDir() {
		String val = getValue("product.image.directory");
		return val;
	}

	public static String getWordDictionary() {
		String val = getValue("word.dictionary.file");
		return val;
	}
	public static String getExOutputPath(){
		String val = getValue("extractor.outputPath");
		return val;
	}
	public static String getExImageDir(){
		String val = getValue("extractor.imageDir");
		return val;
	}
	public static String getExResourceDir(){
		String val = getValue("extractor.resourceDir");
		return val;
	}
	public static String getExResImageDir(){
		String val = getValue("extractor.resImageDir");
		return val;
	}
	public static String getCsdnProcessedDir(){
		String val = getValue("csdn.processed.directory");
		return val;
	}
	public static String getCsdnIndexDir(){
		String val = getValue("csdn.index.directory");
		return val;
	}
	public static String getCsdnDetailPageDir(){
		String val = getValue("csdn.detailPage.directory");
		return val;
	}
}
