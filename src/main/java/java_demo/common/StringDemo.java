package java_demo.common;

public class StringDemo {
    public static void main(String[] args) {
        String str1 = "abc";

        String str2 = new String("abc");
        System.out.println(str1.equals(str2));

        String intern = str1.intern();
        System.out.println(intern);

        boolean flag = true;
        Integer i = Integer.valueOf(0);
        int j = 1;
        int k = flag ? i.intValue() : j;
        System.out.println(k);

        Integer i1 = 1;
        Integer i2 = 1;
        System.out.println(i1 == i2);

        Integer i3 = new Integer(2);
        Integer i4 = new Integer(2);
        System.out.println(i3 == i4);

        System.out.println("=============================");

        System.out.println(test01());
    }

    private static int test01() {
        int i = 1;
        try {
            return i;
        } finally {
            System.out.println("finally invoked!");

        }
    }
}
