/* @author Xu Yang;
   @time 2013-5-17
*/
package com.yangxu.searchengine.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;

/**
 * 网页提取类父类
 * @author yangxu
 *
 */
public abstract class Extractor {

	protected static final String NEWLINE = "\r\n";

	/**
	 * 表示所有结果的输出路径
	 */
	private String outputPath;

	/**
	 * 表示当前正在被处理的文件
	 */
	protected String inuputFilePath;

	/**
	 * 表示当前所有被抓取的网页的目录
	 */
	private String resourceDir;

	private FileInputStream fis ;
	/**
	 * HTMLParser的实例
	 */
	private Parser parser;

	/**
	 * 对图片路径进行哈希的算法，这里采用MD5算法
	 */
	protected static final String HASH_ALGORITHM = "md5";

	/**
	 * 分隔符
	 */
	public static final String SEPARATOR = "======================";

	/**
	 * 装载需要的网页文件
	 * 
	 */
	public void loadFile(String path) {
		try {
			parser = new Parser(path);
			inuputFilePath = path;
			fis = new FileInputStream(path);
			parser.setEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取输出的路径
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * 设置输出的路径，通常在初始化Extractor时就应该做
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public Parser getParser() {
		return parser;
	}

	/**
	 * 使用正则来匹配并获得网页中的字符串
	 */
	protected String getProp(String pattern, String match, int index) {
		Pattern sp = Pattern.compile(pattern);
		Matcher matcher = sp.matcher(match);
		while (matcher.find()) {
			return matcher.group(index);
		}
		return null;
	}

	

	/**
	 * 抽象方法，用于供子类实现。 其功能主要是解释网页文件 将产品信息保存到
	 * 
	 */
	public abstract void extract();

	/**
	 * 获取正在处理的文件的路径
	 */
	public String getInuputFilePath() {
		return inuputFilePath;
	}
	
	/**
	 * 获取资源文件目录
	 * @return
	 */
	public String getResourceDir() {
		return resourceDir;
	}

	public void setResourceDir(String resourceDir) {
		this.resourceDir = resourceDir;
	}

	public void setInuputFilePath(String inuputFilePath) {
		this.inuputFilePath = inuputFilePath;
	}


	// 统计文件个数
	static int count = 0;

	/**
	 * 遍历所有的文件，并用extractor处理
	 * @param extractor
	 * @param path
	 * @throws Exception
	 */
	public static void traverse(Extractor extractor, File path)
			throws Exception {
		if (path == null) {
			return;
		}
		if (path.isDirectory()) {
			String[] files = path.list();
			for (int i = 0; i < files.length; i++) {
				traverse(extractor, new File(path, files[i]));
			}
		} else {
			String pathname = path.getAbsolutePath();
			System.out.println(pathname);
			count++;
			extractor.loadFile(path.getAbsolutePath());
			extractor.extract();
		}
	}
	
	
	public FileInputStream getFis() {
		return fis;
	}

	public void setFis(FileInputStream fis) {
		this.fis = fis;
	}

	

}
