
package leetcode.hot100;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 二叉树的前序遍历
public class PreorderTraversal {
    private final List<Integer> res = new ArrayList<>();
    private final Deque<TreeNode> stack = new LinkedList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
//        postorder(root);
//        return res;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    public void postorder(TreeNode node) {
        if (node == null) return;
        res.add(node.val);
        postorder(node.left);
        postorder(node.right);
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
