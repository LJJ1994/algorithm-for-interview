
package leetcode.hot100;

import java.util.*;

// 239. 滑动窗口最大值
public class MaxSlidingWindow1 {
    private final List<Integer> res = new LinkedList<>();
    private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return new int[0];
        }
        for (int i = 0; i < nums.length; ++i) {
            if (i < k -1) {
                maxHeap.add(nums[i]);
            } else {
                maxHeap.add(nums[i]);
                res.add(maxHeap.peek());
                maxHeap.remove(nums[i - k + 1]);
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        MaxSlidingWindow1 window1 = new MaxSlidingWindow1();
        int[] ints = window1.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ints));
    }
}