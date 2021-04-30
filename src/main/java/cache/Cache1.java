package cache;

import java.util.concurrent.*;

// 使用ConcurrentHashMap做内置缓存
public class Cache1 {
    private static final ConcurrentHashMap<String, Entity> map = new ConcurrentHashMap<String, Entity>();
    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public static void put(String key, Object value, long expires) {
        remove(key);
        if (expires > 0) {
            Future future = executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    map.remove(key);
                }
            }, expires, TimeUnit.MILLISECONDS);
            map.put(key, new Entity(value, future));
        } else {
            map.put(key, new Entity(value, null));
        }
    }

    public static Object get(String key) {
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }

    public static <T> T get(String key, Class<T> clazz) {
        return clazz.cast(map.get(key));
    }

    public static Object remove(String key) {
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

    public static int size() {
        return map.size();
    }

    private static class Entity {
        Object value;
        Future future;

        public Entity(Object value, Future future) {
            this.value = value;
            this.future = future;
        }

        public Object getValue() {
            return value;
        }

        public Future getFuture() {
            return future;
        }
    }
}
