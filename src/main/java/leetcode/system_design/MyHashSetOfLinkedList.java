package leetcode.system_design;

import java.util.Iterator;
import java.util.LinkedList;

// // 705. 设计哈希集合(链地址法实现)
public class MyHashSetOfLinkedList {
    private final LinkedList[] elements;
    private final int capacity = 769;
    /** Initialize your data structure here. */
    public MyHashSetOfLinkedList() {
        elements = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            elements[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int hash = hash(key);
        LinkedList element = elements[hash];
        Iterator iterator = element.iterator();
        while (iterator.hasNext()) {
            Integer next = (Integer) iterator.next();
            if (next.equals(key)) {
                return;
            }
        }
        element.offerLast(key);
    }

    public void remove(int key) {
        int hash = hash(key);
        LinkedList element = elements[hash];
        Iterator iterator = element.iterator();
        while (iterator.hasNext()) {
            Integer next = (Integer) iterator.next();
            if (next.equals(key)) {
                element.remove(next);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        LinkedList element = elements[hash];
        Iterator iterator = element.iterator();
        while (iterator.hasNext()) {
            Integer next = (Integer) iterator.next();
            if (next.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private int hash(Integer key) {
        return key % capacity;
    }
}
