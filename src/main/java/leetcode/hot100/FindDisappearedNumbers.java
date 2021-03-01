package leetcode.hot100;

import java.util.*;

// 448. 找到所有数组中消失的数字
public class FindDisappearedNumbers {
    private final List<Integer> res = new ArrayList<>();
    private final Set<Integer> store = new HashSet<>();
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            store.add(nums[i]);
        }

        for (int i = 0; i < n; i++) {
            if (!store.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }
}
