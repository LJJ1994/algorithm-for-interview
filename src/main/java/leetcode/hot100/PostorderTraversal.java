
package leetcode.hot100;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostorderTraversal {
    private final LinkedList<Integer> res = new LinkedList<>();
    private final Deque<TreeNode> stack = new LinkedList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
//        postorder(root);
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                res.addFirst(node.val);
                node = node.right;
            }
            node = stack.pop();
            node = node.left;
        }
        return res;
    }

    private void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        res.add(node.val);
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
