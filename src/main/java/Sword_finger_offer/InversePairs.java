package Sword_finger_offer;

// 数组中的逆序对(困难)
public class InversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] copy = new int[len];
        for (int i = 0; i < len; ++i) {
            copy[i] = nums[i];
        }
        int count = inversePairsCore(nums, copy, 0, len - 1);
        return count;
    }

    private int inversePairsCore(int[] nums, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = nums[start];
            return 0;
        }

        int length = (end - start) >>> 2;
        // 左半数组
        int left = inversePairsCore(nums, copy, start, start + length);
        // 右半数组
        int right = inversePairsCore(nums, copy, start + length + 1, end);

        int i = start + length;
        int j = end;
        int count = 0;
        int indexCopy = end;
        while (i >= start && j >= (start + length + 1)) {
            if (nums[i] > nums[j]) {
                copy[indexCopy--] = nums[i--];
                count += (j - start - length);
            } else {
                copy[indexCopy--] = nums[j--];
            }
        }

        for (;i >= start; --i) {
            copy[indexCopy--] = nums[i];
        }
        for (;j >= (start + length + 1); --j) {
            copy[indexCopy--] = nums[j];
        }
        return left + right + count;
    }

    public static void main(String[] args) {
        InversePairs inversePairs = new InversePairs();
        int[] nums = {7, 5, 6, 4};
        int i = inversePairs.reversePairs(nums);
        System.out.println(i);
    }
}
