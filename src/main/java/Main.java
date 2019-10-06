import data.FileReader;
import data.InputFormatter;
import searchengine.SearchEngine;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            if (args.length < 2)
                throw new IOException("Please specify path to the file");

            var fileReader = new FileReader();
            var scanner = new Scanner(System.in);
            var searchEngine = new SearchEngine();
            int userAction = 0;
            var queryStrategy = "";
            var filename = "/home/andrey/IdeaProjects/searchengine/src/main/resources/persons.txt";

            searchEngine.load(fileReader.readFileAsList(filename), fileReader.readFileAsMap(filename));

            do {
                printMenu();
                userAction = scanner.nextInt();
                scanner.nextLine();

                switch (userAction) {
                    case 0:
                        printExitMessage();
                        break;

                    case 1:
                        System.out.println("Please select a searching strategy: ALL, ANY, NONE");
                        queryStrategy = scanner.nextLine();

                        System.out.println("\nEnter data to search people:");
                        String query = InputFormatter.deleteExtraWhitespaces(scanner.nextLine());
                        List<String> found = searchEngine.findQuery(query, queryStrategy);
                        printFoundResult(found);
                        break;

                    case 2:
                        printPersons(searchEngine);
                        break;

                    default:
                        System.out.println("Incorrect option! Try again.");
                }
            } while (userAction != 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    private static void printPersons(SearchEngine searchEngine) {
        System.out.println("=== List of people ===");
        for (String s : searchEngine.getData())
            System.out.println(s);
        System.out.println();
    }

    private static void printFoundResult(List<String> list) {
        for (String s : list)
            System.out.println(s);
        System.out.println();
    }

    private static void printExitMessage() {
        System.out.println("Bye!");
    }
}
