package java_demo;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    private static int count = 0;
    public static void main(String[] args) {
        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("after 1000ms, this task executed!");
//            }
//        }, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                count += 1;
                System.out.println("current count: " + count);
                if (count == 10) {
                    System.exit(0);
                }
            }
        }, 2000, 1000);
    }

    public boolean isNumber(int any) {
        return any >= 1 && any <= 10;
    }
}
