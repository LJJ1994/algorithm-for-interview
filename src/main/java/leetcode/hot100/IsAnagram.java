package leetcode.hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 242. 有效的字母异位词
public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

//        char[] s1 = s.toCharArray();
//        char[] t1 = t.toCharArray();
//
//        Arrays.sort(s1);
//        Arrays.sort(t1);
//
//        return Arrays.equals(s1, t1);
//        Map<Character, Integer> store = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            Character ch = s.charAt(i);
//            store.put(ch, store.getOrDefault(ch, 0) + 1);
//        }
//
//        for (int j = 0; j < t.length(); j++) {
//            Character ch = t.charAt(j);
//            store.put(ch, store.getOrDefault(ch, 0) - 1);
//            if (store.get(ch) < 0) {
//                return false;
//            }
//        }
//        return true;

        int[] letters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            letters[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            letters[t.charAt(i) - 'a']--;
        }
        for (int j = 0; j < letters.length; ++j) {
            if (letters[j] != 0) {
                return false;
            }
        }
        return true;
    }
}
