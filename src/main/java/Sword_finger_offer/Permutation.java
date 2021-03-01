package Sword_finger_offer;

import java.util.*;

// 剑指offer 字符串排列
public class Permutation {
    private Deque<Integer> deque = new ArrayDeque<>();
    private final List<String> res = new ArrayList<>();
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        dfs(chars, visited, new StringBuilder());
        return res.toArray(new String[res.size()]);
    }

    private void dfs(char[] chars, boolean[] visited, StringBuilder path) {
        if (path.length() == chars.length) {
            res.add(path.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!visited[i]) {
                if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                    continue;
                }
                visited[i] = true;
                path.append(chars[i]);
                dfs(chars, visited, path);
                path.deleteCharAt(path.length() - 1);
                visited[i] = false;
            }
        }
    }
}
