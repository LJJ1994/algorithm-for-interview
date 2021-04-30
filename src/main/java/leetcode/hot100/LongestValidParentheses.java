
package leetcode.hot100;

import java.util.Deque;
import java.util.LinkedList;

// 32. 最长有效括号
public class LongestValidParentheses {
    // 1. DP
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = ((i - 2 > 0) ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) =='(') {
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // 栈
    public int longestValidParentheses1(String s) {
        int max = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addFirst(i);
            } else {
                stack.removeFirst();
                if (stack.isEmpty()) {
                    stack.addFirst(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
