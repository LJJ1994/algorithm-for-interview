package leetcode.hot100;

import java.util.Stack;

// 84. 柱状图中最大的矩形
public class LargestRectangleArea {
    private final Stack<Integer> stack = new Stack<>();
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();
        for (int j = n - 1; j >= 0; j--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[j]) {
                stack.pop();
            }
            right[j] = (stack.isEmpty() ? n : stack.peek());
            stack.push(j);
        }

        int ans = 0;
        for (int k = 0; k < n; ++k) {
            ans = Math.max(ans, (right[k] - left[k] - 1) * heights[k]);
        }
        return ans;
    }
}
