package java_demo.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
//    private static volatile int[] arr = new int[10];
    private static int[] arr = new int[10];
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    arr[0] = 10;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (arr[0] == 10) {
                        System.out.println("结束!");
                        break;
                    }
                    try {
                        // 即使变量没有用volatile修饰，在CPU空闲时，也会将变量的最新值刷新到主内存中去。
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
