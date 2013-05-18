package com.yangxu.searchengine.index;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.yangxu.searchengine.data.Blog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Index all text files under a directory.
 * <p>
 * This is a command-line application demonstrating simple Lucene indexing. Run
 * it with no command-line arguments for usage information.
 */
public class IndexFiles {
	private final String indexPath = "e:\\crawler\\temp\\blog.csdn.net\\index\\";
	private final String docsPath = "e:\\crawler\\temp\\blog.csdn.net\\processed\\";

	private IndexFiles() {

	}

	/**
	 * 
	 * @param createOrUpdate
	 *            为真的时候 是create 为假的时候是update
	 */
	public void createIndex(boolean createOrUpdate) {
		if (docsPath == null) {
			System.err.println("docsPath not exists!");
			System.exit(1);
		}

		final File docDir = new File(docsPath);
		if (!docDir.exists() || !docDir.canRead()) {
			System.out
					.println("Document directory '"
							+ docDir.getAbsolutePath()
							+ "' does not exist or is not readable, please check the path");
			System.exit(1);
		}

		Date start = new Date();
		try {
			System.out.println("Indexing to directory '" + indexPath + "'...");

			Directory dir = FSDirectory.open(new File(indexPath));
			Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_31);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31,
					analyzer);

			if (createOrUpdate) {
				// Create a new index in the directory, removing any
				// previously indexed documents:
				iwc.setOpenMode(OpenMode.CREATE);
			} else {
				// Add new documents to an existing index:
				iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			}

			// Optional: for better indexing performance, if you
			// are indexing many documents, increase the RAM
			// buffer. But if you do this, increase the max heap
			// size to the JVM (eg add -Xmx512m or -Xmx1g):
			//
			// iwc.setRAMBufferSizeMB(256.0);

			IndexWriter writer = new IndexWriter(dir, iwc);
			indexDocs(writer, docDir);

			// NOTE: if you want to maximize search performance,
			// you can optionally call forceMerge here. This can be
			// a terribly costly operation, so generally it's only
			// worth it when your index is relatively static (ie
			// you're done adding documents to it):
			//
			// writer.forceMerge(1);

			writer.close();

