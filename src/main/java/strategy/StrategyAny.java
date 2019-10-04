package strategy;

import searchengine.SearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class to implement algorithm to find matches with ANY search strategy.
 */
public class StrategyAny implements FindStrategy {

    /**
     * Search for lines where matches at least one word from query string
     * @param query - query string
     * @param wordsIndex - inverted index map
     * @param data - List with stored strings
     * @return string with found matches
     */
    public List<String> findQuery(String query, Map<String, ArrayList<Integer>> wordsIndex, List<String> data) {
        List<String> result = new ArrayList<String>();
        String[] words = query.split(" ");
        Set<String> keys = wordsIndex.keySet();

        for (String word : words) {
            for (String key : keys) {
                if (SearchEngine.containsIgnoreCase(key, word)) {
                    ArrayList<Integer> indexes = wordsIndex.get(key);
                    for (int i : indexes) {
                        String t = data.get(i);
                        if (!result.contains(t))
                            result.add(data.get(i));
                    }
                }
            }
        }

        if (result.size() == 0)
            result.add("Not found.");

        return result;
    }
}
