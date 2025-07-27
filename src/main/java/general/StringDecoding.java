package general;

public class StringDecoding {
    public static String stringDecoding(int N, String S) {
        // Step 1: Reverse the string
        StringBuilder reversedString = new StringBuilder(S).reverse();

        // Step 2: Shift each character back to the original
        StringBuilder originalString = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char ch = reversedString.charAt(i);
            // Shift back by one position in the alphabet
            if (ch == 'a') {
                originalString.append('z');
            } else {
                originalString.append((char) (ch - 1));
            }
        }

        // Return the decoded original string
        return originalString.toString();
    }

    public static void main(String[] args) {
        // Example usage:
        int N = 5; // length of the string
        String S = "abcde"; // encoded string
        String originalString = stringDecoding(N, S);
        System.out.println(originalString); // Output should be "abcdz"
    }
}