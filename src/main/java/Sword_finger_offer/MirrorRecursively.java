package Sword_finger_offer;

// 求二叉树的镜像
public class MirrorRecursively {
    public void mirror(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null) {
            mirror(root.left);
        }

        if (root.right != null) {
            mirror(root.right);
        }
    }
    private static class TreeNode {
        private double val;
        private TreeNode left;
        private TreeNode right;
    }
}
