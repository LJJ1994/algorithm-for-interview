
package leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 子集
public class Subsets {
    private final List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, int begin, LinkedList<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; ++i) {
            path.addLast(nums[i]);
            dfs(nums, begin + 1, path);
        }
    }
}

