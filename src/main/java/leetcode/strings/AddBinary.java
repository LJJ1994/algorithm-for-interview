package leetcode.strings;

// 二进制求和

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AddBinary {
    public String addBinary(String a, String b) {
        // 进位
        StringBuilder ans = new StringBuilder();
        int step = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; --i, --j) {
            int sum = step;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            step = sum / 2;
        }
        if (step == 1) {
            ans.append(step);
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010", b = "1011";
        AddBinary addBinary = new AddBinary();
        String s = addBinary.addBinary(a, b);
        System.out.println(s);
        Deque<int[]> stack = new ArrayDeque<>();
        System.out.println(Math.pow(2.0, 0));
    }
}
