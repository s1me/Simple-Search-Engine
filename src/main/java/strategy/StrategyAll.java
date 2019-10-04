package strategy;

import searchengine.SearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class to implement algorithm to find matches with ALL search strategy.
 */
public class StrategyAll implements FindStrategy {

    /**
     * Search for lines with full math line and query string
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
                    for (int i = 0; i < indexes.size(); i++) {
                        int compared = 0;
                        String t = data.get(indexes.get(i));
                        for (int j = 0; j < words.length; j++) {
                            if (SearchEngine.containsIgnoreCase(t, words[j]))
                                compared++;
                        }
                        if (compared == words.length && !result.contains(t)) {
                            result.add(t);
                        }
                    }
                }
            }
        }

        if (result.size() == 0)
            result.add("Not found.");

        return result;
    }
}
