
package datastructure.sorts;

import java.util.Arrays;

/**
 * 最好情况下，要排序的数据已经是有序，只需要进行一次冒泡操作，所以最好情况时间复杂度是O(n)。
 * 最坏的情况是，要排序的数据, 刚好是倒序排列的，我们需要进行n次冒泡操作，所以最坏情况时间复杂度为O(n2)。
 * 空间复杂度：冒泡的过程只涉及相邻数据的交换操作，只需要常量级的临时空间，所以它的空间复杂度为O(1)，是一个原地排序算法
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            // 提前退出
            boolean flag = false;
            for (int j = 0; j < arr.length - i -1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 冒泡排序改进:在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     * 对于边界外的元素在下次循环中无需比较.
     */
    public static void sort1(int[] arr) {
        if (arr.length < 1) {
            return;
        }
        // 无序数据的边界, 每次只需要比较到这里即可退出
        int sortBorder = arr.length - 1;
        // 最后一次数据交换的位置
        int lastChanged = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false; // 提前退出标志
            for (int j = 0; j < sortBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                    // 更新最后一次交换的位置
                    lastChanged = j;
                }
            }
            sortBorder = lastChanged;
            if (!flag) break;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 8, 7, 2, 20, 9};
        System.out.println(Arrays.toString(arr));
        sort1(arr);
        System.out.println(Arrays.toString(arr));
    }
}
