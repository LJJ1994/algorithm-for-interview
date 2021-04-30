
package leetcode.hot100;

//33
//搜索旋转排序数组
public class SearchInRotateArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int l = 0;
        int r = nums.length - 1;
        int n = nums.length;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 1. mid 在数组的前半部分
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && nums[mid] > target) {
                    r = mid - 1;

                } else {
                    l = mid + 1;
                }
            } else { // 2. mid 在数组的后半部分(旋转部分)
                if (target <= nums[ n- 1] && nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}
