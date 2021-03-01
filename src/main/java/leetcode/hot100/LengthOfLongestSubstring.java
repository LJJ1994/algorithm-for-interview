package leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

// 3. 无重复字符的最长子串
public class LengthOfLongestSubstring {
    private int res = 0;
    private final Map<Character, Integer> windows = new HashMap<>();
    public int lengthOfLongestSubstring(String s) {
        // 1.不太直观的解法
        // int n = s.length();
        // int ans = 0;
        // Map<Character, Integer> map = new HashMap<>();
        // for (int start = 0, end = 0; end < n; ++end) {
        //     Character ch = s.charAt(end);
        //     if (map.containsKey(ch)) {
        //         start = Math.max(start, map.get(ch));
        //     }
        //     ans = Math.max(ans, end - start + 1);
        //     map.put(s.charAt(end), end + 1);
        // }
        // return ans;

        // 2.滑动窗口法(较为直观)
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char ch1 = s.charAt(right);
            windows.put(ch1, windows.getOrDefault(ch1, 0) + 1);
            right++;

            // 滑动窗口出现重复字符
            // 则移动left指针，一直到重复元素的第二个
            // （例如abcddefgn, 则left移到第二个'd', 然后right继续向右移动）
            while (windows.get(ch1) > 1) {
                char ch2 = s.charAt(left);
                windows.put(ch2, windows.get(ch2) - 1);
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcddef";
        LengthOfLongestSubstring longestSubstring = new LengthOfLongestSubstring();
        int i = longestSubstring.lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}
