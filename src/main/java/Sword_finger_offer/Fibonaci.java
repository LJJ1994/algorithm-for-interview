package Sword_finger_offer;

public class Fibonaci {
    public int getFib(int n) {
        int[] result = {0, 1};
        if (n <= 1) {
            return result[n];
        }
        int fibMinOne = 1;
        int fibMinTwo = 0;
        int fibN = 0;

        for (int i = 2; i <= n; i++) {
            fibN = fibMinOne + fibMinTwo;
            fibMinTwo = fibMinOne;
            fibMinOne = fibN;
        }
        return fibN;
    }

    public int getFib01(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return getFib01(n - 1) + getFib01(n - 2);
    }

    public int fib(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void main(String[] args) {
        int n = 45;
        Fibonaci fibonaci = new Fibonaci();
        System.out.println(fibonaci.getFib(n));
        System.out.println(fibonaci.getFib01(n));
        System.out.println(fibonaci.fib(n));
    }
}
