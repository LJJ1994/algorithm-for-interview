package java_demo.forEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForEachDemo {
    private static final ExecutorService ex = Executors.newCachedThreadPool();
    public static void main(String[] args) {
        ex.execute(() -> System.out.println("Test decompile."));
        ex.shutdownNow();
    }
}
