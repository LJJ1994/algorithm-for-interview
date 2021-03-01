package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 336. 回文对
public class PalindromePairs {
    private final List<List<Integer>> res = new ArrayList<>();
    // 暴力法（超时拉！）
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) {
            return res;
        }
        for (int i = 0; i < words.length; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                String s1 = splice(words[i], words[j]);
                String s2 = splice(words[j], words[i]);
                boolean flag1 = isPalindrome(s1);
                boolean flag2 = isPalindrome(s2);
                if (flag1) {
                    List<Integer> one = new ArrayList<>();
                    one.add(i);
                    one.add(j);
                    res.add(one);
                }
                if (flag2) {
                    List<Integer> one = new ArrayList<>();
                    one.add(j);
                    one.add(i);
                    res.add(one);
                }
            }
        }
        return res;
    }

    private String splice(String s1, String s2) {
        return s1 + s2;
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            ++i;
            --j;
        }
        return  true;
    }
}
