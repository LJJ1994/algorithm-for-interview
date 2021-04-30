package Sword_finger_offer;

// 判断一棵树是否为对称二叉树
public class IsSymmetric {
    public boolean isSymmetrical(TreeNode root) {
        return isSymmetrical(root, root);
    }

    private boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
    }

    private static class TreeNode {
        private double val;
        private TreeNode left;
        private TreeNode right;
    }
}
