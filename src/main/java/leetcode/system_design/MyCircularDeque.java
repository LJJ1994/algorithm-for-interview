package leetcode.system_design;

// 641. 设计循环双端队列
public class MyCircularDeque {
    // head 指向第一个元素
    // tail 指向尾部最后一个元素的下一个位置
    // head 和 tail 之间永远至少有一个元素，方便于判空判满
    // 队列判空：front = tail
    // 队列判满：(tail + 1) % capacity = front
    private int[] elements;
    private int capacity;
    private int head;
    private int tail;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.capacity = k + 1;
        elements = new int[capacity];
        head = tail = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        // 加上capacity是为了防止head 变为负数，因为有可能head跑到tail后面，也就是数组的末尾
        head = (head - 1 + capacity) % capacity;
        elements[head] = value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        elements[tail] = value;
        tail = (tail + 1) % capacity;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        // 加上capacity 是为了防止tail变为负数，有可能tail会跑到数组前面，然后追上head
        tail = (tail - 1 + capacity) % capacity;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return elements[head];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return elements[tail - 1];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return head == tail;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }
}
