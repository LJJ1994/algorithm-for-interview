
package datastructure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(int value) {
        root = new Node(value);
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node find(int value) {
        if (root == null) {
            return null;
        }

        Node p = root;
        while (p != null) {
            if (p.data > value) {
                p = p.left;
            } else if (p.data < value) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node p = root;
        while (p != null) {
            if (p.data > value) {
                if (p.left == null) {
                    p.left = new Node(value);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(value);
                    return;
                } else {
                    p = p.right;
                }
            }
        }
    }

    public void delete(int data) {
        // 从根节点开始遍历
        Node p = root;
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (p.data < data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) return;

        // 1.要删除的节点p都有左右节点
        // 找到该节点的右子树的最左节点（该节点为该右子树的最小节点），然后替换p
        if (p.right != null && p.left != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 删除p节点
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }
        // 2. 要删除的节点只有一个子节点(left or right)，或者没有子节点
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // 3.要删除的是根节点
        if (pp == null) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

    public Node findMin() {
        if (null == root) {
            return null;
        }
        Node p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node findMax() {
        if (null == root) {
            return null;
        }
        Node p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    public void printInOrder() {
        root.printInOrder();
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }
            System.out.print(data + " ");
            if (right != null) {
                right.printInOrder();
            }
        }
    }

    public static void main(String[] args) {
        /**
         *               5
         *              / \
         *           3     7
         *          / \   /  \
         *        2    4 6    8
         *
         */
        BinarySearchTree tree = new BinarySearchTree(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(7);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        Node node = tree.find(8);
        System.out.println(node.data); // 8

        tree.printInOrder(); // 2 3 4 5 6 7 8
        System.out.println();

        tree.delete(4);
        tree.printInOrder(); // 2 3 5 6 7 8
        System.out.println();

        Node min = tree.findMin();
        System.out.println(min.data); // 2
        Node max = tree.findMax();
        System.out.println(max.data); // 8
    }
}