package general;

import java.util.Scanner;

public class TrolleyKeyExtractor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        scanner.close();

        // Initialize an empty StringBuilder to build the result
        StringBuilder key = new StringBuilder();

        // Iterate over the message string
        for (int i = 0; i < message.length(); i++) {
            // Check if the current character is the same as the previous character
            if (i == 0 || message.charAt(i) != message.charAt(i - 1)) {
                // Append the character to the key if it's not the same as the previous one
                key.append(message.charAt(i));
            }
        }

        // Print the result
        System.out.println(key.toString());
    }
}