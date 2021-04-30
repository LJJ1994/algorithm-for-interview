package Sword_finger_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 二叉树中和为某一值的路径
public class FindPath {
    private final LinkedList<Integer> path = new LinkedList<>();
    private final LinkedList<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return new ArrayList<>(new ArrayList<>());
        int currentSum = 0;

        findPath(root, sum, currentSum);
        return result;
    }

    private void findPath(TreeNode root, int sum, int currentSum) {
        currentSum += root.val;
        path.add(root.val);

        // 如果是叶子结点
        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf && currentSum == sum) {
            result.add(new LinkedList(path));
        }

        // 非叶子节点
        if (root.left != null) {
            findPath(root.left, sum, currentSum);
        }
        if (root.right != null) {
            findPath(root.right, sum, currentSum);
        }
        path.removeLast();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }
}
