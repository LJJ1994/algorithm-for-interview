package leetcode.hot100;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//114. 二叉树展开为链表
public class Flatten {
    private final Deque<TreeNode> stack = new LinkedList<>();
    private final List<TreeNode> list = new LinkedList<>();
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode node = root;
        stack.push(node);
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; ++i) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
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
