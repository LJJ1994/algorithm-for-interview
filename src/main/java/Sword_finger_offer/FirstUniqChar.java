package Sword_finger_offer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqChar {
    public char first(String s) {
        if (s.length() == 0 || s == null) {
            return ' ';
        }
        Map<Character, Boolean> map = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc) {
            map.put(c, !map.containsKey(c));
        }
        for (char c : sc) {
            if (map.get(c)) {
                return c;
            }
        }
        return ' ';
    }

    public char first01(String s) {
        char[] sc = s.toCharArray();
        Map<Character, Boolean> map = new LinkedHashMap<>();
        for (char c : sc) {
            map.put(c, !map.containsKey(c));
        }
        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        FirstUniqChar uniqChar = new FirstUniqChar();
        String s = "abaccdeff";
        System.out.println(uniqChar.first(s));
        System.out.println(uniqChar.first01(s));
    }
}
