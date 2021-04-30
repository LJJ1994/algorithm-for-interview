package Sword_finger_offer;

import sun.reflect.generics.tree.Tree;

/**
 * 查找二叉树的下一个节点
 *
 * 给定一棵二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点
 *
 * 如中序遍历 {d, b, h, e, i, a, f, c, g}
 *
 *               a
 *             /   \
 *           b      c
 *         /  \    /  \
 *        d    e  f    g
 *            / \
 *           h   i
 *
 *           （二叉树）
 *
 */
public class FindBinaryTreeSuccessor {
    public TreeNode findSuccessor(TreeNode node) {
        if (node == null) {
            return node;
        }
        // 初始化后继节点
        TreeNode next = null;
        if (node.right != null) { // 1. 该节点有右子树，则后继节点为该右子树的最左子节点
            TreeNode rightChild = node.right;
            while (rightChild.left != null) {
                rightChild = rightChild.left;
            }
            next = rightChild;
        } else if (node.parent != null) {
            TreeNode currentNode = node;
            TreeNode parentNode = node.parent;
            // 3. 该节点没有右子树，且它是父节点的右子节点
            // 需要沿着父节点一直往上查询，找一个这样一个节点：它是它父节点的左子节点，否则为null
            while (parentNode != null && (currentNode == parentNode.right)) {
                currentNode = parentNode;
                parentNode = parentNode.parent;
            }
            // 2. 1）该节点没有右子树，但是它是它父节点的左子节点, 不走前面的while 循环
            //    2) 走前面的while 循环，赋值。
            next = parentNode;
        }
        return next;
    }


    private static class TreeNode{
        String val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(String x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("a");
        TreeNode b = new TreeNode("b");

    }
}
