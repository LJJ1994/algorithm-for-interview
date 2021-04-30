
package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 120. 三角形最小路径和
/**
 * [2]
 * [3,4]
 * [6,5,7]
 * [4,1,8,3]
 */
public class Triangle {
    private Integer[][] memo;
    // 自顶向下的递归，深度优先遍历
    public int minimumTotal3(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        memo[i][j] = Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
        return memo[i][j];
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(0, Arrays.asList(2));
        lists.add(1, Arrays.asList(3, 4));
        lists.add(2, Arrays.asList(6, 5, 7));
        lists.add(3, Arrays.asList(4, 1, 8, 3));
        int i = triangle.minimumTotal3(lists);
        System.out.println(i);
    }

    // 自顶向下的DP
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if (triangle == null || triangle.size() == 0) return 0;
        if (m == 1) {
            return triangle.get(0).get(0);
        }
        // dp[i][j] 表示到达位置(i, j)时所得到的最小路径和
        // dp[0][0] 初始化为第一层第一个元素
        int[][] dp = new int[m][m];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < m; ++i) {
            // 在区间 [0, i] 枚举值
            for (int j = 0; j <= i; ++j) {
                // 当 j = 0时, 表示只能从第 i-1 行的最左侧转移过来; dp[i - 1][j - 1] = dp[i - 1][-1] 无意义项
                if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
                    continue;
                }
                // 当 j = i时, 表示只能从第 i-1 行的最右侧转移过来；dp[i - 1][j] = dp[i - 1][i] 无意义项
                if (j == i) {
                    dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
        }

        int totalMin = dp[m - 1][0];
        for (int i = 1; i < m; i++) {
            totalMin = Math.min(totalMin, dp[m - 1][i]);
        }
        return totalMin;
    }

    // 自底向上的DP
    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    // 自底向上的DP(空间优化)
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
