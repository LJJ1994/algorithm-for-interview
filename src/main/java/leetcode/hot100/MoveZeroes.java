
package leetcode.hot100;

import java.util.Arrays;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) return;
        int i = 0;
        int j = nums.length - 1;
        int temp = nums[0];
        move(nums);
        nums[j] = temp;
    }

    private void move(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            nums[i - 1] = nums[i];
            nums[i] = nums[i + 1];
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
