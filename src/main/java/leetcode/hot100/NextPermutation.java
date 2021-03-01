package leetcode.hot100;

// 下一个排列
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums == null) return;

        int i = nums.length - 2;
        // 1. 从后向前遍历，找到第一个较小的数: nums[i] < nums[i + 1]
        while (i >=0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        int j = nums.length - 1;
        // 2. 从后向前，找到第一个较大的数：nums[j] > nums[i]
        if (i >= 0) {
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 3. 从第 i + 1个数（包含）后的序列是一个降序序列，将其反转变为升序
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
