
package datastructure.sorts;

import java.util.Arrays;

/**
 * 时间复杂度
 * 1. 最坏时间复杂度：O(n2)
 * 2. 最好时间复杂度: O(n2)
 * 3. 空间复杂度: O(1)
 */
public class SelectSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        // 为什么是length - 1? 因为在第length - 1 次比较的时候， 剩下最后一个值肯定是最大的, 不需要比较。
        for (int i = 0; i < arr.length - 1; i++) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 找到最小值，交换
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 8, 7, 2, 20, 9};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}