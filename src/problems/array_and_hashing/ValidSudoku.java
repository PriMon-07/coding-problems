package problems.array_and_hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Valid Sudoku
 * <p>
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <ul>
 *   <li>Each row must contain the digits 1-9 without repetition.</li>
 *   <li>Each column must contain the digits 1-9 without repetition.</li>
 *   <li>Each of the nine 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.</li>
 * </ul>
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * <h3>Example 1:</h3>
 * <pre>
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * </pre>
 *
 * <h3>Example 2:</h3>
 * <pre>
 * Input:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same number in first column twice: 8
 * </pre>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>board.length == 9</li>
 *   <li>board[i].length == 9</li>
 *   <li>board[i][j] is a digit or '.'</li>
 * </ul>
 */
public class ValidSudoku {

    /**
     * Checks if a given 9x9 Sudoku board is valid using a hashing approach.
     * <p>
     * A Sudoku board is valid if the following conditions are met:
     * 1. Each row contains the digits 1-9 without repetition.
     * 2. Each column contains the digits 1-9 without repetition.
     * 3. Each of the nine 3x3 submatrices contains the digits 1-9 without repetition.
     * <p>
     * Time Complexity: O(n^2), due to the fixed size of the board.
     * Space Complexity: O(n), due to the fixed size of the hash set.
     *
     * @param board the given Sudoku board
     * @return {@code true} if the board is valid, otherwise {@code false}
     */
    public boolean isValidSudokuHashing(char[][] board) {
        Set<Character> set;

        for (int i = 0; i < 9; i++) {
            set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[i][j]) && !set.add(board[i][j])) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[j][i]) && !set.add(board[j][i])) {
                    return false;
                }
            }
        }

        for (int k = 0; k < 9; k++) {
            int p = (k / 3) * 3;
            int q = (k % 3) * 3;
            set = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Character.isDigit(board[i + p][j + q]) && !set.add(board[i + p][j + q])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    /**
     * Validates a 9x9 Sudoku board using a single pass approach.
     * <p>
     * This method checks each row, column, and 3x3 sub-grid for duplicate digits.
     * It uses three sets to track the occurrences of digits in each row, column, and sub-grid
     * during a single iteration over the board.
     * <p>
     * Time Complexity: O(n^2), as the board size is fixed at 9x9.
     * Space Complexity: O(n), due to the fixed size of the sets used.
     *
     * @param board the 9x9 Sudoku board represented as a 2D character array,
     *              where empty cells are represented by '.' and filled cells
     *              contain digits '1' through '9'.
     * @return {@code true} if the board is valid according to Sudoku rules, otherwise {@code false}.
     */
    public boolean isValidSudokuHashingOnePass(char[][] board) {
        Set<Character> setRow;
        Set<Character> setColumn;
        Set<Character> setSubMatrix;

        for (int i = 0; i < 9; i++) {
            setRow = new HashSet<>();
            setColumn = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[i][j]) && !setRow.add(board[i][j])) {
                    return false;
                }
                if (Character.isDigit(board[j][i]) && !setColumn.add(board[j][i])) {
                    return false;
                }
            }

            int p = (i / 3) * 3;
            int q = (i % 3) * 3;
            setSubMatrix = new HashSet<>();
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    if (Character.isDigit(board[k + p][j + q]) && !setSubMatrix.add(board[k + p][j + q])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        char[][] validBoard = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] invalidBoard = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        ValidSudoku validator = new ValidSudoku();

        System.out.println("Valid Board with Hashing: " + validator.isValidSudokuHashing(validBoard));
        System.out.println("Invalid Board with Hashing: " + validator.isValidSudokuHashing(invalidBoard));

        System.out.println("Valid Board with Hashing One Pass: " + validator.isValidSudokuHashingOnePass(validBoard));
        System.out.println("Invalid Board with Hashing One Pass: " + validator.isValidSudokuHashingOnePass(invalidBoard));
    }
}
