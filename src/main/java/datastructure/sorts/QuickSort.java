package datastructure.sorts;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuickSort {
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int lo, int hi) {
        if (arr.length <= 1) {
            return;
        }
        if (lo < hi) {
            int pivot = partition(arr, lo, hi);
            sort(arr, lo, pivot - 1);
            sort(arr, pivot + 1, hi);
        }
    }

    private int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        // 选取数组第一个值作为pivot
        int pivot = arr[lo];
        while (true) {
            // 左指针i向右扫描找到第一个大于等于pivot的元素
            while (less(arr[++i], pivot)) {
                if (i == hi) break;
            }
            // 右指针向左扫描找到第一个小于等于pivot的元素
            while (less(pivot, arr[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;

            swap(arr, i, j);
        }
        // j此时到达左侧数组的最右侧
        // 将pivot 放到正确的位置
        swap(arr, lo, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean less(int i, int j) {
        return i - j < 0;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[20];
        System.out.println("------------------排序前-------------------");
        for (int i = 0; i < 20; i++) {
            int a = random.nextInt(100);
            arr[i] = a;
            System.out.print(a + ", ");
        }
        System.out.println();
        System.out.println("-----------------排序后——-------------------");
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }

        List list = new ArrayList();

    }
}
