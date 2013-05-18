/**
 *@author 杨旭，创建日期:2013-5-16
 *
 */
package com.yangxu.searchengine.extractor;

import java.io.*;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.ObjectFindingVisitor;

public class ExtratorCsdn extends Extractor {

	@Override
	public void extract() {

		String fileName = this.getInuputFilePath().substring(
				this.getInuputFilePath().lastIndexOf("\\") + 1);
		// System.out.println(fileName);
		OutputStreamWriter writer = null;
		// 提取URL
		// 提取TIME
		try {

			writer = new OutputStreamWriter(new FileOutputStream(
					this.getOutputPath() + fileName + ".txt"), "UTF-8");
			LineNumberReader lbr = new LineNumberReader(new InputStreamReader(
					this.getFis(), "UTF-8"));
			String line = null;
			while ((line = lbr.readLine()) != null) {
				if (lbr.getLineNumber() > 2) {
					break;
				}
				// System.out.println(line);
				writer.append(line);
				writer.append(NEWLINE);
			}

			lbr.close();
			// 提取TITLE
			// 标题过滤器
			NodeFilter filter_title = new TagNameFilter("title");// title节点过滤
			NodeList nodelist1 = this.getParser().extractAllNodesThatMatch(
					filter_title);// 过滤得符合过滤要求的节点的LIST
			Node node_title = nodelist1.elementAt(0);// 取节点
			String title = null;
			if (node_title == null) {// 判断是否为空
				title = null;
			} else {
				title = node_title.toPlainTextString();// 把节点里的文本节点转化为String
														// 加到buftitle上
			}
			writer.append("Title:" + title);
			writer.append(NEWLINE);
			// 提取Content
			this.getParser().reset();// 重置
			NodeFilter article_content_Filter = new AndFilter(
					new TagNameFilter("div"), new HasAttributeFilter("class",
							"article_content"));
			NodeList article_content_list = this.getParser().parse(
					article_content_Filter);
			String content = null;
			if (article_content_list.size() == 0) {
				content = "页面内容不存在，也许是页面不存在导致！";
			} else {
				Node contentNode = article_content_list.elementAt(0);
				content = contentNode.toPlainTextString().trim().toString();
				content = content.replaceAll("&nbsp;", "");
				// 剔除content中的一些空白字符
				if (content.contains("&nbsp")) {
					content.replaceAll("&nbsp", "");
				}
			}
			writer.append("Content:");
			writer.append(NEWLINE);
			writer.append(content.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Extractor extractor = new ExtratorCsdn();
		extractor
				.setOutputPath("e:\\crawler\\temp\\blog.csdn.net\\processed\\");
		extractor
				.setResourceDir("e:\\crawler\\temp\\blog.csdn.net\\detailPage\\");
		try {
			traverse(extractor, new File(extractor.getResourceDir()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finised!");

	}

}
