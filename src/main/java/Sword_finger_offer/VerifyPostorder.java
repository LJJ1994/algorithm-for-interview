package Sword_finger_offer;

import java.util.Arrays;

//二叉搜索树的后序遍历序列
public class VerifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
//        return verifyPostorder(postorder, postorder.length);
        return recur(postorder, 0, postorder.length - 1);
    }

    // 课本中的题解，待续
    private boolean verifyPostorder(int[] postorder, int length) {
        if (postorder == null || length == 0) {
            return false;
        }
        int root = postorder[length - 1];

        // 在二叉搜索树中左子树节点的值小于根节点的值
        int i = 0;
        for (; i < length - 1; ++i) {
            if (postorder[i] > root) break;
        }
        // 在二叉搜索树中右子树节点的值大于根节点的值
        int j = i;
        for (; j < length - 1; ++j) {
            if (postorder[j] < root) return false;
        }

        // 判断左子树是否为二叉搜索树
        boolean left = true;
        if (i > 0) {
            left = verifyPostorder(postorder, i);
        }

        // 判断左子树是否为二叉搜索树
        boolean right = true;
        if (i < length - 1) {
            right = verifyPostorder(Arrays.copyOfRange(postorder, i, j-1), length - 1 - i);
        }
        return left && right;
    }

    // leetcode中的题解
    private boolean recur(int[] postorder, int i, int j) {
        // 节点数 <= 1
        if (i >= j) return true;
        int p = i;
        while (postorder[p] < postorder[j]) {
            p++;
        }
        // m是第一个大于根节点的节点，即右子树第一个节点
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    public static void main(String[] args) {
        int[] postorder = {1,3,2,6,5};
        int[] notPostorder = {7, 4, 6, 5};
        VerifyPostorder verifyPostorder = new VerifyPostorder();
        System.out.println(verifyPostorder.verifyPostorder(postorder)); // true

        System.out.println(verifyPostorder.verifyPostorder(notPostorder));
    }
}
