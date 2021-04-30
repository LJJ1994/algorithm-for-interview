package cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CacheTest1 {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 并发测试
        String key = "id";
        Future[] futures = new Future[10];
        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                futures[i] = executorService.submit(() -> {
                    for (int j = 0; j < 500000; j++) {
                        Cache1.put(Thread.currentThread().getId() + key + j, j, 300000);
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                futures[i].get();
            }
            System.out.println("put took time: " + (System.currentTimeMillis() - start) + "millis");
        }

        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                futures[i] = executorService.submit(() -> {
                    for (int j = 0; j < 500000; j++) {
                        Cache1.get(Thread.currentThread().getId() + key + j);
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                futures[i].get();
            }
            System.out.println("get took time: " + (System.currentTimeMillis() - start) + "millis");
        }
        System.out.println("当前缓存容量：" + Cache1.size());
        System.exit(0);
    }
}
