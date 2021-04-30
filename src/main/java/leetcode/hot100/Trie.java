
package leetcode.hot100;

// 208. 实现 Trie (前缀树)
public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode trieNode = searchPrefix(word);
        // trieNode 不为空，并且该节点被标记为end
        return trieNode != null && trieNode.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode trieNode = searchPrefix(prefix);
        return trieNode != null;
    }

    // 搜索Trie中的前缀键
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                return null;
            }
            node = node.get(ch);
        }
        return node;
    }

    private static class TrieNode {
        public final int R = 26;
        public TrieNode[] links;
        public boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void  setEnd() {
            isEnd = true;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }
    }
}
