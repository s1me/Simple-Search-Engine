package strategy;

import searchengine.SearchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class to implement algorithm to find matches with ANY search strategy.
 */
public class StrategyAny implements FindStrategy {

    /**
     * Search for lines where matches at least one word from query string
     *
     * @param query      - query string
     * @param wordsIndex - inverted index map
     * @param data       - List with stored strings
     * @return string with found matches
     */
    public List<String> findQuery(String query, Map<String, ArrayList<Integer>> wordsIndex, List<String> data) {
        var result = new ArrayList<String>();
        var words = query.split(" ");
        var keys = wordsIndex.keySet();

        Arrays.stream(words).forEach(word -> {
            keys.stream()
                    .filter(key -> SearchEngine.containsIgnoreCase(key, word))
                    .forEach(key -> {
                        var indexes = wordsIndex.get(key);
                        indexes.forEach(index -> result.add(data.get(index)));
                    });
        });

        if (result.size() == 0)
            result.add("Not found.");

        return result;
    }
}
