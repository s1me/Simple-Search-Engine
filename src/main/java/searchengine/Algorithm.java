package searchengine;

import java.util.ArrayList;
import java.util.List;


/**
 * The Boyer-Moore-Horspool Algorithm
 */
public class Algorithm {

    public static void main(String[] args) {
        String text = "Hello world my name is Andrey";
        String pattern = "Hello";

        System.out.println(contains(text, pattern));
    }

    public static boolean contains(String line, String query) {
        char[] lineArr = line.toCharArray();
        char[] queryARr = query.toCharArray();
        List<Character> used = new ArrayList<Character>();
        int[] shift = new int[256];

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

        for (int textPointer = queryARr.length - 1; textPointer <= lineArr.length;) {
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
