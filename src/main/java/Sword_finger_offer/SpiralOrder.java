package Sword_finger_offer;

// 顺时针打印矩阵
public class SpiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 结果数组
        int[] res = new int[rows * cols];
        int index = 0;
        int left = 0, right = cols - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int col = left; col <= right; ++col) {
                res[index++] = matrix[top][col];
            }
            for (int row = top + 1; row <= bottom; ++row) {
                res[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int col = right - 1; col > left; --col) {
                    res[index++] = matrix[bottom][col];
                }
                for (int row = bottom; row > top; --row) {
                    res[index++] = matrix[row][left];
                }
            }
            // 进入下一层循环
            left++;
            top++;
            right--;
            bottom--;
        }
        return res;
    }
}
