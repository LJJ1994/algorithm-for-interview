package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 71. 简化路径
public class SimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!item.isEmpty() && !item.equals(".")) {
                stack.push(item);
            }
        }
        String res = "";
        for (String d : stack) {
            res = "/" + d + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    public static void main(String[] args) {
        String path = "/a/./b/../../c/";
        String[] split = path.split("/");
        System.out.println(Arrays.toString(split));
//        SimplifyPath simplifyPath = new SimplifyPath();
//        String s = simplifyPath.simplifyPath(path);
//        System.out.println(s);
    }
}
