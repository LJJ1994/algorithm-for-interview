package Sword_finger_offer;

/**
 * 矩阵中的路径
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
 * 每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 *
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 *
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 提示：
 *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 *
 */

public class MatrixPath {
    public boolean exit(char[][] board, String word) {
        int rows = board.length;
        int columns = board[0].length;
        if (board == null || rows < 1 || columns < 1 || word == null ) {
            return false;
        }
        boolean[][] visited = new boolean[rows][columns];
        int pathLength = 0;
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                if (hasPathCore(board, rows, columns, row, column, word, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPathCore(char[][] board, int rows, int columns, int row, int column,
                                String word, int pathLength, boolean[][] visited) {
        if (word.length() - 1 == pathLength) {
            return true;
        }
        boolean hasPath = false;
        if (row >= 0 && row < rows && column >= 0 && column < columns
                && board[row][column] == word.charAt(pathLength)) {
            ++pathLength;
            visited[row][column] = true;
            hasPath = hasPathCore(board, rows, columns, row, column - 1, word, pathLength, visited) ||
                    hasPathCore(board, rows, columns, row + 1, column, word, pathLength, visited) ||
                    hasPathCore(board, rows, columns, row, column + 1, word, pathLength, visited) ||
                    hasPathCore(board, rows, columns, row - 1, column, word, pathLength, visited);

            if (!hasPath) { // 当前位置 n的上下左右都找不到word中的下一个(n+1)字符，回退到n-1位置的字符.
                --pathLength;
                visited[row][column] = false;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        char[][] chars = {
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}
        };
        String canFounded = "bfce";
        String canNotFounded = "afe";

        MatrixPath matrixPath = new MatrixPath();
        System.out.println(matrixPath.exit(chars, canFounded));
        System.out.println(matrixPath.exit(chars, canNotFounded));

        char[][] chars1 = {{'a'}};
        String s = "a";
        System.out.println(matrixPath.exit(chars1, s));
    }
}
