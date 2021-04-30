package leetcode.system_design;

// 705. 设计哈希集合
public class MyHashSet {
    private final Integer[] elements;
    private int capacity;
    /** Initialize your data structure here. */
    public MyHashSet() {
        this.capacity = 1000001;
        elements = new Integer[capacity];

    }

    public void add(int key) {
        int hash = mod(key);
        if (elements[hash] != null) {
            return;
        }
        elements[hash] = key;
    }

    public void remove(int key) {
        int hash = mod(key);
        if (elements[hash] != null) {
            elements[hash] = null;
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = mod(key);
        if (elements[hash] != null) {
            return true;
        } else {
            return false;
        }
    }

    private int mod(int key) {
        int hash = hash(key);
        return hash % capacity;
    }

    private int hash(Integer key) {
        return (key == null) ? 0 : key.hashCode();
    }
}
