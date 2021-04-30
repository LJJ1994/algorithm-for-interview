package java_demo.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateDemo2 {
    private static final ExecutorService pool = Executors.newFixedThreadPool(100);
    private static final CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        Set<String> sets = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String s = formatter.format(LocalDateTime.now());
                sets.add(s);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(sets.size());
        System.exit(0);
    }
}
