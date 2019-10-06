package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class reads specified file line by line into List and word by word into map
 * @author andrey
 */
public class FileReader {

    /**
     * Reads file and returns it as list
     * @param filename - filename
     * @return list contains lines from file
     * @throws IOException
     */
    public List<String> readFileAsList(String filename) throws IOException {
        return Files.
                lines(Paths.get(filename)).
                collect(Collectors.toList());
    }

    /**
     * Reads file and returns it as map
     * @param filename - filename
     * @return inverted index map
     * @throws IOException
     */
    public Map<String, ArrayList<Integer>> readFileAsMap(String filename) throws IOException {
        var lineNumber = new int[1];
        var wordsIndex = new HashMap<String, ArrayList<Integer>>();
        Files.lines(Paths.get(filename))
                .forEach(line -> {
                    var splittedLine = line.split(" ");
                    for (String s : splittedLine) {
                        wordsIndex.putIfAbsent(s, new ArrayList<>());
                        wordsIndex.get(s).add(lineNumber[0]);
                    }
                    lineNumber[0]++;
                });
        return wordsIndex;
    }
}
