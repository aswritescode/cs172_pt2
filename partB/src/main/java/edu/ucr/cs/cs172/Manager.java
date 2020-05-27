package edu.ucr.cs.cs172;


import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.ArrayList;

/*
The main class for the application.
 */
public class Manager {

    private static final String INDEX_DIR = "lucene_index";

    public static String index_dir = INDEX_DIR;

    public static void main(String[] args) {

        // Handle the input parameters
        switch(args.length) {
            case 1: // Set the index location
                index_dir = args[0];
            case 0:
            default:
                break;
        }

        // Try reading a CSV File
        CSVParser parser = new CSVParser("0_tweets.tsv");
        parser.setCvsSplitBy("\t");                 // Set delimiter to tab for TSV file
        ArrayList<String[]> tweets = parser.read(); // Get the tweets

        IndexWriter writer = null;
        try {
            writer = Index.createWriter(INDEX_DIR);

            for (String[] tweet : tweets ) {
                writer.addDocument(Index.createDocument(tweet)); // Turn the tweets into documents, then add them to
                                                                 // the index.
            }

            writer.commit();        // Commit the documents to the index
            ConsoleUtil.debug("Done comitting...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
