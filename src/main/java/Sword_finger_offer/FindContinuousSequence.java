package Sword_finger_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 剑指 Offer 57 - II. 和为s的连续正数序列
public class FindContinuousSequence {
    private final List<int[]> res = new ArrayList<>();
    public int[][] findContinuousSequence(int target) {
        if (target <= 2) {
            return new int[0][0];
        }
        int[] nums = new int[target];
        for (int i = 0; i < target; ++i) {
            nums[i] = i + 1;
        }
        int upper = target / 2 + 1;
        int i = 0;
        int j = 1;
        int sum = nums[i] + nums[j];
        while (i <= upper && j <= upper) {
            if (sum == target) {
                res.add(compute(nums, i, j));
                sum -= nums[i];
                i++;
                j++;
                sum = sum + nums[j];
            } else if (sum < target) {
                j++;
                sum += nums[j];
            } else {
                sum -= nums[i];
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    private int[][] convert(List<List<Integer>> res) {
        return res.stream()
                .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }

    private int[] compute(int[] nums, int i, int j) {
        int[] one = new int[j - i + 1];
        for (int k = i; k <= j; ++k) {
            one[k - i] = nums[k];
        }
        return one;
    }
}
