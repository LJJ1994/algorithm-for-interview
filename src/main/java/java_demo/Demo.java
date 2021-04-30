package java_demo;

import com.google.common.base.Stopwatch;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Demo {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10000).parallel().forEach(i -> new Data().increment());
        System.out.println("counter: " + Data.getCounter());
    }

    private static class Data {
        private static int counter = 0;
        public static void reset() {
            counter = 0;
        }

        public void increment() {
            synchronized(Data.class) {
                counter++;
            }
        }

        public static int getCounter() {
            return counter;
        }
    }
}
