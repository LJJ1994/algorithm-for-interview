package leetcode.hot100;

import java.util.ArrayDeque;
import java.util.Queue;

public class MapSum {
    private TrieNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode node = root;
        for (int i = 0; i < key.toCharArray().length; ++i) {
            char c = key.charAt(i);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.value = val;
    }

    public int sum(String prefix) {
        return bfs(prefix, root);
    }

    private int bfs(String prefix, TrieNode root) {
        if (prefix == null || prefix.length() == 0) {
            return 0;
        }
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (cur.children[c - 'a'] == null) {
                return 0;
            }
            cur = cur.children[c - 'a'];
        }
        int sum = 0;

        Queue<TrieNode> queue = new ArrayDeque<>();
        queue.add(cur);
        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            sum += node.value;
            for (TrieNode child : node.children) {
                if (child != null) {
                    queue.add(child);
                }
            }
        }
        return sum;
    }

    private static class TrieNode {
        public TrieNode[] children;
        public Integer value;
        private final int R = 26;
        public TrieNode() {
            children = new TrieNode[R];
            value = 0;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }
}
