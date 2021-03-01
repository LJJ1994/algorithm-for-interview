package leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

// 双向链表放置数据节点，且维护最近最少使用的元素
// 哈希表用于定位缓存项，及定位双向链表的元素，以进行后续操作
public class LRUCache {
    // 链表头部放置最新访问的值，链表尾部放置最久没有访问的值
    private final Map<Integer, DLinkedNode> cache;
    private DLinkedNode head;
    private DLinkedNode tail;
    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 移动到头部，最近使用
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        // 节点不存在
        if (node == null) {
            DLinkedNode dLinkedNode = new DLinkedNode(key, value);
            cache.put(key, dLinkedNode);
            addToHead(dLinkedNode);
            size++;
            if (size > capacity) {
                size--;
                // 移除尾节点
                DLinkedNode tail = removeTail();
                // 移除缓存
                cache.remove(tail.key);
            }
        } else { // 节点存在
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    // 双向链表
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public DLinkedNode() {}
    }
}
