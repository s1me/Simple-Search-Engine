package strategy;

import searchengine.SearchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class to implement algorithm to find matches with NONE search strategy.
 */
public class StrategyNone implements FindStrategy {

    /**
     * Search for lines with no math line and query string
     *
     * @param query      - query string
     * @param wordsIndex - inverted index map
     * @param data       - List with stored strings
     * @return string with found matches
     */
    public List<String> findQuery(String query, Map<String, ArrayList<Integer>> wordsIndex, List<String> data) {
        var result = new ArrayList<String>(data);
        var words = query.split(" ");
        var keys = wordsIndex.keySet();

        Arrays.stream(words).forEach(word -> {
            keys.forEach(key -> {
                if (SearchEngine.containsIgnoreCase(key, word)) {
                    var indexes = wordsIndex.get(key);
                    indexes.forEach(index -> result.remove(data.get(index)));
                }
            });
        });

        return result;
    }
}
