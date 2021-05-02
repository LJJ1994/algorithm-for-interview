package leetcode.strings;

import java.util.HashSet;
import java.util.Set;

// 面试题 01.01. 判定字符是否唯一
public class IsUnique {
    public boolean isUnique(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }
}
