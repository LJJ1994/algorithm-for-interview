
package leetcode.hot100;

import java.util.*;

// 56. 合并区间
public class IntervalsMerge {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();

        for (int i = 0; i < intervals.length; ++i) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                merged.add(new int[]{left, right});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1,9},
                {2,5},
                {19,20},
                {10,11},
                {12,20},
                {0,3},
                {0,1},
                {0,2}
        };
        IntervalsMerge intervalsMerge = new IntervalsMerge();
        intervalsMerge.merge(intervals);
    }
}

