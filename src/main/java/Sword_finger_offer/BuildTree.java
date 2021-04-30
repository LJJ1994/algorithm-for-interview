package Sword_finger_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class BuildTree {
    public TreeNode buildTree(int[] preOder, int[] inOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = preOder.length;
        // 用一个map 记录中序遍历序列中的元素及下标
        for (int i = 0; i < length; i++) {
            map.put(inOrder[i], i);
        }
        TreeNode root = buildTree(preOder, 0, length - 1, inOrder, 0, length -1, map);
        return root;
    }

    public TreeNode buildTree(int[] preOrder, int preOrderStart, int preOrderEnd,
                          int[] inOrder, int inOrderStart, int inOrderEnd, Map<Integer, Integer> map) {
        if (preOrderStart > preOrderEnd) {
            return null;
        }
        int rootValue =  preOrder[preOrderStart];
        TreeNode root = new TreeNode(rootValue);
        if (preOrderStart == preOrderEnd) {
            return root;
        } else {
            int rootIndex = map.get(rootValue);
            int leftNodes = rootIndex - inOrderStart;
            int rightNodes = inOrderEnd - rootIndex;
            TreeNode leftSubTree = buildTree(preOrder, preOrderStart + 1, preOrderStart + leftNodes,
                    inOrder, inOrderStart, rootIndex-1, map);
            TreeNode rightSubTree = buildTree(preOrder, preOrderEnd - rightNodes + 1, preOrderEnd,
                    inOrder, rootIndex + 1, inOrderEnd, map);
            root.left = leftSubTree;
            root.right = rightSubTree;
            return root;
        }
    }

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x; }
    }
}

