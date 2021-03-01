package leetcode.backtrace;

import java.util.*;

// 187. 重复的DNA序列
public class FindRepeatedDnaSequences {
    private final List<String> res = new ArrayList<>();
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() == 0) {
            return res;
        }
        int L = 10, n = s.length();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n - L + 1; ++i) {
            String sub = s.substring(i, i + L);
            if (set.contains(sub)) {
                res.add(sub);
            } else {
                set.add(sub);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        FindRepeatedDnaSequences sequences = new FindRepeatedDnaSequences();
        List<String> list = sequences.findRepeatedDnaSequences(s);
        for (String s1 : list) {
            System.out.println(s1);
        }

    }
}
