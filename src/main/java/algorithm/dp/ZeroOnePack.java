package algorithm.dp;

/**
 * 0-1背包
 * 题目：
 * 有N件物品和一个容量为 V 的背包。放入第 i 件物品耗费的空间是 w(i)，得到
 * 的价值是 val(i)。求解将哪些物品装入背包可使价值总和最大。
 *
 */

public class ZeroOnePack {
    /**
     * 0-1背包
     * @param v 背包容量
     * @param w 物品重量，与n相对应
     * @param val 物品价值，与n相对应
     * @return
     */
    private int maximum(int v, int[] w, int[] val) {
        if (val == null || val.length == 0|| w == null || w.length == 0) {
            return 0;
        }
        int[][] dp = new int[w.length + 1][v + 1];

        for (int i = 0; i < w.length + 1; ++i) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < v + 1; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= v; j++) {
                if (j < w[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[w.length][v];
    }

    /**
     * 0-1背包 优化空间，只用一维数组存储
     * @param v 背包容量
     * @param w 物品重量，与n相对应
     * @param val 物品价值，与n相对应
     * @return
     */
    private int maximum1(int v, int[] w, int[] val) {
        if (val == null || val.length == 0|| w == null || w.length == 0) {
            return 0;
        }
        int[] dp = new int[v + 1];

        for (int i = 0; i < v + 1; ++i) {
            dp[i] = 0;
        }
        for (int i = 1; i <= w.length; ++i) {
            for (int j = v; j >= 0; --j) {
                if (j >= w[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[v];
    }

    /**
     * 0-1背包 优化下界
     * @param v 背包容量
     * @param w 物品重量，与n相对应
     * @param val 物品价值，与n相对应
     * @return
     */
    private int maximum2(int v, int[] w, int[] val) {
        if (val == null || val.length == 0|| w == null || w.length == 0) {
            return 0;
        }
        int[] dp = new int[v + 1];

        for (int i = 0; i < v + 1; ++i) {
            dp[i] = 0;
        }
        int sumW = 0;
        for (int i = 0; i < val.length; ++i) {
            sumW += val[i];
        }
        for (int i = 1; i <= w.length; ++i) {
            int bound = Math.max(v - sumW, w[i - 1]);
            for (int j = v; j >= bound; --j) {
                if (j >= w[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[v];
    }

    public static void main(String[] args) {
        int v = 5; //背包最大重量
        int[] w = {2, 1, 3, 2}; // 物品重量
        int[] val = {12, 10, 20, 15}; // 物品价值
        ZeroOnePack zeroOnePack = new ZeroOnePack();
        int maximum = zeroOnePack.maximum(v, w, val);
        int maximum1 = zeroOnePack.maximum1(v, w, val);
        int maximum2 = zeroOnePack.maximum2(v, w, val);
        System.out.println(maximum);
        System.out.println(maximum1);
        System.out.println(maximum2);
    }
}
