
package leetcode.stack;

import java.util.*;

// 150. 逆波兰表达式求值
public class EvalRPN {
    private final Deque<Integer> stack = new ArrayDeque<>();
    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        for (int i = 0; i < n; ++i) {
            String s = tokens[i];

            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int in = 0;
                switch(s) {
                    case "+":
                        in = num1 + num2;
                        stack.push(in);
                        break;
                    case "-":
                        in = num2 - num1;
                        stack.push(in);
                        break;
                    case "*":
                        in = num1 * num2;
                        stack.push(in);
                        break;
                    case "/":
                        in = (int)(num2 / num1);
                        stack.push(in);
                        break;
                }
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] strings = {"2", "1", "+", "3", "*"};
        EvalRPN evalRPN = new EvalRPN();
        int i = evalRPN.evalRPN(strings);
        System.out.println(i);

        System.out.println(Math.ceil(2.1));
        System.out.println(Math.floor(2.1));

    }
}
