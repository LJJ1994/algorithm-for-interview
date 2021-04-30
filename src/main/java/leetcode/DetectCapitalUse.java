package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 520. 检测大写字母
public class DetectCapitalUse {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        String UPPER = word.toUpperCase();
        String LOWER = word.toLowerCase();
        if (word.equals(UPPER)) {
            return true;
        }
        if (word.equals(LOWER)) {
            return true;
        }

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (i == 0) {
                String val = String.valueOf((char) c).toUpperCase();
                if (!val.equals(word.substring(0, 1))) {
                    return false;
                }
            } else {
                if (c < 97) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean detectCapitalUse1(String word) {
        Pattern compile = Pattern.compile("^([A-Z][a-z]+|[A-Z]+|[a-z]+)$");
        Matcher matcher = compile.matcher(word);
        return matcher.find();
    }

    public static void main(String[] args) {

//        String name;
//        for(int i = 1;i<=26;i++) {
//            name = String.valueOf((char) (64 + i));
//            System.out.println(name);
//        }
        String a = "USA";
        String b = "leetcode";
        String c = "Google";
        String d = "DataSource";
        String e = "aData";
        DetectCapitalUse detect = new DetectCapitalUse();
//        System.out.println(detect.detectCapitalUse(a));
//        System.out.println(detect.detectCapitalUse(b));
//        System.out.println(detect.detectCapitalUse(c));
//        System.out.println(detect.detectCapitalUse(d));
//        System.out.println(detect.detectCapitalUse(e));
        System.out.println(detect.detectCapitalUse1(a));
        System.out.println(detect.detectCapitalUse1(b));
        System.out.println(detect.detectCapitalUse1(c));
        System.out.println(detect.detectCapitalUse1(d));
        System.out.println(detect.detectCapitalUse1(e));

        System.out.println(11 / 3);

        String s = Arrays.toString(a.toUpperCase().split("-"));
    }
}
