package edu.ucr.cs.cs172;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParser {

    // Class variables
    String csvFile = "";
    String line = "";
    String cvsSplitBy = ",";

    //Constructors

    public CSVParser() {
    }

    public CSVParser(String path) {
        this.csvFile = path;
    }

    // Class Methods

    public ArrayList<String[]> read() {

        ArrayList<String[]> results = new ArrayList<String[]>();

        if(csvFile == "") return results;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] values  = line.split(cvsSplitBy);
                results.add(values);
                //ConsoleUtil.debug(values[0] + " " + values[1] + " " + values[2] + " " + values[3] + " " + values[4]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }


    // Class Helper Methods


    // Accessor Methods

    public String getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCvsSplitBy() {
        return cvsSplitBy;
    }

    public void setCvsSplitBy(String cvsSplitBy) {
        this.cvsSplitBy = cvsSplitBy;
    }



}
