package data;

/**
 * Class to format input string
 * @author andrey
 */
public class InputFormatter {

    /**
     * @param line - input line
     * @return formatted line without extra whitespaces
     */
    public static String deleteExtraWhitespaces(String line) {
        if (line == null || line.length() == 0)
            throw new IllegalArgumentException("method: \"deleteExtraWhitespaces\" param: line is null");

        var chars = line.trim().toCharArray();
        StringBuilder formattedLine = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                formattedLine.append(chars[i]);
            } else {
                while (chars[i] == ' ')
                    i++;
                i--;
                formattedLine.append(" ");
            }
        }

        return formattedLine.toString();
    }
}
