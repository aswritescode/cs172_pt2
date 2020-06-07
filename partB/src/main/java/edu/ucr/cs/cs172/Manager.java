package edu.ucr.cs.cs172;

import java.io.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.IndexWriter;
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
import java.util.ArrayList;
import java.util.Scanner;

/*
The main class for the application.
 */
public class Manager {

    private static final String INDEX_DIR = "lucene_index";

    public static String index_dir = INDEX_DIR;

    public static void main(String[] args) throws Exception{

        // Handle the input parameters
        switch(args.length) {
            case 1: // Set the index location
                index_dir = args[0];
            case 0:
            default:
                break;
        }


        Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
        System.out.print("Enter a QUERY: ");
        String query = sc.nextLine(); //reads string.
        System.out.println(query);

        // Try reading a CSV File
        CSVParser parser = new CSVParser("977_tweets.tsv");
        parser.setCvsSplitBy("\t");                 // Set delimiter to tab for TSV file
        ArrayList<String[]> tweets = parser.read(); // Get the tweets

        //IndexWriter writer = null;

        try {
           // writer = Index.createWriter(INDEX_DIR);
            FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            IndexWriter writer = new IndexWriter(dir, config);
            System.out.println("in try");
            //int b = 0;
            for (String[] tweet : tweets ) {
                writer.addDocument(Index.createDocument(tweet)); // Turn the tweets into documents, then add them to
                                                                 // the index.
                //b += 1;
              //  System.out.println(b);
                //System.out.println(" ");


               /* public static Document createDocument(String[] values)
                {
                    Document document = new Document();
                    document.add(new StringField("username", values[0] , Field.Store.YES));
                    document.add(new TextField("bio", values[1] , Field.Store.YES));
                    document.add(new TextField("location", values[2] , Field.Store.YES));
                    document.add(new TextField("body", values[3] , Field.Store.YES));
                    document.add(new TextField("urlTitle", values[4] , Field.Store.YES));
                    return document;
                }*/



               // System.out.println("hello");
            }

            writer.commit();        // Commit the documents to the index
            ConsoleUtil.debug("Done committing...");
            writer.close();
            dir.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        //IndexReader reader = IndexReader.open(FSDirectory.open(new File(index_dir)));
        //IndexSearcher searcher = null;
        try {
          //IndexSearcher searcher = Index.createSearcher(INDEX_DIR); //create searcher
            FSDirectory dir1 = FSDirectory.open(Paths.get(INDEX_DIR));
            IndexReader reader = DirectoryReader.open(dir1);
            IndexSearcher searcher = new IndexSearcher(reader);

            //TopDocs top_results = Index.searchIndex(query, searcher);
            QueryParser qp = new QueryParser("body", new StandardAnalyzer());
            Query idQuery = qp.parse(query);
            TopDocs top_results = searcher.search(idQuery, 10);


            System.out.println("hello");

            int numresults = top_results.scoreDocs.length;

            System.out.println(numresults);

            String[] tweetsout = new String [numresults];

            System.out.println(tweetsout.length);
            ScoreDoc[] hits = top_results.scoreDocs;

            for (int i = 0; i < numresults; i++) {
                Document d = Index.getDocument(hits[i], searcher);
                String tweetss = "User: " + d.get("username");
                tweetss += "\n";
                tweetss = tweetss + "Tweet: " + d.get("body");
                tweetss += "\n";
                tweetss = tweetss + "Location" + d.get("location");

                tweetsout[i] = tweetss;
                System.out.println(tweetsout[i]);
              //  System.out.println("i ");
            }
           // searcher.close();
            dir1.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
