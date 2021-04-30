
package leetcode.strings;

import java.util.LinkedHashMap;
import java.util.Map;

// 387. 字符串中的第一个唯一字符
public class FirstUniqChar {
    private final LinkedHashMap<Character, int[]> store = new LinkedHashMap<>();
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) return -1;
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (store.containsKey(c)) {
                // int[0] ==> index, int[1] ==> count
                int[] ints = store.get(c);
                ints[1] += 1;
                store.put(c, ints);
            } else {
                int[] ints1 = new int[2];
                ints1[0] = i;
                ints1[1] = 1;
                store.put(c, ints1);
            }
        }

        for (Map.Entry<Character, int[]> entry : store.entrySet()) {
            int[] ints = entry.getValue();
            if (ints[1] == 1) {
                return ints[0];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String s1 = "loveleetcode";
        FirstUniqChar aChar = new FirstUniqChar();
        int i = aChar.firstUniqChar(s);
        int i1 = aChar.firstUniqChar(s1);
        System.out.println(i);
        System.out.println(i1);
    }
}
