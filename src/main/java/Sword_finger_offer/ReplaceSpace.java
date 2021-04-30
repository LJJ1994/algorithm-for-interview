package Sword_finger_offer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
 */
public class ReplaceSpace {
    public String replaceSpace(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        StringBuilder builder = new StringBuilder();
        int length = s.length();
        int index = 0;
        while (length > 0) {
            if (s.charAt(index) == ' ') {
                builder.append("%20");
            } else {
                builder.append(s.charAt(index));
            }
            index++;
            length--;
        }
        return builder.toString();
    }

    // 字符数组方法
    public String replaceSpace01(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int length = s.length();
        char[] chars = new char[length * 3]; // 当遇到" "时，每个" "转换为 %20, 一个字符变为3个
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            } else {
                chars[size++] = c;
            }
        }
        String newStr = new String(chars, 0 , size);
        return newStr;
    }

    public static void main(String[] args) {
        String s = "We are happy.";
        ReplaceSpace replaceSpace = new ReplaceSpace();
        System.out.println(s);

        String space = replaceSpace.replaceSpace(s);
        System.out.println(space);

        String space01 = replaceSpace.replaceSpace01(s);
        System.out.println(space01);

    }
}
