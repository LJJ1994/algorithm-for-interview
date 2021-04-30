
package leetcode.hot100;

// 64. 最小路径和
public class MinPathSum {
    // 动态规划
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        // dp[row][col] 表示在到达坐标(row,col)时的最小路径总和
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (i > 0 && j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }

    // 动态规划优化版
    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    grid[i][j] = grid[0][0];
                } else if (i == 0 && j > 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (i > 0 && j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[rows - 1][cols - 1];
    }
}
