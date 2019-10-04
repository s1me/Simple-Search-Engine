package searchengine;

import data.MyFileReader;
import strategy.FindStrategy;
import strategy.StrategyAll;
import strategy.StrategyAny;
import strategy.StrategyNone;

import java.io.IOException;
import java.util.*;

public class SearchEngine {

    private FindStrategy strategy;
    private List<String> data;
    private Map<String, ArrayList<Integer>> wordsIndexMap;

    public SearchEngine() {
        data = new ArrayList<String>();
        wordsIndexMap = new HashMap<String, ArrayList<Integer>>();
    }

    public void init(String filename) throws IOException {
        MyFileReader fileReader = new MyFileReader();
        fileReader.readFile(filename, data, wordsIndexMap);
    }

    public List<String> getData() {
        return this.data;
    }

    public List<String> findQuery(List<String> lines, String query) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (containsIgnoreCase(line, query))
                result.add(line);
        }

        if (result.size() == 0)
            result.add("Not found.");

        return result;
    }

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
        String lowerCaseLine = line.toLowerCase();
        String lowerCaseQuery = query.toLowerCase();
        return lowerCaseLine.contains(lowerCaseQuery);
    }
}