package leetcode.hot100;

import java.util.Arrays;

// 518. 零钱兑换 II
public class CoinChange2 {
    private int count = 0;
    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        if (coins.length == 1) {
            return coins[0] == amount ? 1 : 0;
        }
        Arrays.sort(coins);
        dfs(amount, coins);
        return count;
    }

    // 空间压缩
    public int change1(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] += dp[j - coins[i] - 1];
            }
        }
        return dp[amount];
    }

    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (coins[i - 1] <= j) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

    private void dfs(int amount, int[] coins) {
        for (int coin : coins) {
            if (amount < coin) {
                break;
            }
            if (amount == coin) {
                count++;
                continue;
            }
            dfs(amount - coin, coins);
        }
    }
}
