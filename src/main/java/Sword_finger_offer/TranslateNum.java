package Sword_finger_offer;

public class TranslateNum {
    public int translateNum(int num) {
        if (num < 0) return 0;
        String str = String.valueOf(num);
        return getTranslation(str);
    }

    private int getTranslation(String str) {
        int length = str.length();
        int[] counts = new int[length];
        int count = 0;

        for (int i = length - 1; i >= 0 ; --i) {
            count = 0;
            if (i < length - 1) {
                count = counts[i + 1];
            } else {
                count = 1;
            }

            if (i < length - 1) {
                int digit1 = str.charAt(i) - '0';
                int digit2 = str.charAt(i + 1) - '0';
                int converted = digit1 * 10 + digit2;
                if (converted >= 10 && converted <= 25) {
                    if (i < length - 2) {
                        count += counts[i + 2];
                    } else {
                        count += 1;
                    }
                }
            }

            counts[i] = count;
        }

        count = counts[0];
        return count;
    }

    public static void main(String[] args) {
        int num = 12258;
        TranslateNum translateNum = new TranslateNum();
        int i = translateNum.translateNum(num);
        System.out.println(i);
    }
}
