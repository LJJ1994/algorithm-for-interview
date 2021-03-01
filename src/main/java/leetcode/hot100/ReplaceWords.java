package leetcode.hot100;

import java.util.*;

// 648. 单词替换
public class ReplaceWords {
    private StringBuilder res = new StringBuilder();
    private final Set<String> store = new HashSet<>();
    // 1. 哈希表法
    public String replaceWords(List<String> dictionary, String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return res.toString();
        }
        store.addAll(dictionary);

        String[] strings = sentence.split(" ");
        for (String s : strings) {
            if (res.length() > 0) {
                res.append(" ");
            }
            String prefix = "";
            for (int i = 1; i <= s.toCharArray().length; i++) {
                prefix = s.substring(0, i);
                // 如果有前缀，则添加前缀
                if (store.contains(prefix)) {
                    break;
                }
            }
            // 没有前缀，则添加单词
            res.append(prefix);
        }

        return res.toString();
    }

    // 前缀树法
    public String replaceWords1(List<String> dictionary, String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return res.toString();
        }
        TrieNode trie = new TrieNode();
        // 添加词根到 trie
        for (String root : dictionary) {
            TrieNode cur = trie;
            for (int i = 0; i < root.toCharArray().length; i++) {
                char c = root.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            // 将词根添加到最后一个节点
            cur.word = root;
        }

        String[] strings = sentence.split(" ");
        for (String word : strings) {
            if (res.length() > 0) {
                res.append(" ");
            }
            TrieNode cur = trie;
            for (int i = 0; i < word.toCharArray().length; i++) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null || cur.word != null) {
                    break;
                }
                cur = cur.children[c - 'a'];
            }
            res.append(cur.word == null ? word : cur.word);
        }
        return res.toString();
    }

    private static class TrieNode {
        private final int R = 26;
        public TrieNode[] children;
        public String word;
        public TrieNode() {
            children = new TrieNode[R];
        }
    }

    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>(Arrays.asList("cat","bat","rat"));
        String sentence = "the cattle was rattled by the battery";

        ReplaceWords replaceWords = new ReplaceWords();
        String s = replaceWords.replaceWords(dictionary, sentence);

        System.out.println(s);
    }
}
