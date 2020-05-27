package edu.ucr.cs.cs172;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

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
    public static Document createDocument(String username, String bio, String location, String body, String urlTitle)
    {
        Document document = new Document();
        document.add(new StringField("username", username , Field.Store.YES));
        document.add(new TextField("bio", bio , Field.Store.YES));
        document.add(new TextField("location", location , Field.Store.YES));
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
        document.add(new TextField("body", values[3] , Field.Store.YES));
        document.add(new TextField("urlTitle", values[4] , Field.Store.YES));
        return document;
    }


}
