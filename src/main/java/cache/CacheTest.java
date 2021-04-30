package cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CacheTest {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String key = "id";
//        System.out.println("===================不设置过期时间");
//        Cache.put(key, "no expires!");
//        System.out.println(Cache.get(key));
//        Thread.sleep(3000);
//        System.out.println("Thread sleep 3000 millis...");
//        System.out.println(Cache.remove(key));
//        System.out.println(Cache.get(key));

//        System.out.println("===================设置过期时间");
//        Cache.put(key, "expires!", 2000);
//        System.out.println(Cache.get(key));
//        Thread.sleep(5000);
//        System.out.println(Cache.get(key));
//        System.exit(0);

        // 并发测试
        Future[] futures = new Future[10];
        {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                futures[i] = executorService.submit(() -> {
                    for (int j = 0; j < 500000; j++) {
                        Cache.put(Thread.currentThread().getId() + key + j, j, 300000);
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
                        Cache.get(Thread.currentThread().getId() + key + j);
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                futures[i].get();
            }
            System.out.println("get took time: " + (System.currentTimeMillis() - start) + "millis");
        }
        System.out.println("当前缓存容量：" + Cache.size());
        System.exit(0);


    }
}
