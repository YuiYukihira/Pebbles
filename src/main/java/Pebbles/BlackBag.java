package Pebbles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BlackBag extends Bag {
    public BlackBag() {
        stones = new ArrayList<Integer>();
    }

    public BlackBag(ArrayList<Integer> l) {
        stones = l;
    }

    public static BlackBag fromCSV(String filename) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        ArrayList<Integer> flattened = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(csvSplitBy);
                Integer[] intLine = new Integer[splitLine.length];
                for (int i=0; i < splitLine.length; i++) {
                    intLine[i] = Integer.parseInt(splitLine[i]);
                }
                flattened.addAll(Arrays.asList(intLine));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new BlackBag(flattened);
    }
}
