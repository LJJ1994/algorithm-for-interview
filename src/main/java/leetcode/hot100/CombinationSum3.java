package leetcode.hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 216. 组合总和 III
public class CombinationSum3 {
    private final List<List<Integer>> res = new ArrayList<>();

    /**
     *
     * @param k 个数
     * @param n 和
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums = new int[9];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
//        dfs(nums, k, n, new ArrayDeque<>(n), 0);
        dfs(k, n, 1, new ArrayDeque<>());
        return res;
    }

    private void dfs (int[] nums, int k, int n, Deque<Integer> path, int begin) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (k < 0 || n < 0) {
            return;
        }
        for (int i = begin; i < nums.length; ++i) {
            path.addLast(nums[i]);
            dfs(nums, k - 1, n - nums[i], path, i + 1);
            path.removeLast();
        }
    }
    private void dfs(int k, int n, int begin, Deque<Integer> path) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (k < 0 || n < 0) {
            return;
        }
        for (int i = begin; i <= 9; ++i) {
            path.addLast(i);
            dfs(k - 1, n - 1, i + 1, path);
            path.removeLast();
        }
    }
}
