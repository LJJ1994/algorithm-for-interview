package java_demo;

public class OverloadTest {
    public void test() {
        System.out.println("test void");
    }

    public int test(int a) {
        System.out.println("test int: " + a);
        return a;
    }

    public void test(int a, String b) {
        System.out.println("1.test a + b: " + (a + b));
    }

    public void test(String a, int b) {
        System.out.println("2.test a + b: " + (a + b));
    }

    public void test(int... a) {
        int sum = 0;
        for (int arg : a) {
            sum += arg;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        OverloadTest test = new OverloadTest();
        test.test(1, "2");
        test.test("2", 3);
        test.test(1, 2, 3);
    }
}
