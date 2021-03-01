package Sword_finger_offer;

import java.util.Arrays;

public class ReorderOddEven {
    public void reoder(int[] arr, int length) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int begin = 0;
        int end = length - 1;
        while (begin < end) {
            // begin指针一直往后直到找到一个偶数
            while (begin < end && (arr[begin] & 1) != 0) {
                begin++;
            }
            // end指针一直往前直到找到一个奇数
            while (begin < end && (arr[end] & 1) == 0) {
                end--;
            }
            if (begin < end) {
                int temp = arr[begin];
                arr[begin] = arr[end];
                arr[end] = temp;

            }
        }
    }

    public static void main(String[] args) {
        ReorderOddEven oddEven = new ReorderOddEven();
        int[] arr = {1,2,3,4,5};
        oddEven.reoder(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
