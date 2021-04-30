
package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// 494. 目标和
public class FindTargetSumWays {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) sum += n;
        // 这两种情况，不可能存在合法的子集划分
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        return subsets1(nums, (sum + S) / 2);
    }

    private int subsets(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= nums[i-1]) {
                    // 两种选择的结果之和
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                } else {
                    // 背包的空间不足，只能选择不装物品 i
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

    // 3. 0-1背包空间优化
    private int subsets1(int[] nums, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = 0; i< nums.length; ++i) {
            for (int j = sum; j >= nums[i]; --j) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        FindTargetSumWays sumWays = new FindTargetSumWays();
        int ways = sumWays.findTargetSumWays(nums, 3);
        System.out.println(ways);
    }
}
