package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Base strategy class
 */
public interface FindStrategy {

    /**
     * Abstract method to find query string in stored lines
     * @param query - query string
     * @param wordsIndex - inverted index map
     * @param data - List with stored strings
     * @return list with matched strings
     */
    List<String> findQuery(String query, Map<String, ArrayList<Integer>> wordsIndex, List<String> data);
}
