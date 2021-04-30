package Sword_finger_offer;

public class PowerWithExponent {
    public double powWith(double base, int exponent) {
        double result = 1.0;
        for(int i = 1; i <= exponent; ++i) {
            result *= base;
        }
        return result;
    }

    public double powWithRecursive(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        double result = powWithRecursive(base, exponent >>> 1);
        result *= result;
        // 判断 exponent 奇偶性
        // 判断为奇数，则多出一项基数 base
        if ((exponent & 1) == 1) {
            result *= base;
        }
        return result;
    }

    public double powWithWhile(double base, int exponent) {
        //n∈[−2147483648,2147483647] ，因此当 n = -2147483648n=−2147483648 时执行 n = -n 会因越界而赋值出错.
        long n = exponent;
        if (base == 0) {
            return 0;
        }
        // 指数为负数，则进行转换
        if (n < 0) {
            base = 1 / base;
            n = -n;
        }
        double res = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= base;
            }
            base *= base;
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        PowerWithExponent power = new PowerWithExponent();
        System.out.println(power.powWith(2, 10));
        System.out.println(power.powWithRecursive(2, 10));
        System.out.println(power.powWithWhile(2, 10));
    }
}
