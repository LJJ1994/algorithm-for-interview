package Sword_finger_offer;

// 数组的连续子数组的最大和
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;
        int curSum = 0;
        int greatestSum = 0x80000000;
        for (int i = 0; i < nums.length; ++i) {
            if (curSum <= 0) {
                curSum = nums[i];
            } else {
                curSum += nums[i];
            }
            if (curSum > greatestSum) {
                greatestSum = curSum;
            }
        }
        return greatestSum;
    }

    public static void main(String[] args) {
        int[] arr = {-1};
        MaxSubArray maxSubArray = new MaxSubArray();
        int sum = maxSubArray.maxSubArray(arr);
        System.out.println(sum);
    }
}
