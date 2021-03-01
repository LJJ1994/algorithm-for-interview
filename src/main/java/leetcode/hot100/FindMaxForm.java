package leetcode.hot100;

// 474. 一和零
public class FindMaxForm {
    /**
     *
     * @param strs
     * @param m 0 的数量
     * @param n 1 的数量
     * @return 最大子集
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j][k] 表示考虑第i个字符串时，其中的 0 和 1 能装入背包j和背包k的 最多字符串数
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        // dp[i][j][k] = max{dp[i - 1][j][k], dp[i][j - count(0)][k - count(1)] + 1}
        for (int i = 1; i <= strs.length; ++i) {
            int num0 = count(strs[i - 1])[0];
            int num1 = count(strs[i - 1])[1];
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    if (num0 > j || num1 > k) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - num0][k - num1] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    public int findMaxForm1(String[] strs, int m, int n) {
        // dp[i][j][k] 表示考虑第i个字符串时，其中的 0 和 1 能装入背包j和背包k的 最多字符串数
        //int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        // dp[i][j][k] = max{dp[i - 1][j][k], dp[i][j - count(0)][k - count(1)] + 1}
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; ++i) {
            int num0 = count(strs[i])[0];
            int num1 = count(strs[i])[1];
            for (int j = m; j >= 0; --j) {
                for (int k = n; k >= 0; --k) {
                    if (num0 < j && num1 < k) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - num0][k - num1] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }

    private int[] count(String s) {
        char[] chars = s.toCharArray();
        int[] res = new int[2]; // 0 索引存储 0 的个数， 1 索引存储 1 的个数
        for (char c : chars) {
            if (c == '0') {
                res[0]++;
            } else if (c == '1'){
                res[1]++;
            }
        }
        return res;
    }
}
