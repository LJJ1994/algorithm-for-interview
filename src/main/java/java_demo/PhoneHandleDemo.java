package java_demo;

public class PhoneHandleDemo {
    public static String handlePhoneNum(String num) {
        StringBuilder builder = new StringBuilder();
        // 182-7599-3801
        builder.append(num.substring(0, 3)).append("****").append(num.substring(7));
        return builder.toString();
    }

    public static void main(String[] args) {
        String num = "18275993801";
        System.out.println(num);
        String s = handlePhoneNum(num);
        System.out.println(s);
    }
}
