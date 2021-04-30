
package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 40. 组合总和 II
public class CombinationSum2 {
    private final List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length <= 0) return res;
        LinkedList<Integer> combinate = new LinkedList<>();
        // 关键步骤
        Arrays.sort(candidates);
        dfs(candidates, target, 0, combinate);
        return res;
    }

    private void dfs(int[] candidates, int target, int begin, LinkedList<Integer> combinate) {
        if (target == 0) {
            res.add(new ArrayList<>(combinate));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = begin; i < candidates.length; ++i) {
            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) continue;
            // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) {
                break;
            }
            combinate.addLast(candidates[i]);
            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, target - candidates[i], i + 1, combinate);
            combinate.removeLast();
        }
    }
}
