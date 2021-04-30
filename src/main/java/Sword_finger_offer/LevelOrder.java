package Sword_finger_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 从上到下打印二叉树
public class LevelOrder {
    private final Queue<TreeNode> treeNodeQueue = new LinkedList<>();
    public int[] levelOrder(TreeNode root) {
        if (root == null) return null;

        List<Integer> result = new ArrayList<>();
        treeNodeQueue.offer(root);
        while (treeNodeQueue.size() > 0) {
            TreeNode poll = treeNodeQueue.poll();
            result.add(poll.val);

            if (poll.left != null) {
                treeNodeQueue.offer(poll.left);
            }
            if (poll.right != null) {
                treeNodeQueue.offer(poll.right);
            }
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

     private static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }
}
