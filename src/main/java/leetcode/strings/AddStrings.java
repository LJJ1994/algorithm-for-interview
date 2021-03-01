package leetcode.strings;

// 415. 字符串相加

/**
 * num1: "3234"  "1234"
 * num2: "8016"  "678"
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        int step = 0;
        int m = num1.length();
        int n = num2.length();
        StringBuilder ans = new StringBuilder();
        for (int i = m - 1, j = n - 1; i >= 0 || j >= 0; --i, --j) {
            int sum = step;
            sum += i >= 0 ? num1.charAt(i) - '0' : 0;
            sum += j >= 0 ? num2.charAt(j) - '0' : 0;
            ans.append(sum % 10);
            if (sum >= 10 && sum <= 18) {
                step = 1;
            } else {
                step = 0;
            }
        }
        // 多出来的一位
        if (step == 1) {
            ans.append(step);
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {

        String num1 = "3234";
        String num2 = "8016";
        AddStrings addBinary = new AddStrings();
        String s = addBinary.addStrings(num1, num2);
        System.out.println(s);
    }
}
