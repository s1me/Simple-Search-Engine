package strategy;

import searchengine.SearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class to implement algorithm to find matches with NONE search strategy.
 */
public class StrategyNone implements FindStrategy {

    /**
     * Search for lines with no math line and query string
     * @param query - query string
     * @param wordsIndex - inverted index map
     * @param data - List with stored strings
     * @return string with found matches
     */
    public List<String> findQuery(String query, Map<String, ArrayList<Integer>> wordsIndex, List<String> data) {
        List<String> result = new ArrayList<String>(data);
        String[] words = query.split(" ");
        Set<String> keys = wordsIndex.keySet();

        for (String word : words) {
            for (String key : keys) {
                if (SearchEngine.containsIgnoreCase(key, word)) {
                    ArrayList<Integer> indexes = wordsIndex.get(key);
                    for (int i : indexes) {
                        result.remove(data.get(i));
                    }
                }
            }
        }

        return result;
    }
}
