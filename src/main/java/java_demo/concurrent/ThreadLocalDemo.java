package java_demo.concurrent;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static final ThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {

        threadLocal.set(0);
        System.out.println("set后的父线程的值：" + threadLocal.get());
        Runnable task = () -> {

            System.out.println("start========================");
            System.out.println("执行前的父线程的值: " + threadLocal.get());
            threadLocal.set(1);
            System.out.println("执行后的父线程的值: " + threadLocal.get());
            System.out.println("end===========================");
        };
        TtlRunnable ttlRunnable = TtlRunnable.get(task);
        executorService.submit(ttlRunnable);
        Thread.sleep(1);
        executorService.submit(ttlRunnable);
        Thread.sleep(1);
        executorService.submit(ttlRunnable);
        Thread.sleep(1);
        executorService.shutdownNow();
    }
}
