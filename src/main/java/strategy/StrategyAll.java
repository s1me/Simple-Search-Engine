package strategy;

import searchengine.SearchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class to implement algorithm to find matches with ALL search strategy.
 */
public class StrategyAll implements FindStrategy {

    /**
     * Search for lines with full math line and query string
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
                        indexes.forEach(index -> {
                            int compared = 0;
                            var t = data.get(index);
                            for (String s : words) {
                                if (SearchEngine.containsIgnoreCase(t, s))
                                    compared++;
                            }
                            if (compared == words.length && !result.contains(t))
                                result.add(t);
                        });
                    });
        });

        if (result.size() == 0)
            result.add("Not found.");

        return result;
    }
}
