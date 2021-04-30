package Sword_finger_offer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 零钱换算
public class CoinChange {
    private int[] coins;
    private Map<Integer, Integer> dpTable = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        return dp(amount);
    }

    // 递归法
    private int dp(int amount) {
        if (dpTable.containsKey(amount)) return dpTable.get(amount);
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subproblem = dp(amount - coin);
            if (subproblem == -1) continue;
            res = Math.min(res, subproblem + 1);
        }
        if (res != Integer.MAX_VALUE) {
            dpTable.put(amount, res);
        } else {
            dpTable.put(amount, -1);
        }
        return dpTable.get(amount);
    }

    // 迭代法
    public int dp01(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
//        int[] coins = {1, 2, 5};
//        int amount = 11;
//        CoinChange coinChange = new CoinChange();
//        int i = coinChange.coinChange(coins, amount);
//        System.out.println(i);
    }
}
