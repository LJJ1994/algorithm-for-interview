
package leetcode.hot100;

import java.util.*;

// 692. 前K个高频单词
public class TopKFrequent {
    private final List<String> res = new ArrayList<>();
    private final Map<String, Integer> store = new HashMap<>();
    private final PriorityQueue<String> minHeap = new PriorityQueue<String>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return store.get(o1).equals(store.get(o2)) ? o2.compareTo(o1) : store.get(o1) - store.get(o2);
        }
    });

    // 小顶堆方法
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return res;
        }
        for (String s : words) {
            store.put(s, store.getOrDefault(s, 0) + 1);
        }
        for (String s : store.keySet()) {
            minHeap.offer(s);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }
        Collections.reverse(res);
        return res;
    }

    // 哈希表法
    public List<String> topKFrequent1(String[] words, int k) {
        if (words == null || words.length == 0) {
            return res;
        }
        for (String s : words) {
            store.put(s, store.getOrDefault(s, 0) + 1);
        }
        List<String> res = new ArrayList<>(store.keySet());
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return store.get(o1).equals(store.get(o2)) ? o1.compareTo(o2) : store.get(o2) - store.get(o1);
            }
        });
        return res.subList(0, k);
    }

    public static void main(String[] args) {
        String[] strings = {"i", "love", "leetcode", "i", "love", "coding"};
        TopKFrequent kFrequent = new TopKFrequent();
        List<String> list = kFrequent.topKFrequent1(strings, 2);
        System.out.println(Arrays.toString(list.toArray(new String[0])));
    }
}
