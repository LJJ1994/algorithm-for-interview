package java_demo.common;

public class NPEDemo {
    public static void main(String[] args) {
        Integer i = null;
        int i1 = i.intValue();
        System.out.println(i1);
    }
}
