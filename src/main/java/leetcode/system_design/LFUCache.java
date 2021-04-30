package leetcode.system_design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// 460. LFU 缓存(最近最久未使用缓存)
public class LFUCache {


    private Map<Integer, LinkedList<Node>> freqTable;
    private Map<Integer, Node> keyTable;
    private int capacity;
    private int minFreq;

    public LFUCache(int capacity) {
        freqTable = new HashMap<>();
        keyTable = new HashMap<Integer, Node>();
        this.capacity = capacity;
        minFreq = 0;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!keyTable.containsKey(key)) {
            return -1;
        }
        Node node = keyTable.get(key);
        int val = node.value;
        int freq = node.freq;
        freqTable.get(freq).remove(node);
        if (freqTable.get(freq).size() == 0) {
            freqTable.remove(freq);
            if (minFreq == freq) {
                // 因为这里对node进行get访问操作，如果是相等，要加1
                minFreq += 1;
            }
        }
        LinkedList<Node> list = freqTable.getOrDefault(freq + 1, new LinkedList<>());
        list.offerFirst(new Node(key, val, freq + 1));
        freqTable.put(freq + 1, list);
        keyTable.put(key, freqTable.get(freq + 1).peekFirst());
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!keyTable.containsKey(key)) {
            if (capacity == keyTable.size()) {
                Node last = freqTable.get(minFreq).peekLast();
                keyTable.remove(last.key);
                freqTable.get(minFreq).pollLast();
                if (freqTable.get(minFreq).size() == 0) {
                    freqTable.remove(minFreq);
                }
            }
            LinkedList<Node> list = freqTable.getOrDefault(1, new LinkedList<>());
            list.offerFirst(new Node(key, value, 1));
            freqTable.put(1, list);
            keyTable.put(key, freqTable.get(1).peekFirst());
            // 不包含键，说明这个key第一次放入，当做访问一次，最小为1
            minFreq = 1;
        } else {
            Node node = keyTable.get(key);
            int val = node.value;
            int freq = node.freq;
            freqTable.get(freq).remove(node);
            if (freqTable.get(freq).size() == 0) {
                freqTable.remove(freq);
                if (minFreq == freq) {
                    // 因为这里对node进行get访问操作，如果是相等，要加1
                    minFreq += 1;
                }
            }
            LinkedList<Node> list = freqTable.getOrDefault(freq + 1, new LinkedList<>());
            list.offerFirst(new Node(key, value, freq + 1));
            freqTable.put(freq + 1, list);
            keyTable.put(key, freqTable.get(freq + 1).peekFirst());
        }
    }

    private class Node {
        int key;
        int value;
        int freq;

        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
}
