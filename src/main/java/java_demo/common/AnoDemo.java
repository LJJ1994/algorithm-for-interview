package java_demo.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnoDemo {
    public int value = 4;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    public void doIt() {
        int value = 6;
//        Runnable runnable = new Runnable() {
//            public final int value = 5;
//            @Override
//            public void run() {
//                int value = 10;
//                System.out.println("local value: " + value);
//                System.out.println("this.value: " + this.value);
//                System.out.println("wrapper class value: " + AnoDemo.this.value);
//            }
//        };
//        runnable.run();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
//                System.out.println(this.value); 访问不到
                System.out.println(AnoDemo.this.value);
            }
        });
    }

    public static class Person {
        public String name = "asd";
    }

    public static void main(String[] args) {
        AnoDemo anoDemo = new AnoDemo();
        anoDemo.doIt();
        Person person = new Person();
        System.out.println(person.name);
    }
}
