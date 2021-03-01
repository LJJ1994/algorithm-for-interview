package leetcode.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//20. 有效的括号
public class BracketsValid {
    private final Stack<Character> stack = new Stack<>();
    private final Map<Character, Character> pairs = new HashMap<Character, Character>() {{
        put(')', '(');
        put(']', '[');
        put('}', '{');
    }};

    public boolean isValid(String s) {
        if (s == null || s.length() == 0 || s == "") return true;
        if (s.length() % 2 == 1) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            if (pairs.containsKey(c)) {
                if (stack.empty() || stack.peek() != pairs.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }
}
