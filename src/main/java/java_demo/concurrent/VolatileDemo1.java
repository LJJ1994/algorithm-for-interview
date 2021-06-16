package java_demo.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileDemo1 {
    private static volatile Person[] arr = new Person[10];
    static {
        Person person = new Person();
        person.name = "ljj";
        arr[0] = person;
    }
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
//                    arr[0].name = "asd";
                    Person person = new Person();
                    person.name = "new";
                    arr[0] = person;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    System.out.println("print cur arr[0].name: " + arr[0].name);
//                    if ("asd".equals(arr[0].name)) {
//                        System.out.println("结束!");
//                        break;
//                    }
//                    try {
//                        // 即使变量没有用volatile修饰，在CPU空闲时，也会将变量的最新值刷新到主内存中去。
////                        TimeUnit.MILLISECONDS.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    if ("new".equals(arr[0].name)) {
                        System.out.println("结束!");
                        break;
                    }
                }
            }
        }).start();
    }

    private static class Person {
        String name;
    }
}
