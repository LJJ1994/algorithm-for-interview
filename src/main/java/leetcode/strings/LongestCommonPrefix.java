
package leetcode.strings;

import java.util.Stack;

//14. 最长公共前缀
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int j = 1; j < strs.length; ++j) {
            prefix = longestCommonPrefix(prefix, strs[j]);
            if ("".equals(prefix)) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public static void main(String[] args) {
        String[] strings = {"flower","flow","flight"};
        LongestCommonPrefix commonPrefix = new LongestCommonPrefix();
        String prefix = commonPrefix.longestCommonPrefix(strings);
        System.out.println(prefix);
    }
}
