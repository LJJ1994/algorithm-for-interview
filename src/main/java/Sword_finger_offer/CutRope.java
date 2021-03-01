package Sword_finger_offer;

public class CutRope {
    // 动态规划
    public int cutRope01(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        if (n == 3) {
            return 2;
        }
        int[] products = new int[n + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        int max = 0;
        for (int i = 4; i <= products.length; ++i) {
            max = 0;
            for (int j = 1; j <= i / 2; ++j) {
                int product = products[j] * products[i-j];
                if (product > max) {
                    max = product;
                }
                products[i] = max;
            }
        }
        max = products[n];
        return max;
    }

    // 贪心算法，尽量剪成长度为3的段
    public int cutRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3;
        int b = n % 3;

        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }

    public static void main(String[] args) {
        CutRope cutRope = new CutRope();
        System.out.println(cutRope.cutRope(2));
        System.out.println(cutRope.cutRope(3));

        System.out.println(cutRope.cutRope01(2));
        System.out.println(cutRope.cutRope01(3));
    }
}
