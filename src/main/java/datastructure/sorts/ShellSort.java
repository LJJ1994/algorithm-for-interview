package datastructure.sorts;

import java.util.Arrays;

public class ShellSort {
    public static void sort(int[] arr) {
        int j;
        int gap = arr.length / 2;
        for (; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                for (j = i; (j >= gap) && tmp < arr[j - gap]; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }

    public static void genericSort(int[] arr) {
        int length = arr.length;
        int gap = 1;
        while (gap < length / 3) {
            gap = gap * 3 + 1;
        }

        for (; gap > 0; gap /= 3) {
            for (int i = gap; i < length; i++) {
                int j;
                int tmp = arr[i];
                for (j = i; j >= gap && (tmp < arr[j -gap]); j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15};
//        sort(arr);
        genericSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}