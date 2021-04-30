
package leetcode.hot100;

// 322. 零钱兑换

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 */
public class CoinChange {

    // 自底向上的dp
    public int coinChange(int[] coins, int amount) {
        /**
         * 该问题可以转化成完全背包问题：
         *  选择不同的硬币，放入容量为amount的背包中，在装满背包的情况下所需要的最少硬币数
         */

        // dp[i][j] 表示i个硬币能凑成金额为j的最少硬币数
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int m = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; ++i) {
            dp[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j <= amount; ++j) {
                if (coins[i] <= j) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MIN_VALUE ? -1 : dp[amount];
    }

    // 自顶向下 dfs，记忆化存储中间值，避免重复计算(效率比较低，毕竟回溯)
    public int coinChange1(int[] coins, int amount) {
        int[] memo = new int[amount];
        Arrays.fill(memo, -2);
        Arrays.sort(coins);
        return dfs(coins, amount, memo);
    }

    // bfs, 类似无向图的广度优先遍历
    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        Arrays.sort(coins);
        boolean[] visited = new boolean[amount + 1];
        visited[amount] = true; // 设置最后一个值 amount = 0 为true, 表示访问过，让队列无法添加该值
        Queue<Integer> queue = new LinkedList<>();
        queue.add(amount);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer head = queue.poll();
                for (int coin : coins) {
                    int next = head - coin;
                    if (next == 0) {
                        return step;
                    }
                    if (next < 0) {
                        break;
                    }
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private int dfs(int[] coins, int amount, int[] memo) {
        int res = Integer.MAX_VALUE;
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != -2) {
            return memo[amount];
        }
        for (int coin : coins) {
            if (coin - amount > 0) {
                break;
            }
            int subRes = dfs(coins, amount - coin, memo);
            if (subRes == -1)  {
                continue;
            }
            res = Math.min(res, subRes + 1);
        }

        return memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
    }
}