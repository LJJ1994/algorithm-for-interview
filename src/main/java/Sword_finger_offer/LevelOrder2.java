package Sword_finger_offer;

import sun.reflect.generics.tree.Tree;

import java.util.*;

// 剑指 Offer 32 - III. 从上到下打印二叉树 III
public class LevelOrder2 {
    private final List<List<Integer>> res = new ArrayList<>();
    private final Queue<TreeNode> queue = new LinkedList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> one = new LinkedList<>();
            int size = queue.size();
            for (int i = size; i > 0; i--) {
                TreeNode poll = queue.poll();
                if (res.size() % 2 == 0) {
                    one.addLast(poll.val);
                } else {
                    one.addFirst(poll.val);
                }

                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            res.add(one);
        }
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        list.addLast(9);
        list.addLast(2);

        res.add(list);
        for (List<Integer> re : res) {
            for (Integer integer : re) {
                System.out.println(integer);
            }
        }
    }
}
