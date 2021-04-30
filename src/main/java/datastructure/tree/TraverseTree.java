
package datastructure.tree;

import java.util.LinkedList;

/**
 * Tree based on Binary search datastructure.tree.
 * each node has been visited twice most, so the time complexity: O(n)
 */
public class TraverseTree {

    private static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        // pre-order traverse, root->left->right
        public void printPreOrder() {
            System.out.print(data + " ");
            if (left != null) {
                left.printPreOrder();
            }
            if (right != null) {
                right.printPreOrder();
            }
        }

        //in-order traverse, left-root-right
        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }
            System.out.print(data + " ");
            if (right != null) {
                right.printInOrder();
            }
        }

        // post traver datastructure.tree, left->right->root
        public void printPostOrder() {
            if (left != null) {
                left.printPostOrder();
            }
            if (right != null) {
                right.printPostOrder();
            }
            System.out.print(data + " ");
        }

        // insert element，left.data < root.data <= right.data
        public void insert(int value) {
            if (value < data) {
                if (left == null) {
                    left = new Node(value);
                }else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new Node(value);
                } else {
                    right.insert(value);
                }
            }
        }

        /**
         * level traverse, from root to leaf node, from left to right,
         * need O(n) space to store element.
         */
        public void printLevelOrder() {
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(this);
            while (queue.size() > 0) {
                Node head = queue.remove();
                System.out.print(head.data + " ");
                if (head.left != null) {
                    queue.add(head.left);
                }
                if (head.right != null) {
                    queue.add(head.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node tree = new Node(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(7);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println("Pre order traversal:");
        tree.printPreOrder();
        System.out.println();

        // Prints 2 3 4 5 6 7 8
        System.out.println("In order traversal:");
        tree.printInOrder();
        System.out.println();
        // Prints 2 4 3 6 8 7 5
        System.out.println("Post order traversal:");
        tree.printPostOrder();
        System.out.println();
        // Prints 5 3 7 2 4 6 8
        System.out.println("Level order traversal:");
        tree.printLevelOrder();
        System.out.println();
    }
}