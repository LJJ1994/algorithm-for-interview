package leetcode.system_design;

import java.util.HashMap;

// 706. 设计哈希映射
public class MyHashMap {
    private final Integer[] elements;
    /** Initialize your data structure here. */
    public MyHashMap() {
        elements = new Integer[1 << 30];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = hash(key);
        if (elements[hash] != null) {
            elements[hash] = value;
            return;
        }
        elements[hash] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hashCode = hash(key);
        System.out.println("hashCode: " + hashCode);
        Integer result = -1;
        return (result = elements[hashCode]) == null ? -1 : result;
    }

    private int hash(Integer key) {
        return (key == null) ? 0 : key.hashCode();
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = hash(key);
        if (elements[hash] == null) {
            return;
        } else {
            elements[hash] = null;
        }
    }
}
