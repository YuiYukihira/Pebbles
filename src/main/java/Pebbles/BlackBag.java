package Pebbles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BlackBag extends Bag {
    private final IBag refillBag;

    BlackBag(IBag refillBag, Vector<Integer> l) {
        super(l);
        this.refillBag = refillBag;
    }

    public synchronized void refill() {
        while (refillBag.size() > 0) {
            addStone(refillBag.removeStone(0));
        }
        notifyAll();
    }

    public static BlackBag fromCSV(IBag refillBag, int numPlayers, String filename) {
        BufferedReader br = null;
        String line;
        String csvSplitBy = ",";
        Vector<Integer> flattened = new Vector<>();
        boolean includesNegatives = false;
        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(csvSplitBy);
                Integer[] intLine = new Integer[splitLine.length];
                for (int i=0; i < splitLine.length; i++) {
                    int val = Integer.parseInt(splitLine[i]);
                    if (val <= 0 && !includesNegatives) {
                        includesNegatives = true;
                    }
                    intLine[i] = val;
                }
                flattened.addAll(Arrays.asList(intLine));
            }
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
        if ((numPlayers*11) > flattened.size()) {
            throw new IllegalArgumentException("Bag starting contents must have at least 11 times the number of players (" + numPlayers*11 + " > " + flattened.size() + ") in stones (file: " + filename + ")");
        }
        if (includesNegatives) {
            throw new IllegalArgumentException("Bag must contain strictly positively weighted stones (file: " + filename + ")");
        }
        return new BlackBag(refillBag, flattened);
    }
}
