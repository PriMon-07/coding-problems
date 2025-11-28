package problems.binary_search;

public class Search2DMatrix {

    public boolean searchMatrixStaircase(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public boolean searchMatrixBinarySearch(int[][] matrix, int target) {

        int row = matrix.length;
        int col = matrix[0].length;

        // finding the target's row index
        int top = 0;
        int bottom = row - 1;
        int theRow = -1;
        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;
            if (target >= matrix[mid][0] && target <= matrix[mid][col - 1]) {
                theRow = mid;
                break;
            } else if (matrix[mid][col - 1] < target) {
                top = mid + 1;
            } else {
                bottom = mid - 1;
            }
        }

        if (theRow < 0) {
            return false;
        }

        int left = 0;
        int right = col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (matrix[theRow][mid] == target) {
                return true;
            } else if (matrix[theRow][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public boolean searchMatrixBinarySearchOnePass(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                right = mid - 1;
                ;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search2DMatrix search2DMatrix = new Search2DMatrix();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;
        System.out.println(search2DMatrix.searchMatrixBinarySearchOnePass(matrix, target));
        System.out.println(search2DMatrix.searchMatrixBinarySearch(matrix, target));
        System.out.println(search2DMatrix.searchMatrixStaircase(matrix, target));

        int[][] matrix2 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target2 = 13;
        System.out.println(search2DMatrix.searchMatrixBinarySearchOnePass(matrix2, target2));
        System.out.println(search2DMatrix.searchMatrixBinarySearch(matrix2, target2));
        System.out.println(search2DMatrix.searchMatrixStaircase(matrix2, target2));
    }
}
