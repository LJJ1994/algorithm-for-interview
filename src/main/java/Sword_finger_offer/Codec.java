package Sword_finger_offer;

import java.util.Deque;

// 序列化和反序列化
public class Codec {
    private int index = -1;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) {
            return "$,";
        }
        return root.val + "," + serialize(root.left) + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] str = data.split(",");
        return deserialize(str);
    }

    private TreeNode deserialize(String[] strNode) {
        index++;
        TreeNode treeNode = null;
        if (!strNode[index].equals("$")) {
            treeNode = new TreeNode(Integer.valueOf(strNode[index]));
            treeNode.left = deserialize(strNode);
            treeNode.right = deserialize(strNode);
        }
        return treeNode;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
