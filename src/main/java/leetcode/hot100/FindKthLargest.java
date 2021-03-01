package leetcode.hot100;

import java.util.*;

//215. 数组中的第K个最大元素
public class FindKthLargest {
    // 最大堆来比较
    private final PriorityQueue<Integer> max = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            max.offer(nums[i]);
        }
        for (int i = 0; i < k - 1; ++i) {
            max.poll();
        }
        return max.peek();
    }
}
