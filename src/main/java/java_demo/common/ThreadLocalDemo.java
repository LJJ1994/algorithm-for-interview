package java_demo.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            User user = new User();
            executorService.execute(user);
        }
    }

    private static class User extends Thread{
        private static boolean flag = true;
        @Override
        public void run() {
            try {
                if (flag) {
                    threadLocal.set(this.getName() + ", session info");
                    flag = false;
                }
                System.out.println(this.getName() + " 线程是: " + threadLocal.get());
            } finally {
                threadLocal.remove();
                flag = true;
            }
        }
    }
}
