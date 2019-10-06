package searchengine;

import strategy.FindStrategy;
import strategy.StrategyAll;
import strategy.StrategyAny;
import strategy.StrategyNone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class represents simple search engine
 */
public class SearchEngine {

    private FindStrategy strategy;
    private List<String> data;
    private Map<String, ArrayList<Integer>> wordsIndexMap;

    public SearchEngine() {
        data = new ArrayList<>();
        wordsIndexMap = new HashMap<>();
    }

    /**
     * Initialize data structures with defined values
     * @param list list stored file's lines
     * @param map - inverted index map
     * @throws IOException
     */
    public void load(List<String> list, Map<String, ArrayList<Integer>> map) throws IOException {
        data = list;
        wordsIndexMap = map;
    }

    public List<String> getData() {
        return this.data;
    }

    public List<String> findQuery(List<String> lines, String query) {
        var result = new ArrayList<String>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (containsIgnoreCase(line, query))
                result.add(line);
        }

        if (result.size() == 0)
            result.add("Not found.");

        return result;
    }

    /**
     * Find query string in the file with specified search strategy
     * @param query
     * @param concreteStrategy
     * @return
     */
    public List<String> findQuery(String query, String concreteStrategy) {
        if (concreteStrategy.equalsIgnoreCase("ALL")) {
            this.strategy = new StrategyAll();
            return this.strategy.findQuery(query, wordsIndexMap, data);
        } else if (concreteStrategy.equalsIgnoreCase("ANY")) {
            this.strategy = new StrategyAny();
            return this.strategy.findQuery(query, wordsIndexMap, data);
        } else if (concreteStrategy.equalsIgnoreCase("NONE")) {
            this.strategy = new StrategyNone();
            return this.strategy.findQuery(query, wordsIndexMap, data);
        } else {
            ArrayList<String> list = new ArrayList<String>();
            list.add("Not found");
            return list;
        }
    }

    public static boolean containsIgnoreCase(String line, String query) {
        var lineArr = line.toLowerCase().toCharArray();
        var queryARr = query.toLowerCase().toCharArray();
        var used = new ArrayList<>();
        var shift = new int[256];

        for (int i = queryARr.length - 2, j = 1; i >= 0; i--) {
            if (!used.contains(queryARr[i])) {
                used.add(queryARr[i]);
                shift[queryARr[i]] = j++;
            }

            if (i == 0) {
                if (!used.contains(queryARr[queryARr.length - 1])) {
                    shift[queryARr[queryARr.length - 1]] = j;
                    used.add(queryARr[queryARr.length - 1]);
                }

                for (int g = 0; g < 256; g++) {
                    if (shift[g] == 0)
                        shift[g] = j;
                }
            }
        }

        for (int textPointer = queryARr.length - 1; textPointer < lineArr.length;) {
            int patternPointer = queryARr.length - 1;
            if (queryARr[patternPointer] == lineArr[textPointer]) {
                for (int j = 0; j < queryARr.length; j++) {
                    if (queryARr[patternPointer - j] != lineArr[textPointer - j]) {
                        textPointer += shift[queryARr[queryARr.length - 1]];
                        break;
                    }
                    if (j == queryARr.length - 1)
                        return true;
                }
            } else {
                textPointer += shift[lineArr[textPointer]];
            }
        }

        return false;
    }
}