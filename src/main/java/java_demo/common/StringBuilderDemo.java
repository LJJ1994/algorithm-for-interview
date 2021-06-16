package java_demo.common;

public class StringBuilderDemo {
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        String s = "hello";
//        for (int i = 0; i < 100000; i++) {
//            s += "world";
//        }
//        System.out.println("'+' operation took timed: " + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder("hello");
        for (int i = 0; i < 100000; i++) {
            builder.append("world");
        }
        System.out.println("'StringBuilder' append() took timed: " + (System.currentTimeMillis() - start1));
    }
}
