package Sword_finger_offer;

import java.util.PriorityQueue;

// 数组中出现次数超过一半的数字
public class MajorityElement {
    private boolean isInvalidInput = false;
    private int result;
    // 快速排序的思想
    public int majorityElement(int[] nums) {
        if (checkInvalidArray(nums)) {
            result = 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int middle = nums.length >>> 1;
        int index = partition(nums, start, end);
        while (index != middle) {
            if (index > middle) {
                end = index - 1;
                index = partition(nums, start, end);
            } else {
                start = index + 1;
                index = partition(nums, start, end);
            }
        }
        result = nums[middle];
        if (!checkMoreThanHalf(nums, nums.length, result)) {
            result = 0;
        }
        return result;
    }

    // 第二种解法: 根据数组特点
    public int majorityElement01(int[] nums) {
        if (checkInvalidArray(nums)) {
            result = 0;
        }
        result = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; i++) {
            if (times == 0) {
                result = nums[i];
                times = 1;
            } else if (nums[i] == result) {
                times += 1;
                result = nums[i];
            } else {
                times -= 1;
            }
        }
        if (!checkMoreThanHalf(nums, nums.length, result)) {
            result = 0;
        }
        return result;
    }

    private boolean checkMoreThanHalf(int[] arr, int length, int result) {
        int times = 0;
        for (int i = 0; i < length; ++i) {
            if (arr[i] == result) {
                times++;
            }
        }
        boolean isMoreThanHalf = true;
        if ((times << 1) <= length) {
            isMoreThanHalf = false;
            isInvalidInput = true;
        }
        return isMoreThanHalf;
    }

    private boolean checkInvalidArray(int[] arr) {
        isInvalidInput = false;
        if (arr == null || arr.length <= 0) {
            isInvalidInput = true;
        }
        return isInvalidInput;
    }

    /**
     *
     * @param arr 源数组
     * @param p 起始索引
     * @param r 终点索引
     * @return index
     */
    private int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        int j = p;
        for (; j < r; ++j) {
            if (arr[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                }
            }
        }

        // put the pivot to correct position.
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;

        return i;
    }

    public static void main(String[] args) {

        MajorityElement majorityElement = new MajorityElement();
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        int num = majorityElement.majorityElement(arr);
        System.out.println(num);

    }
}
