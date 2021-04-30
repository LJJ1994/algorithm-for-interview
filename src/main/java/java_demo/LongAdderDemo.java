package java_demo;



import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
//    private static final LongAdder count = new LongAdder();
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final int COUNT_THREADS = 2000;
    private static final CountDownLatch latch = new CountDownLatch(2000);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2000);
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        // FIXME : LJJ , 2021-4-22 15:32, [2021-4-22 17:30]
        for (int i = 0; i < COUNT_THREADS; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    count.add(1);
                    count.addAndGet(1);
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(1000);
        System.out.println(count);
        System.out.println("took timed: " + (System.currentTimeMillis() - start) + "ms");
        System.exit(0);

    }
}
