package leetcode.hot100;

import java.util.*;

// 239. 滑动窗口最大值
public class MaxSlidingWindow {
    private final MonotonicQueue window = new MonotonicQueue();
    private final List<Integer> res = new ArrayList<>();

    // 穷举法
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            output[i] = max;
        }
        return output;
    }

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
        private final Deque<Integer> data = new ArrayDeque<>();

        public void push(Integer n) {
            while (!data.isEmpty() && data.peekLast() < n) {
                data.removeLast();
            }
            data.addLast(n);
        }

        public int max() {
            return data.peek();
        }

        public void pop(int n) {
            if (!data.isEmpty() && data.peekFirst() == n) {
                data.removeFirst();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        int[] ints = maxSlidingWindow.maxSlidingWindow(nums, k);

        MaxSlidingWindow1 window1 = new MaxSlidingWindow1();
        int[] ints1 = window1.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(ints1));
    }
}
