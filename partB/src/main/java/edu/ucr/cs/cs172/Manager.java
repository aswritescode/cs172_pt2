package edu.ucr.cs.cs172;


/*
The main class for the application.
 */
public class Manager {

    private static final String INDEX_DIR = "lucene_index";

    private static String index_dir = INDEX_DIR;

    public static void main(String[] args) {

        // Handle the input parameters
        switch(args.length) {
            case 1: // Set the index location
                index_dir = args[0];
            case 0:
            default:
                break;
        }

    }

}
