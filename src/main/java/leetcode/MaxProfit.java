
package leetcode;

import java.util.HashMap;
import java.util.Map;

// 买卖股票的最佳时机
public class MaxProfit {

    // 暴力枚举
    public int maxProfit(int prices[]) {
        int len = prices.length;
        if (prices == null || prices.length <= 0) return 0;
        int maxProfix = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((prices[j] - prices[i]) > maxProfix) {
                    maxProfix = prices[j] - prices[i];
                }
            }
        }
        return maxProfix;
    }

    //一次遍历（贪心）
    public int maxProfit01(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        int[] prices = {7,1,5,3,6,4};
        int[] noProfix = {7,6,4,3,1};
        System.out.println(maxProfit.maxProfit(noProfix));
        System.out.println(maxProfit.maxProfit(prices));

        System.out.println(maxProfit.maxProfit01(noProfix));
        System.out.println(maxProfit.maxProfit01(prices));

    }
}
