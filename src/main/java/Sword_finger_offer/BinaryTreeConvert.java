package Sword_finger_offer;

// 二叉搜索树和双向链表
public class BinaryTreeConvert {
    public Node treeToDoublyList(Node root) {
        Node lastNodeInList = null;
        convertNode(root, lastNodeInList);
        Node headOfList = lastNodeInList;
        while (headOfList != null && headOfList.left != null) {
            headOfList = headOfList.left;
        }
        return headOfList;
    }
    private void convertNode(Node node, Node lastNodeInList) {
        if (node == null) return;
        Node current = node;
        if (current.left != null) {
            convertNode(current.left, lastNodeInList);
        }
        current.left = lastNodeInList;
        if (lastNodeInList != null) {
            lastNodeInList.right = current;
        }
        lastNodeInList = current;

        if (current.right != null) {
            convertNode(current.right, lastNodeInList);
        }
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int val) {
            val = val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
