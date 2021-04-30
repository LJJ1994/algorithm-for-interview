package crashing_interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// 有一个值都不相同的整数数组，计算两个数差值为 k 的对数
public class Difference {
    private List<Integer> queue = new LinkedList<>();
    // hash table
    public int diff(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = arr.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            map.put(arr[i], arr[i]);
        }

        for (int i = 0; i < length; i++) {
            if (map.containsKey(arr[i] + k)) {
                count += 1;
                System.out.println("(" + arr[i] + ", " + (arr[i] + k) + ")");
            }
        }
        return count;
    }

    // 蛮力法
    public int diff01(int[] arr, int k) {
        int length = arr.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; ++j) {
                if (Math.abs(arr[i] - arr[j]) == k) {
                    count += 1;
                    System.out.println("(" + arr[i] + ", " + arr[j] + ")");
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Difference difference = new Difference();
        int[] arr = {1, 7, 5, 9, 2, 12, 3};
        System.out.println(difference.diff(arr, 2));
        System.out.println("==========================");
        System.out.println(difference.diff01(arr, 2));
    }
}