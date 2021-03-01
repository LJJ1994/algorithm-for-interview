package leetcode.hot100;

import java.util.*;

// 34. 在排序数组中查找元素的第一个和最后一个位置
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }

        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int first = -1;
        int last = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                //查找第一个first
                if (mid == 0 || nums[mid - 1] !=target) {
                    first = mid;
                } else {
                    r = mid - 1;
                }
                // 查找最后一个last
                if ((mid == n - 1) || nums[mid + 1] != target) {
                    last = mid;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return new int[]{first, last};
    }

    public int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }
        // 左边第一个大于等于target的数
        int leftIndex = binarySearch(nums, target, true);
        // 右边第一个大于target的数，然后索引-1，即可得到右边target的索引
        int rightIndex = binarySearch(nums, target, false) - 1;
        if (leftIndex <= rightIndex
                && rightIndex < nums.length
                && nums[leftIndex] == target
                && nums[rightIndex] == target) {
            return new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1, -1};

    }

    private int binarySearch(int[] nums, int target, boolean lower) {
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // nums[mid] > target 表示查找第一个大于target的值，为最后一个index
            // nums[mid] >= target 表示查找第一个大于等于target的值，为第一个index
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                ans = mid;
                r = mid - 1;
            } else {
                 l = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        SearchRange searchRange = new SearchRange();
        int[] ints = searchRange.searchRange(nums, target);
        System.out.println(Arrays.toString(ints));
    }
}
