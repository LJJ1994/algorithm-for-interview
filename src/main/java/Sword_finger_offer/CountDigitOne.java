package Sword_finger_offer;

// 1~n 整数中1出现的次数
public class CountDigitOne {
    public int countDigitOne(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += numberOf1(i);
        }
        return sum;
    }

    private int numberOf1(int n) {
        int sum = 0;
        while (n != 0) {
            if ((n % 10) == 1) {
                sum += 1;
            }
            n = n / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        CountDigitOne digitOne = new CountDigitOne();
        int one = digitOne.countDigitOne(12);
        System.out.println(one);
    }
}
