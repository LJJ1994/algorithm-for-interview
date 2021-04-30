
package leetcode.hot100;

import java.util.*;

// 77. 组合
public class Combine {
    private final List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
//        for (int i = 0; i < n; ++i) {
//            nums[i] = i + 1;
//        }
//        dfs(nums, k, new LinkedList<>(), 0);
        dfs(n, k, 1, new ArrayDeque<>(k));
        return res;
    }

    // 从二叉树的角度考虑，从根开始，对于数组中的每一个数，作出 选 or 不选，往下递归
    // 直到 k-- 为0
    private void dfs(int n, int k, int begin, Deque<Integer> path) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 递归终止条件
        if ( begin > n- k + 1) {
            return;
        }

        // 不考虑当前的节点，k 不变
        dfs(n, k, begin + 1, path);
        // 考虑当前节点，k - 1
        path.addLast(begin);
        dfs(n, k - 1, begin + 1, path);
        path.removeLast();
    }

    private void dfs(int[] nums, int k, LinkedList<Integer> path, int begin) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= nums.length - (k - path.size()) + 1; ++i) {
            path.addLast(nums[i]);
            dfs(nums, k, path, i + 1);
            path.removeLast();
        }
    }
}

