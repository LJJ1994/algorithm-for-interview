package java_demo.concurrent;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDemo {
    private static final int LOOP_TIMES = 1000 * 1000;
    public static void main(String[] args) {

        // 适用于读多写少的场景
        CopyOnWriteArrayList<Integer> copy = new CopyOnWriteArrayList<Integer>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_TIMES;i++) {
            copy.add(i);
        }
        System.out.println("time: " + (System.currentTimeMillis() - start));
    }

}
