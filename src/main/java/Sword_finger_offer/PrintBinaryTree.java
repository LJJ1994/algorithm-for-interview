package Sword_finger_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 二叉树的打印
public class PrintBinaryTree {
    private final Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return null;
        treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < treeNodeQueue.size(); ++i) {
                TreeNode poll = treeNodeQueue.poll();
                tmp.add(poll.val);
                if (poll.left != null) {
                    treeNodeQueue.offer(poll.left);
                }
                if (poll.right != null) {
                    treeNodeQueue.offer(poll.right);
                }
            }
            result.add(tmp);
        }
        return result;
    }
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
