package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 39. 组合总和
public class CombinationSum {
    private final List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, path);
        return res;
    }

    private void dfs(int[] nums, int target, int begin, LinkedList<Integer> path) {
//        if (target < 0) {
//            return;
//        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < nums.length; ++i) {
            // 剪枝，提前终止该层递归
            if (target - nums[i] < 0) break;
            path.addLast(nums[i]);
            dfs(nums, target - nums[i], i, path);
            path.removeLast();
        }
    }
}
