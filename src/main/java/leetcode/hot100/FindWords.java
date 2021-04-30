
package leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

// 212. 单词搜索 II
public class FindWords {
    private final List<String> res = new ArrayList<>();
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return res;
        }
        int rows = board.length;
        int cols = board[0].length;

        next:
        for (String word : words) {
            boolean[][] visited = new boolean[rows][cols];
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    boolean hasPath = dfs(board, visited, board.length, board[0].length, i, j, 0, word);
                    if (hasPath) {
                        res.add(word);
                        // 当前该 word 已搜索到，则不再进行搜索，退回到第一次循环开始下一轮循环。
                        continue next;
                    }
                }
            }
        }

        return res;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int rows, int cols, int row, int col,
                     int pathLength, String word) {
        if (pathLength == word.length()) {
            return true;
        }

        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols
                && word.charAt(pathLength) == board[row][col] && !visited[row][col]) {
            ++pathLength;
            visited[row][col] = true;
            hasPath = dfs(board, visited, rows, cols, row - 1, col, pathLength, word)
                    || dfs(board, visited, rows, cols, row + 1, col, pathLength, word)
                    || dfs(board, visited, rows, cols, row, col - 1, pathLength, word)
                    || dfs(board, visited, rows, cols, row, col + 1, pathLength, word);
            if (!hasPath) {
                --pathLength;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }
}
