package leetcode.hot100;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 二叉树的中序遍历
public class InorderTraversal {
    private final List<Integer> res = new ArrayList<>();
    private final Deque<TreeNode> stack = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
//        inorder(root);
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }


    // 递归
    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        res.add(node.val);
        inorder(node.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
