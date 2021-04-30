package java_demo;

public class CovariantDemo {
    public static void main(String[] args) {
        String[] a = {"a", "b"};
        Object[] b = a;
        //b[0] = 1; // java.lang.ArrayStoreException
        System.out.println(b[0]);
        System.out.println(b[1]);
    }
}
