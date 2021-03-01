package Sword_finger_offer;

// 数字转换成字符串
public class StrToInt {
    public int strToInt(String str) {
        char[] chars = str.trim().toCharArray();
        int i = 1;
        int sign = 1;
        int res = 0;
        int bndry = Integer.MAX_VALUE / 10; // 整数的最大边界除于10,，用于判断如果整数最后一位超过7，则直接返回
        if (chars[0] == '-') sign = -1;
        else if (chars[0] != '+') i = 0;
        for (int j = i; j < chars.length; ++j) {
            if (chars[j] < '0' || chars[j] > '9') break;
            if (res > bndry || res == bndry && chars[j] > '7') {
                if (sign == -1) return Integer.MIN_VALUE;
                else return Integer.MAX_VALUE;
            }
            res = 10 * res + (chars[j] - '0');
        }
        return sign * res;
    }

    public static void main(String[] args) {
        StrToInt strToInt = new StrToInt();
        int i = strToInt.strToInt("4193 with words");
        System.out.println(i);
    }
}
