
package leetcode.hot100;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 单词拆分
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        int maxStr = 0;
        for (String s1 : wordDict) {
            maxStr = Math.max(maxStr, s1.length());
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
