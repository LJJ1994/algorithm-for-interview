package leetcode.hot100;

import java.util.*;

// 377. 组合总和 Ⅳ
public class CombinationSum4 {
    Map<Integer, Integer> memo = new HashMap<>();
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int res = dfs1(nums, target, 0);
        if (res == -1) {
            return 0;
        }
        return res;
    }

    // DP 完全背包问题
    public int combinationSum4Copy(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int i = 1; i <= target; ++i) {
                for (int num : nums) {
                    if (i >= num) {
                        dp[i] += dp[i - num];
                    }
                }
            }
            return dp[target];
    }

    private int dfs1(int[] nums, int target, int begin) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return -1;
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        int res = 0;
        for (int i = begin; i < nums.length; ++i) {
            if (target - nums[i] < 0) {
                break;
            }
            int result = dfs1(nums, target - nums[i], begin);
            if (result != -1) {
                res += result;
            }
        }
        memo.put(target, res);
        return res;
    }


}
