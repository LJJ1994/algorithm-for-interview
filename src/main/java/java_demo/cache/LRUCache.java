package java_demo.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_CACHE_CAPACITY = 3;
    private int limit;
    public LRUCache(int limit) {
        super(limit, 0.75f, true);
        this.limit = limit;
    }

    public LRUCache() {
        this(MAX_CACHE_CAPACITY);
    }

    public V putVal(K key, V val) {
        return put(key, val);
    }

    public V getVal(K key) {
        return get(key);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > limit;
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>();
        cache.put("张三", "1");
        cache.put("李四", "2");
        cache.put("王五", "3");

        System.out.println(cache.get("王五"));
        cache.put("赵六", "4");
        System.out.println(cache);
    }
}
