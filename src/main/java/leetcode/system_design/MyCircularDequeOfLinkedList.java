package leetcode.system_design;

//// 641. 设计循环双端队列(链表实现)
public class MyCircularDequeOfLinkedList {
    private Node head;
    private Node tail;
    private int[] elements;
    private int size;
    private int capacity;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDequeOfLinkedList(int k) {
        this.capacity = k;
        elements = new int[capacity];
        head = tail = null;
        size = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        Node node = new Node(value);
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        Node node = new Node(value);
        node.prev = tail;
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        Node next = head.next;
        if (next != null) {
            next.prev = null;
            head.next = null;
        } else {
            tail = null;
        }
        head = next;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        Node prev = tail.prev;
        tail.prev = null;
        if (prev != null) {
            prev.next = null;
        } else {
            head = null;
        }
        tail = prev;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return head == null ? -1 : head.value;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return tail == null ? -1 : tail.value;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == capacity;
    }

    // linkedlist node
    private static class Node {
        Node prev;
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }

        public Node(Node prev, Node next, int value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
}
