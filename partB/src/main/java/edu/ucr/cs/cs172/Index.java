package edu.ucr.cs.cs172;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.queryparser.classic.QueryParser;

import java.io.IOException;
import java.nio.file.Paths;

public class Index {

    // Generates an instance of an IndexWriter for an index located at "indexDir"
    public static IndexWriter createWriter(String indexDir) throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    // Generates a document object given a set of input fields. These fields are Twitter object fields that can be parsed from a CSV file.
    public static Document createDocument(String username, String bio, String location, String date, String body, String urlTitle)
    {
        Document document = new Document();
        document.add(new StringField("username", username , Field.Store.YES));
        document.add(new TextField("bio", bio , Field.Store.YES));
        document.add(new TextField("location", location , Field.Store.YES));
        document.add(new TextField("date", date, Field.Store.YES));
        document.add(new TextField("body", body , Field.Store.YES));
        document.add(new TextField("urlTitle", urlTitle , Field.Store.YES));
        return document;
    }

    // Generates a document object given an array of values parsed from a tweet
    public static Document createDocument(String[] values)
    {
        Document document = new Document();
        document.add(new StringField("username", values[0] , Field.Store.YES));
        document.add(new TextField("bio", values[1] , Field.Store.YES));
        document.add(new TextField("location", values[2] , Field.Store.YES));
        document.add(new TextField("date", values[3], Field.Store.YES));
        document.add(new TextField("body", values[4] , Field.Store.YES));
        //document.add(new TextField("urlTitle", values[5] , Field.Store.YES));
        return document;
    }

    public static IndexSearcher createSearcher(String indexDir) throws IOException//creates index searcher from index
    {
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }

    public static TopDocs searchIndex(String query, IndexSearcher searcher) throws Exception //returns the top docks of query
    {
        QueryParser qp = new QueryParser(query, new StandardAnalyzer());
        Query idQuery = qp.parse(query);
        TopDocs hits = searcher.search(idQuery, 10);
        return hits;
    }

    //private static TopDocs ranker()
    //{
        //TODO
    //}

    public static Document getDocument(ScoreDoc scoreDoc, IndexSearcher searcher) throws CorruptIndexException, IOException //returns the top doc
    {
        return searcher.doc(scoreDoc.doc);
    }

}
