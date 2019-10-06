package data;

import java.io.*;
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
public class MyFileReader {

//    /**
//     *
//     * @param filename - file with data
//     * @param lines - structure where file saved line by line
//     * @param wordsIndexMap Inverted index, for each word stores number of line where word occurs
//     * @throws IOException
//     */
//    public void readFile(String filename, List<String> lines, Map<String, ArrayList<Integer>> wordsIndexMap) throws IOException {
//        File file = new File(filename);
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String line;
//        int lineNum = 0;
//
//        while ((line = reader.readLine()) != null) {
//            String formattedLine = InputFormatter.deleteExtraWhitespaces(line);
//            lines.add(formattedLine);
//            String[] words = formattedLine.split(" ");
//
//            for (String word : words) {
//                ArrayList<Integer> currList = wordsIndexMap.get(word);
//                if (currList == null)
//                    currList = new ArrayList<Integer>();
//                currList.add(lineNum);
//                wordsIndexMap.put(word, currList);
//            }
//
//            lineNum++;
//        }
//        reader.close();
//    }

    public List<String> readFileAsList(String filename) throws IOException {
        return Files.
                lines(Paths.get(filename)).
                collect(Collectors.toList());
    }

    public Map<String, ArrayList<Integer>> readFileAsMap(String filename) throws IOException {
        var lineNumber = new int[1];
        var wordsIndex = new HashMap<String, ArrayList<Integer>>();
        Files.lines(Paths.get(filename))
                .forEach(line -> {
                    var splittedLine = line.split(" ");
                    for (int i = 0; i < splittedLine.length; i++) {
                        wordsIndex.putIfAbsent(splittedLine[i], new ArrayList<>());
                        wordsIndex.get(splittedLine[i]).add(lineNumber[0]);
                    }
                    lineNumber[0]++;
                });
        return wordsIndex;
    }
}
