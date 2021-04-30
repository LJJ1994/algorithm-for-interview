package Sword_finger_offer;

public class RotateArray {
    public int findMinRotateArray(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int length = nums.length;
        int indexLow = 0;
        int indexHigh = length - 1;
        int midIndex = indexLow;
        while (nums[indexLow] >= nums[indexHigh]) {
            if ((indexHigh - indexLow) == 1) {
                midIndex = indexHigh;
                break;
            }
            midIndex = indexLow + (indexHigh - indexLow) / 2;
            // 如果 indexLow, indexHigh, indexMid 三个指针指向的元素相同，使用顺序查找
            if (nums[indexLow] == nums[indexHigh] && nums[midIndex] == nums[indexLow]) {
                return inOrder(nums, indexLow, indexHigh);
            }
            if (nums[midIndex] >= nums[indexLow]) {
                indexLow = midIndex;
            }else if (nums[midIndex] < nums[indexHigh]) {
                indexHigh = midIndex;
            }
        }
        return nums[midIndex];
    }

    private int inOrder(int[] nums, int indexLow, int indexHigh) {
        int result = nums[indexLow];
        for (int i = indexLow + 1; i <= indexHigh; ++i) {
            if (result > nums[i]) {
                result = nums[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = { 4, 5, 6, 2, 2, 3};
        int[] nums = {1, 0, 1, 1, 1};
        RotateArray rotateArray = new RotateArray();

        int num = rotateArray.findMinRotateArray(nums);
        System.out.println(num);
    }
}
