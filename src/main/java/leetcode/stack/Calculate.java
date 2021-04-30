
package leetcode.stack;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.LinkedList;
import java.util.Stack;

// 224. 基本计算器
public class Calculate {
    private int i = 0;
    private final Stack<Integer> stack = new Stack<>();
    public int calculate(String s) {
        return helper(s);
    }

    private int helper(String s) {
        char sign = '+';
        int num = 0;
        for (; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                ++i;
                num = helper(s);
            }

            if ((!isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        Integer pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        Integer pre1 = stack.pop();
                        stack.push(pre1 / num);
                        break;
                }
                sign = c;
                num = 0;
            }

            if (c == ')') {
                break;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    private boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
