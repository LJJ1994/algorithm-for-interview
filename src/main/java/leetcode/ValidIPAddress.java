package leetcode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import sun.net.util.IPAddressUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 468. 验证IP地址
public class ValidIPAddress {
    // 1. 原生判断
    public String validIPAddress(String IP) {
        if (IP == null || IP.length() == 0) {
            return "Neither";
        }
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return validIPv4(IP);
        }else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validIPV6(IP);
        } else {
            return "Neither";
        }
    }

    private String validIPv4(String Ip) {
        String[] nums = Ip.split("\\.", -1);
        for (String num : nums) {
            if (num.length() == 0 || num.length() > 3) {
                return "Neither";
            }
            if (num.charAt(0) == '0' && num.length() != 1) {
                return "Neither";
            }
            for (char c : num.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return "Neither";
                }
            }
        }
        return "IPv4";
    }

    private String validIPV6(String Ip) {
        String hexdigits = "0123456789abcdefABCDEF";
        String[] strings = Ip.split(":", -1);
        for (String s : strings) {
            if (s.length() == 0 || s.length() > 4) {
                return "Neither";
            }
            for (char c : s.toCharArray()) {
                if (hexdigits.indexOf(c) == -1) {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }

    // 2.语言内置
    public String validIPAddress1(String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return IPAddressUtil.isIPv4LiteralAddress(IP) ? "IPv4" : "Neither";
        } else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return IPAddressUtil.isIPv6LiteralAddress(IP) ? "IPv6" : "Neither";
        } else {
            return "Neither";
        }
    }

    public static void main(String[] args) {
//        Pattern compile = Pattern.compile(".*[a-zA-Z]+.*");
//        System.out.println(compile.matcher("2001:0db8:85a3:0:0:8A2E:0370:7334").find());
//        System.out.println(compile.matcher("123456").find());
        String IPv4 = "172.16.254.1";
        String IPv6 = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        String NotIPv4 = "1e1.4.5.6";
        String NotIPv6 = "2001:0db8:85a3:0:0:8A2E:0370:7334:";

        ValidIPAddress validIPAddress = new ValidIPAddress();
        System.out.println(validIPAddress.validIPAddress1(IPv4));
        System.out.println(validIPAddress.validIPAddress1(IPv6));
        System.out.println(validIPAddress.validIPAddress1(NotIPv4));
        System.out.println(validIPAddress.validIPAddress1(NotIPv6));
    }
}
