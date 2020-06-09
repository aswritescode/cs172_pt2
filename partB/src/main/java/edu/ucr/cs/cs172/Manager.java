package edu.ucr.cs.cs172;

import java.io.*;
import org.apache.lucene.index.IndexWriter;
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
import java.util.ArrayList;
import java.util.Scanner;

/*
The main class for the application.
 */
public class Manager {

    private static final String INDEX_DIR = "lucene_index";

    public static String index_dir = INDEX_DIR;

    public static void main(String[] args) throws Exception {
        UI temp = new UI();
        boolean tButton = temp.enterButton;
        // Handle the input parameters
        switch (args.length) {
            case 1: // Set the index location
                index_dir = args[0];
            case 0:
            default:
                break;
        }


        //Scanner sc = new Scanner(System.in); //System.in is a standard input stream.
        //System.out.print("Enter a string: ");

        /*^Commented out Scanner variable declaration and declared in UI as a global variable
         in an attempt to link UI to search functionality*/

        if (temp.enterButton = true) { //Conditional statement that's supposed to link search functionality to UI
            String query = temp.userInput.nextLine(); //reads string.

            // Try reading a CSV File
            CSVParser parser = new CSVParser("0_tweets.tsv");
            parser.setCvsSplitBy("\t");                 // Set delimiter to tab for TSV file
            ArrayList<String[]> tweets = parser.read(); // Get the tweets

            IndexWriter writer = null;

            try {
                writer = Index.createWriter(INDEX_DIR);

                for (String[] tweet : tweets) {
                    writer.addDocument(Index.createDocument(tweet)); // Turn the tweets into documents, then add them to
                    // the index.
                }

                writer.commit();        // Commit the documents to the index
                ConsoleUtil.debug("Done comitting...");


            } catch (IOException e) {
                e.printStackTrace();
            }
            //IndexReader reader = IndexReader.open(FDSirectory.open(new File(index_dir)));
            //IndexSearcher searcher = null;
            try {
                IndexSearcher searcher = Index.createSearcher(INDEX_DIR); //create searcher
                TopDocs topresults = Index.searchIndex(query, searcher);

                int numresults = topresults.scoreDocs.length;

                String[] tweetsout = new String[numresults];
                ScoreDoc[] hits = topresults.scoreDocs;
                for (int i = 0; i < numresults; i++) {
                    Document d = Index.getDocument(hits[i], searcher);
                    String tweet = "User: " + d.get("username");
                    tweet += "\n";
                    tweet = tweet + "Tweet: " + d.get("body");
                    tweet += "\n";
                    tweet = tweet + "Location" + d.get("location");

                    tweetsout[i] = tweet;
                    System.out.println(tweet);
                }
                // searcher.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
