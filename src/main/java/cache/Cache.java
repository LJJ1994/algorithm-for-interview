package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

// 用HashMap + 定时器 定制一个非分布式缓存
public class Cache {
    private static Map<String, Entity> map = new HashMap<String, Entity>();
    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static synchronized void put(String key, Object value) {
        put(key, value, 0);
    }

    public static synchronized void put(final String key, final Object value, long expires) {
        Cache.remove(key);
        if (expires > 0) {
            Future future = executorService.schedule(new Runnable() {
                public void run() {
                    synchronized (Cache.class) {
                        map.remove(key);
                    }
                }
            }, expires, TimeUnit.MILLISECONDS);
            map.put(key, new Entity(value, future));
        } else {
            map.put(key, new Entity(value, null));
        }
    }

    public static synchronized Object get(String key) {
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }

    public static synchronized <T> T get(String key, Class<T> cla) {
        return cla.cast(map.get(key));
    }

    public static synchronized Object remove(String key) {
        Entity entity = map.remove(key);
        if (entity == null) {
            return null;
        }
        Future future = entity.getFuture();
        if (future != null) {
            future.cancel(true);
        }
        return entity.getValue();
    }

    public static synchronized int size() {
        return map.size();
    }

    private static class Entity {
        Object value;
        Future future;

        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }

        public Future getFuture() {
            return future;
        }

        public Object getValue() {
            return value;
        }
    }
}
