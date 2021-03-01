package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 47. 全排列 II
public class PermuteUnique {
    private final List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length <= 0) return res;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, boolean[] used, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, used, path);
            used[i] = false;
            path.removeLast();
        }
    }
}
