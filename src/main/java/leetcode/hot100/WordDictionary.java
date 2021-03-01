package leetcode.hot100;

// 单词与搜索
public class WordDictionary {
    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode node = root;
        boolean isEnd = searchPrefix(word, node);
        return isEnd;
    }

    // 搜索Trie中的前缀键
    private boolean searchPrefix(String word, TrieNode root) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (ch == '.') {
                for (TrieNode link : node.links) {
                    if (link != null) {
                        boolean isEnd = searchPrefix(word.substring(i + 1), link);
                        if (isEnd) {
                            return true;
                        }
                    }
                }
                return false;
            }
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.isEnd();
    }

    private static class TrieNode {
        public final int R = 26;
        public TrieNode[] links;
        public boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
            isEnd = false;
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
