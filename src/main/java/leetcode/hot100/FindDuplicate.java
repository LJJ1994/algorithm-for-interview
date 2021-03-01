package leetcode.hot100;

import java.util.Arrays;
import java.util.Set;

// 寻找重复的数
public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[]{-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (ans[0] != nums[i]) {
                ans[0] = nums[i];
            } else {
                ans[1] = nums[i];
            }
        }
        return ans[0];
    }
}
