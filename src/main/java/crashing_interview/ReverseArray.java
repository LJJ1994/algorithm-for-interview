package crashing_interview;

import java.util.Arrays;

// 翻转数组
public class ReverseArray {
    public void reverse(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length / 2; ++i) {
            int other = length - i - 1;
            int temp = arr[i];
            arr[i] = arr[other];
            arr[other] = temp;
        }
    }

    public static void main(String[] args) {
        // 1. 5,2,3,4,1
        // 2. 5,4,3,2,1
        // 3. 5,4,3,2,1
        int[] arr = {1, 2, 3, 4, 5};
        ReverseArray reverseArray = new ReverseArray();
        reverseArray.reverse(arr);
        System.out.println(Arrays.toString(arr));
    }
}
