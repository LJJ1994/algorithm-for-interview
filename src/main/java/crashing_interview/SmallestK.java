package crashing_interview;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingDeque;

// 面试题 17.14. 最小K个数
public class SmallestK {
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 小根堆
    public int[] smallestK(int[] arr, int k) {
        int[] res = new int[k];
        if (arr == null || arr.length == 0) {
            return res;
        }
        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i]);
        }
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    // 快速排序
    public int[] smallestK1(int[] arr, int k) {
        if (arr == null || arr.length == 0) return new int[0];
        int lo = 0;
        int hi = arr.length - 1;
        while (true) {
            int pivot = partition(arr, lo, hi);
            if (pivot == k) {
                break;
            } else if (pivot > k) {
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        // 选取数组第一个值作为pivot
        int pivot = arr[lo];
        while (true) {
            // 左指针i向右扫描找到第一个大于等于pivot的元素
            while (less(arr[++i], pivot)) if (i == hi) break;
            // 右指针向左扫描找到第一个小于等于pivot的元素
            while (less(pivot, arr[--j])) if (j == lo) break;
            if (i >= j) break;
            swap(arr, i, j);
        }
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
        int[] nums = {1,3,5,7,2,4,6,8};
        int k = 4;
        SmallestK smallestK = new SmallestK();
//        int[] ints = smallestK.smallestK(nums, k);
        int[] ints = smallestK.smallestK1(nums, k);
        for (int anInt : ints) {
            System.out.print(anInt + ",");
        }

    }
}
