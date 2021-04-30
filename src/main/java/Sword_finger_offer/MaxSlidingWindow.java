package Sword_finger_offer;

import java.util.*;

// 滑动窗口的最大值
// 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

/**
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 */
public class MaxSlidingWindow {
    private final MonotonicQueue window = new MonotonicQueue();
    private final List<Integer> res = new LinkedList<>();

    public int[] maxSlidingWindow(int[] nums, int k) {
        for (int i = 0; i < nums.length; ++i) {
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res.add(window.max());
                window.pop(nums[i - k + 1]);
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    // 单调队列
    private static class MonotonicQueue {
        private final LinkedList<Integer> data = new LinkedList<>();
        public void push(int n) {
            while (!data.isEmpty() && data.peekLast() < n) {
                data.pollLast();
            }
            data.offerLast(n);
        }

        public int max() {
            return data.peek();
        }

        public void pop(int n) {
            if (!data.isEmpty() && data.peek() == n) {
                data.pollFirst();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        MaxSlidingWindow slidingWindow = new MaxSlidingWindow();
        int[] ints = slidingWindow.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ints));
    }
}
