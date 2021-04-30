
package leetcode.hot100;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 76. 最小覆盖子串
public class MinWindow {
    // 存储 t 中的字符
    private final Map<Character, Integer> needs = new HashMap<>();
    // 当前滑动窗口包含的字符
    private final Map<Character, Integer> windows = new HashMap<>();
    // 当前滑动窗口中字符匹配次数
    private int matches = 0;
    // 最小值
    private int minLen = Integer.MAX_VALUE;
    // 滑动窗口
    public String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        int start = 0;

        for (int i = 0; i < t.length(); ++i) {
            char ch = t.charAt(i);
            needs.put(ch, needs.getOrDefault(ch, 0) + 1);
        }
        while (right < s.length()) {
            char ch1 = s.charAt(right);
            if (needs.containsKey(ch1)) {
                windows.put(ch1, windows.getOrDefault(ch1, 0) + 1);
                if (windows.get(ch1).equals(needs.get(ch1))) {
                    matches++;
                }
            }
            // 滑动窗口的字符还没有达到条件，继续移动right指针
            right++;

            // 当滑动窗口达到条件，更新最小值；移动left指针
            while (matches == needs.size()) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - start;
                }
                char ch2 = s.charAt(left);
                // left指针向右移动，滑动窗口缩小
                // 如果left指向的元素存在于needs中，则匹配数 matches - 1，则会跳出该while循环，
                // right则会向右继续滑动，left不动。
                if (needs.containsKey(ch2)) {
                    windows.put(ch2, windows.get(ch2) - 1);
                    if (windows.get(ch2) < needs.get(ch2)) {
                        matches--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}

