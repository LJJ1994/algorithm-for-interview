package Sword_finger_offer;

// 礼物的最大价值
public class MaxValueGift {
    public int maxValue(int[][] grid) {
        int cols = grid[0].length;
        int rows = grid.length;
        if (grid == null || cols <= 0 || rows <= 0) return 0;
        int[][] maxValues = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = maxValues[i - 1][j];
                }
                if (j > 0) {
                    left = maxValues[i][j - 1];
                }
                maxValues[i][j] = Math.max(left, up) + grid[i][j];
            }
        }
        int maxValue = maxValues[rows - 1][cols - 1];
        return maxValue;
    }
}
