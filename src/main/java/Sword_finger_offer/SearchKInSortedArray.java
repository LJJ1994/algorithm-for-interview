package Sword_finger_offer;

// 53.在排序数组中查找数字
public class SearchKInSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int length = nums.length;
        int firstK = searchFirstK(nums, target, 0, length - 1);
        int lastK = searchLastK(nums, target, 0, length - 1);
        int count = 0;
        if (firstK > -1 && lastK > -1) {
            count = lastK - firstK + 1;
        }
        return count;
    }

    // 找到第一个K值
    private int searchFirstK(int[] nums, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            if ((mid > 0 && nums[mid-1] != target) || mid == 0 ) {
                return mid;
            } else {
                end = mid - 1;
            }
        } else if (nums[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
        return searchFirstK(nums, target, start, end);
    }
    // 找到最后一个K值
    private int searchLastK(int[] nums, int target, int start, int end) {
        if (start > end ) return -1;
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            if ((mid < nums.length - 1) && (nums[mid + 1] != target) || mid == nums.length - 1) {
                return mid;
            } else {
                start = mid + 1;
            }
        } else if (nums[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
        return searchLastK(nums, target, start, end);
    }
}
