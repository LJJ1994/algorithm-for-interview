package leetcode.backtrace;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 全排列
public class Permute {
    private final List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
         LinkedList<Integer> trace = new LinkedList<>();
         backtrace(nums, trace);
         return res;
    }

    // 回溯法
    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    private void backtrace(int[] nums, LinkedList<Integer> trace) {
        int length = nums.length;
        if (trace.size() == length) {
            List<Integer> ans = new LinkedList<>(trace);
            res.add(ans);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (trace.contains(nums[i])) continue;
            trace.add(nums[i]);
            // 进入下一层决策树
            backtrace(nums, trace);
            // 回溯
            trace.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permute permute = new Permute();
        List<List<Integer>> result = permute.permute(nums);
//        for (List<Integer> integers : list) {
//            Integer[] result = (Integer[]) integers.toArray(new Integer[integers.size()]);
//            System.out.println(Arrays.toString(result));
//        }
        int[][] ints = result.stream()
                .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);

        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }
}