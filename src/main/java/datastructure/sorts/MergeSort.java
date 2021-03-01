package datastructure.sorts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class MergeSort {

    public void mergeSort(int[] arr, int n) {
        mergeSortInternally(arr, 0, n-1);
    }

    private void mergeSortInternally(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = p + (r - p) / 2;
        // 分治递归
        mergeSortInternally(arr, p, q);
        mergeSortInternally(arr, q + 1, r);

//        merge(arr, p, q, r);
        // 利用哨兵合并
        mergeWithSentinel(arr, p, q, r);
    }

    /**
     *
     * @param arr 数组
     * @param p 数组起始位置
     * @param q 中间位置
     * @param r 结束位置
     */
    private void merge(int[] arr, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int[] temp = new int[r - p + 1];
        int k = 0;

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        // 默认左边数组剩余
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组
        while (start <= end) {
            temp[k++] = arr[start++];
        }

        //将数据复制回数组
        for (int l = 0; l < r- p + 1; l++) {
            arr[p + l] = temp[l];
        }
    }
    
    private void mergeWithSentinel(int[] arr, int p, int q, int r) {
        int[] left = new int[q-p+2];
        int[] right = new int[r-q+1]; // right的起点是 q + 1, 则 r - (q + 1) + 1 + 1 = r - q + 1

        for (int i = 0; i < q - p + 1; i++) {
            left[i] = arr[p + i];
        }
        // 左数组哨兵
        left[q - p + 1] = Integer.MAX_VALUE;

        for (int i = 0; i < r - (q + 1) + 1; i++) {
            right[i] = arr[q + 1 + i];
        }
        // 右数组哨兵
        right[r - q] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = p;
        while (k <= r) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 8, 7, 2, 20, 9};
        System.out.println(Arrays.toString(arr));
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
