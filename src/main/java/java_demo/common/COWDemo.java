package java_demo.common;


import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class COWDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CopyOnWriteArrayList<Long> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20 * 10000; i++) {
            list.add(System.nanoTime());
        }
        System.out.println("add() took time: " + (System.currentTimeMillis() - start) + " ms");

        long start1 = System.currentTimeMillis();
        ArrayList<Long> result = new ArrayList<>();
        for (int i = 0; i < 20 * 10000; i++) {
            result.add(System.nanoTime());
        }
        CopyOnWriteArrayList<Long> list1 = new CopyOnWriteArrayList<>();
        list1.addAll(result);
        System.out.println("addAll() took time: " + (System.currentTimeMillis() - start1) + " ms");
    }
}
