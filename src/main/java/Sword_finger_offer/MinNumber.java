package Sword_finger_offer;

import java.util.Arrays;
import java.util.Comparator;

// 把数组排成最小的数字
public class MinNumber {
    public String minNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            result.append(res[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int[] nums01 = {3,30,34,5,9};
        int[] nums02 = {10, 2};
        int[] nums03 = {};
        MinNumber minNumber = new MinNumber();
        System.out.println(minNumber.minNumber(nums01));
        System.out.println(minNumber.minNumber(nums02));
        System.out.println(minNumber.minNumber(nums03));
    }
}
