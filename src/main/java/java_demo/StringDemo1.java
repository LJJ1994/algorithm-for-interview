package java_demo;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringDemo1 {
    public static void main(String[] args) {
        String a = "hello";
        String b = a + "world";
        System.out.println(b);

        System.out.println(a.codePointAt(0));

        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));

        String s = new String(bytes);
        System.out.println(s);

        String str = "username=ljj&&pass=940617&&type=common&&ismember=true";
        String[] split = str.trim().split("&&");
        Map<String, String> map = new HashMap<>();
        for (String s1 : split) {
            String[] split1 = s1.split("=");
            map.put(split1[0], split1[1]);
        }

        System.out.println(Arrays.toString(split));
        System.out.println(map);
        System.out.println(map.get("username"));
        System.out.println(map.get("pass"));
        System.out.println(map.get("type"));
        System.out.println(map.get("ismember"));
    }
}