			Date end = new Date();
			System.out.println(end.getTime() - start.getTime()
					+ " total milliseconds");

		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass()
					+ "\n with message: " + e.getMessage());
		}
	}

	/**
	 * Indexes the given file using the given writer, or if a directory is
	 * given, recurses over files and directories found under the given
	 * directory.
	 * 
	 * NOTE: This method indexes one document per input file. This is slow. For
	 * good throughput, put multiple documents into your input file(s). An
	 * example of this is in the benchmark module, which can create "line doc"
	 * files, one document per line, using the <a href=
	 * "../../../../../contrib-benchmark/org/apache/lucene/benchmark/byTask/tasks/WriteLineDocTask.html"
	 * >WriteLineDocTask</a>.
	 * 
	 * @param writer
	 *            Writer to the index where the given file/dir info will be
	 *            stored
	 * @param file
	 *            The file to index, or the directory to recurse into to find
	 *            files to index
	 * @throws IOException
	 */
	private void indexDocs(IndexWriter writer, File file) throws IOException {
		// do not try to index files that cannot be read
		// 读取title
		String titleValue = null;
		// 读取content
		String contentValue = null;
		String urlValue = null;
		String indextimeValue = null;
		String uploadtimeValue = null;
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				// an IO error could occur
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexDocs(writer, new File(file, files[i]));
					}
				}
			} else {
				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
					LineNumberReader reader = new LineNumberReader(
							new InputStreamReader(fis, "UTF-8"));
					String line = null;
					StringBuilder sb = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						// int lineNumber = reader.getLineNumber();
						switch (reader.getLineNumber()) {
						case 1:
							urlValue = line;
							break;
						case 2:
							uploadtimeValue = line;
							break;
						case 3:
							titleValue = line.split(":")[1];
							break;
						case 4:
							break;
						default:
							sb.append(line);
							break;

						}
						/*
						 * if (reader.getLineNumber() == 1) { urlValue = line; }
						 * 
						 * if (reader.getLineNumber() == 3) { titleValue =
						 * line.split(":")[1]; } else if (reader.getLineNumber()
						 * > 4) { sb.append(line); }
						 */
					}
					contentValue = sb.toString();
					reader.close();

				} catch (FileNotFoundException fnfe) {
					// at least on windows, some temporary files raise this
					// exception with an "access denied" message
					// checking if the file can be read doesn't help
					return;
				}

				try {
					// make a new, empty document
					Document doc = new Document();

					// Add the path of the file as a field named "path". Use a
					// field that is indexed (i.e. searchable), but don't
					// tokenize
					// the field into separate words and don't index term
					// frequency
					// or positional information:

					Field urlField = new Field("url", urlValue,
							Field.Store.YES, Field.Index.NOT_ANALYZED);
					urlField.setIndexOptions(IndexOptions.DOCS_ONLY);
					doc.add(urlField);

					Field titleField = new Field("title", titleValue,
							Field.Store.YES, Field.Index.ANALYZED);
					titleField.setIndexOptions(IndexOptions.DOCS_ONLY);
					doc.add(titleField);

					Field contentField = new Field("content", contentValue,
							Field.Store.YES, Field.Index.ANALYZED);
					contentField.setIndexOptions(IndexOptions.DOCS_ONLY);
					doc.add(contentField);
					// Add the last modified date of the file a field named
					// "modified".
					// Use a NumericField that is indexed (i.e. efficiently
					// filterable with
					// NumericRangeFilter). This indexes to milli-second
					// resolution, which
					// is often too fine. You could instead create a number
					// based on
					// year/month/day/hour/minutes/seconds, down the resolution
					// you require.
					// For example the long value 2011021714 would mean
					// February 17, 2011, 2-3 PM.					
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy年MM月dd日 HH:mm:ss");
					//Calendar cal = Calendar.getInstance(); // 创建一个日历对象。
					//timeValue = formatter.format(cal.getTime());
					Date now = new Date();				
					indextimeValue = formatter.format(now);
					
					Field indextimeField = new Field("indextime", indextimeValue,
							Field.Store.YES, Field.Index.NOT_ANALYZED);
					titleField.setIndexOptions(IndexOptions.DOCS_ONLY);
					doc.add(indextimeField);
					
					Field uploadtimeField = new Field("uploadtime", uploadtimeValue,
							Field.Store.YES, Field.Index.NOT_ANALYZED);
					titleField.setIndexOptions(IndexOptions.DOCS_ONLY);
					doc.add(uploadtimeField);

					// Add the contents of the file to a field named "contents".
					// Specify a Reader,
					// so that the text of the file is tokenized and indexed,
					// but not stored.
					// Note that FileReader expects the file to be in UTF-8
					// encoding.
					// If that's not the case searching for special characters
					// will fail.
					// doc.add(new Field("contents", new BufferedReader(
					// new InputStreamReader(fis, "UTF-8"))));

					if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
						// New index, so we just add the document (no old
						// document can be there):
						System.out.println("adding " + file);
						writer.addDocument(doc);
					} else {
						// Existing index (an old copy of this document may have
						// been indexed) so
						// we use updateDocument instead to replace the old one
						// matching the exact
						// path, if present:
						System.out.println("updating " + file);
						writer.updateDocument(
								new Term("url", urlValue), doc);
						writer.updateDocument(new Term("title", titleValue),
								doc);
						writer.updateDocument(
								new Term("content", contentValue), doc);
						writer.updateDocument(
								new Term("indextime", String.valueOf(indextimeValue)), doc);
						writer.updateDocument(
								new Term("uploadtime", String.valueOf(uploadtimeValue)), doc);
					}

				} finally {
					fis.close();
				}
			}
		}
	}

	/** Index all text files under a directory. */
	public static void main(String[] args) {
		boolean createOrUpdate = true;
		IndexFiles indexFiles = new IndexFiles();
		indexFiles.createIndex(createOrUpdate);
	}

}
