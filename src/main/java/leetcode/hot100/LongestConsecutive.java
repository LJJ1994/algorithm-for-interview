
package leetcode.hot100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    // 暴力法
    public int longestConsecutive1(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }
        int longestSequence = 0;
        for (int num : nums) {
            int curCount = 1;
            int curNum = num;
            for (Integer i : numsSet) {
                int nextNum = curNum + 1;
                if (i == nextNum) {
                    curCount++;
                    curNum++;
                }
            }
            longestSequence = Math.max(longestSequence, curCount);
        }
        return longestSequence;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int longestSequence = 0;
        for (int num : nums) {
            int currentSequence = 1;
            if (!set.contains(num - 1)) {
                while (set.contains(num + 1)) {
                    currentSequence++;
                    num++;
                }
            }
            longestSequence = Math.max(longestSequence, currentSequence);
        }
        return longestSequence;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        System.out.println(longestConsecutive.longestConsecutive(nums));
        System.out.println(longestConsecutive.longestConsecutive1(nums));
    }
}
