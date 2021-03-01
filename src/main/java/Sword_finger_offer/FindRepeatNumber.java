package Sword_finger_offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 */
public class FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        int n = nums.length;
        Map<Integer, Boolean> Map = new HashMap<>();
        for(int i = 0; i < n; ++i) {
            if(Map.containsKey(nums[i])) {
                return nums[i];
            } else {
                Map.put(nums[i], true);
            }
        }
        return -1;
    }

    public int findRepeatNumber01(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        int repeat = -1;
        for (int i = 0; i < n; ++i) {
            if (!set.add(nums[i])) {
                repeat = nums[i];
                break;
            }
        }
        return repeat;
    }

    public static void main(String[] args) {
        FindRepeatNumber number = new FindRepeatNumber();
        int[] nums = {2, 3, 3, 0, 2, 5, 3};
        int[] nums01 = {1,2,3,4};
//        int repeatNumber = number.findRepeatNumber(nums);
        int repeatNumber = number.findRepeatNumber01(nums);
        System.out.println(repeatNumber);

        int number01 = number.findRepeatNumber01(nums01);
        System.out.println(number01);
    }
}
