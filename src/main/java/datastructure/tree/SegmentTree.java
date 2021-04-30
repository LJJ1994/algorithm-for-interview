
package datastructure.tree;

import java.util.HashSet;
import java.util.Set;

// 线段树
public class SegmentTree {
    private int[] tree;
    private int n;
    public SegmentTree(int[] nums) {
        if (nums.length > 0) {
            this.n = nums.length;
            this.tree = new int[n * 2];
            buildTree(nums);
        }
    }
    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; ++i, ++j) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i >= 0; --i) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            // 1.如果更新的是右孩子：pos % 2 == 1, 那么left为：pos - 1, right为：pos
            // 2.如果更新的是左孩子：pos % 2 == 0, 那么left为：pos, right为：pos + 1
            // 3.更新父节点： pos / 2
            if (pos % 2 == 1) {
                left = pos - 1;
            } else {
                right = pos + 1;
            }
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int l, int r) {
        Set<String> s = new HashSet<>();
        for (String s1 : s) {

        }
        l += n;
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += tree[l];
                ++l;
            }
            if ((r % 2) == 0) {
                sum += tree[r];
                --r;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}
