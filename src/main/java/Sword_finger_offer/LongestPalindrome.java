package Sword_finger_offer;

// 最长回文子串
public class LongestPalindrome {
    // 暴力枚举
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 0) return null;
        if (s.length() == 1) return s;
        char[] chars = s.toCharArray();
        int begin = 0;
        int maxLength = 1;
        // 枚举所有长度大于1的子串
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if ((j - i + 1) > maxLength && isPalindrome(chars, i, j)) {
                    begin = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }
    // 动态规划
    public String longestPalindrome01(String s) {
        if (s.length() == 0 || s == null) return null;
        if (s.length() == 1) return s;
        char[] chars = s.toCharArray();
        int len = chars.length;

        // dp[i][j] 表示子串是否为回文串
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int begin = 0;
        // 第一列，第0行开始填表
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[j] != chars[i]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindrome palindrome = new LongestPalindrome();
        String s = "ababa";
        String s1 = "abacc";
        String s2 = "abcbd";
        String s3 = "a";
        String s4 = "ab";

        System.out.println(palindrome.longestPalindrome(s));
        System.out.println(palindrome.longestPalindrome(s1));
        System.out.println(palindrome.longestPalindrome(s2));
        System.out.println(palindrome.longestPalindrome(s3));
        System.out.println(palindrome.longestPalindrome(s4));
    }
}
