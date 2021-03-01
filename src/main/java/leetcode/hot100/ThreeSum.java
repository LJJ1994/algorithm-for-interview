package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//15. 三数之和
public class ThreeSum {
    private final List<List<Integer>> res = new ArrayList<>();
    // 1. 暴力法
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i; j < len - 1; j++) {
                for (int k = j; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        List<Integer> one = new ArrayList<>();
                        one.add(nums[i]);
                        one.add(nums[j]);
                        one.add(nums[k]);
                        res.add(one);
                    }
                }
            }
        }
        return res;
    }

    // 双指针法
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        for (int k = 0; k < nums.length; ++k) {
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    while(i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]);
                    while (i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum1(nums);
        for (List<Integer> list : lists) {
            Integer[] arr = (Integer[]) list.toArray(new Integer[list.size()]);
            System.out.println(Arrays.toString(arr));
        }
    }
}
