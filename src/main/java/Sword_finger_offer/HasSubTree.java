package Sword_finger_offer;

import javax.swing.tree.TreeNode;

// 两颗二叉树A和B， 判断A是否包含B，或者B是否是A的子树
public class HasSubTree {

    public boolean hasSubTree(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        boolean result = false;
        if (equal(root1.val, root2.val)) {
            result = doesTree1HaveTree2(root1, root2);
        }
        if (!result) {
            result = hasSubTree(root1.left, root2);
        }
        if (!result) {
            result = hasSubTree(root1.right, root2);
        }
        return result;
    }

    private boolean doesTree1HaveTree2(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root2 == null) return true;

        if (root1 == null) return false;

        if (!equal(root1.val, root2.val)) {
            return false;
        }

        return doesTree1HaveTree2(root1.left, root2.left) &&
                doesTree1HaveTree2(root1.right, root2.right);
    }

    private boolean equal(double num1, double num2) {
        if ((num1 - num2) > -0.0000001 && (num1 - num2) < 0.0000001) {
            return true;
        } else {
            return false;
        }
    }

    private static class BinaryTreeNode {
        private double val;
        private BinaryTreeNode left;
        private BinaryTreeNode right;
    }
}
