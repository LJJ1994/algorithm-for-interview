package datastructure.sorts;

import java.util.Arrays;


/**
 * 时间复杂度
 * 1. 最坏时间复杂度：O(n2)，嵌套循环每次迭代花费n次
 * 2. 最好时间复杂度: O(n), 输入序列有序，内嵌循环会终止
 */
public class InsertSort {
    public static void insertSort(int[] arr) {
        int j = 1;
        for (; j < arr.length; ++j) {
            int key = arr[j];
            int i = j - 1;
            // 将0~j-1 的元素与j位置元素比较，如果符合条件，则右移
            while (i >= 0 && arr[i] > key) {
                arr[i+1] = arr[i];
                i--;
            }
            // 找到要插入的位置，将j位置元素插入
            arr[i+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 7, 6, 0, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
