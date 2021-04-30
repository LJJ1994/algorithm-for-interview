package Sword_finger_offer;

// 最长公共子序列
public class LongestCommonSubsequence {
    private char[] char1;
    private char[] char2;
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == "" || text1 == null || text2 == "" || text2 == null) return 0;
        char1 = text1.toCharArray();
        char2 = text2.toCharArray();
        int i = char1.length - 1;
        int j = char2.length - 1;
//        return dp(i, j);
        return dp01(char1.length, char2.length);
    }

    // 递归
    private int dp(int i, int j) {
        if (i == -1 || j == -1) return 0;
        if (char1[i] == char2[j]) {
            return dp(i - 1, j - 1) + 1;
        } else {
            return Math.max(dp(i - 1, j), dp(i, j - 1));
        }
    }

    // table
    private int dp01(int length1, int length2) {
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i < length1 + 1; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        String text1 = "abcde", text2 = "ace";
        int i = subsequence.longestCommonSubsequence(text1, text2);
        System.out.println(i);
    }
}
