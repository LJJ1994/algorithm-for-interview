package Sword_finger_offer;

/**
 * 二维数组中的查找
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target=5，返回true。
 *
 * 给定target=20，返回false。
 */
public class FindNumberIn2DArray {
    // 暴力查找
    public boolean findNumberIn2DArray(int[][] matrix, int num) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    // 线性查找
    public boolean findNumberIn2DArray01(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = 0;
        int columns = matrix[0].length - 1;
        while ( rows <= (matrix.length - 1) && columns >= 0) {
            if (matrix[rows][columns] == target) {
                return true;
            } else if (matrix[rows][columns] < target) { // 向下查找
                rows++;
            } else { // 向左查找
                columns--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // 6
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        FindNumberIn2DArray findNumberIn2DArray = new FindNumberIn2DArray();
        boolean numberIn2DArray = findNumberIn2DArray.findNumberIn2DArray(matrix, 30);
        System.out.println(numberIn2DArray);
    }
}
