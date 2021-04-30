
package leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

// 677. 键值映射
public class MapSum1 {
    private final Map<String, Integer> store;
    /** Initialize your data structure here. */
    public MapSum1() {
        store = new HashMap<>();
    }

    public void insert(String key, int val) {
        store.put(key, val);
    }

    public int sum(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return 0;
        }
        int sum = 0;
        for (Map.Entry<String, Integer> entry : store.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (key.startsWith(prefix)) {
                sum += value;
            }
        }
        return sum;
    }
}
