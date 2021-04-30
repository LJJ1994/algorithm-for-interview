package java_demo.date;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateDemo1 {
    private static volatile SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final ExecutorService pool = Executors.newFixedThreadPool(100);
    private static final CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        Set<String> sets = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Calendar calendar = Calendar.getInstance();
            pool.execute(() -> {
                calendar.add(Calendar.DATE, finalI);
                String s = null;
                synchronized (simpleDate) {
                    s = simpleDate.format(calendar.getTime());
                }
                sets.add(s);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(sets.size());
        System.exit(0);
    }
}
