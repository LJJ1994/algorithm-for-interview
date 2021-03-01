package leetcode.hot100;

import java.util.Deque;
import java.util.LinkedList;

// 394. 字符串解码
public class DecodeString {
    private final Deque<Integer> stack_multi = new LinkedList<>();
    private final Deque<String> stack_str = new LinkedList<>();
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[') {
                stack_multi.push(multi);
                stack_str.push(res.toString());
                res = new StringBuilder();
                multi = 0;
            } else if (chars[i] == ']') {
                Integer multi1 = stack_multi.pop();
                String lastStr = stack_str.pop();
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < multi1; ++j) {
                    temp.append(res);
                }
                res = new StringBuilder(lastStr + temp);
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                multi = multi * 10 + (chars[i] - '0');
            } else {
                res.append(chars[i]);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String str = "3[a]2[bc]";
        DecodeString decodeString = new DecodeString();
        String s = decodeString.decodeString(str);
        System.out.println(s);
    }
}
