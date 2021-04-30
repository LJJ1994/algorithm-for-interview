
package crashing_interview;

import java.util.Random;

//打印从 1 到 n 中 2 的幂数。例如，如果 n 等于 4，它将打印 1、 2、 4。
public class PowerOf2 {
    public int powerOf2(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            System.out.println(1);
            return 1;
        } else {
            int prev = powerOf2(n / 2);
            int curr = prev * 2;
            System.out.println(curr);
            return curr;
        }
    }

    public static void main(String[] args) {
        PowerOf2 power = new PowerOf2();
        power.powerOf2(50);
    }
}

