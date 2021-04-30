
package leetcode.hot100;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int maxValue = nums[0];
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], nums[i]), min * nums[i]);
            min = Math.min(Math.min(min * nums[i], nums[i]), temp * nums[i]);
            maxValue = Math.max(maxValue, max);
        }

        return maxValue;
    }
}
