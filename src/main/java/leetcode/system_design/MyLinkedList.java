package leetcode.system_design;

// 707. 设计链表
public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.size = 0;
        head = tail = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (size == 0 || index >= size || index < 0) {
            return -1;
        }
        // 0 1 2 3 4 5 idx = 2
        Node cur = head;
        for (int i = 0; i < index && cur != null; i++) {
            cur = cur.next;
        }
        return cur == null ? -1 : cur.value;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     * */
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            System.out.println(i);
        }
    }
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index < 0) {
            addAtHead(val);
            return;
        }
        Node pre = null;
        Node cur = head;
        // 0   1   2   3   4   5 index = 2
        //    pre  cur
        for (int i = 0; i < index && cur != null; i++) {
            pre = cur;
            cur = cur.next;
        }
        Node node = new Node(val);
        node.next = cur;
        if (pre != null) {
            pre.next = node;
        } else {
            head = node;
        }
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node cur = head;
        Node pre = null;
        // 0   1   2   3   4   5 index = 2
        //    pre  cur
        for (int i = 0; i < index && cur != null; i++) {
            pre = cur;
            cur = cur.next;
        }
        Node next = null;
        if (cur != null) {
            next = cur.next;
            cur.next = null;
        }
        if (pre != null) {
            pre.next = next;
        } else {
            head = next;
        }
        if (next == null) {
            tail = pre;
        }
        size--;
    }

    // single linkedlist node
    private static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
