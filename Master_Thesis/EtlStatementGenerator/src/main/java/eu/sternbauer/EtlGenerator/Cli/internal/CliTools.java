package eu.sternbauer.EtlGenerator.Cli.internal;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Useful tools for Spring Shell CLI, especially with reading input (for elements)
 */
@Service
public class CliTools {

    /**
     * Accepts a list of strings that are displayed and returns the selected user element
     * @param list Strings which are displayed
     * @return The element the user selected
     */
    public String getInputForList(List<String> list) {
        return list.get(getInputForListIndex(list));
    }

    /**
     * Accepts a list of strings that are displayed and returns the selected index
     * @param list Strings which are displayed
     * @return The element's index, selected by the user. Between 0 and list.size-1
     */
    public int getInputForListIndex(List<String> list) {
        Scanner scanner = new Scanner(System.in);
        int index = -1;
        do {
            System.out.println(listingWithNumbering(list));
            System.out.print("Enter a number (0-" + (list.size()-1) + "): ");
            String input = scanner.nextLine();
            try {
                index = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, please try again!");
            }
        } while (index < 0 || index >= list.size());

        return index;
    }

    /**
     * Lists a passed list with numbers, starting with index 0 and returns the resulting string
     * @param list Elements to be listed
     * @return string with the listing content
     */
    public String listingWithNumbering(List<String> list) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, list.size()).forEach(i ->
                sb.append("(").append(i).append(") - ").append(list.get(i)).append("\n"));
        return sb.toString();
    }

    /**
     * Reads the next line from the input via a scanner.
     * @return Line from the system input
     */
    public String getInputLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
