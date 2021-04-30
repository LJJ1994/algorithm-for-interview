package Sword_finger_offer;

import java.util.Stack;

// 顺时针打印矩阵
public class PrintMatrixClockwisely {
    public void  spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length <=0 || matrix[0].length <= 0) {
            return;
        }
        int rows = matrix.length;;
        int columns = matrix[0].length;
        int start = 0;
        while (start * 2 < columns && start * 2 < rows) {
            printMatrixInCircle(matrix, columns, rows, start);
            ++start;
        }
    }

    private void printMatrixInCircle(int[][] matrix, int columns, int rows, int start) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;

        // 从左到右打印一行
        for (int i = start; i <= endX; ++i) {
            int number = matrix[start][i];
            printNumber(number);
        }

        // 从上到下打印一列
        if (start < endY) {
            for (int i = start + 1; i <= endY; ++i) {
                int number = matrix[i][endX];
                printNumber(number);
            }
        }

        // 从右到左打印一行
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; --i) {
                int number = matrix[endY][i];
                printNumber(number);
            }
        }

        // 从下到上打印一列
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; --i) {
                int number = matrix[i][start];
                printNumber(number);
            }
        }
    }

    private void printNumber(int num) {
        System.out.print(num + ", ");
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        PrintMatrixClockwisely clockwisely = new PrintMatrixClockwisely();
        clockwisely.spiralOrder(matrix);
    }
}
