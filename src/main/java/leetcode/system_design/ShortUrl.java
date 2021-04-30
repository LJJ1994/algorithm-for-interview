package leetcode.system_design;

import java.security.MessageDigest;

/**
 * 短网址生成主要算法：
 * 将长网址md5生成32位签名串，分为4段，每段8个字节；
 * 对这四段循环处理，取8个字节，将他看成16进制串与0x3fffffff(30位1)与操作，即超过30位的忽略处理；
 * 这30位分成6段，每5位的数字作为字母表的索引取得特定字符，依次进行获得6位字符串；
 * 总的md5串可以获得4个6位串；取里面的任意一个就可作为这个长url的短url地址；
 */
public class ShortUrl {
    // 要使用生成 URL 的字符
    private static final String[] chars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
            "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"
    };

    public static void main(String[] args) {
        String sLongUrl = "https://zhangvalue.blog.csdn.net/"; // 长链接
        String[] aResult = shortUrl(sLongUrl);
        // 打印出结果
        for (int i = 0; i < aResult.length; i++) {
            System.out.println("[" + i + "]:::" + aResult[i]);
        }
    }

    public static String[] shortUrl(String url) {
        String key = "mimvp.com";                               // 可以自定义生成 MD5 加密字符传前的混合加密key
        String hex = EncryptInner.md5(key + url);

        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteter.parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHexLong;     // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                outChars += chars[(int) index];         // 把取得的字符相加
                lHexLong = lHexLong >> 5;             // 每次循环按位右移 5 位
            }
            resUrl[i] = outChars;                       // 把字符串存入对应索引的输出数组
        }
        return resUrl;
    }

    private static class EncryptInner {
        // 十六进制下数字到字符的映射数组
        private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        /** 把inputString加密 */
        public static String md5(String inputStr) {
            return encodeByMD5(inputStr);
        }
        /**
         * 验证输入的密码是否正确
         *
         * @param password 真正的密码（加密后的真密码）
         * @param inputString 输入的字符串
         * @return 验证结果，boolean类型
         */
        public static boolean authenticatePassword(String password, String inputString) {
            if (password.equals(encodeByMD5(inputString))) {
                return true;
            } else {
                return false;
            }
        }

        /** 对字符串进行MD5编码 */
        private static String encodeByMD5(String originString) {
            if (originString != null) {
                try {
                    // 创建具有指定算法名称的信息摘要
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    // 使用指定的字节数组进行摘要计算
                    byte[] results = md5.digest(originString.getBytes());
                    // 将得到的字节数组变成字符串返回
                    String result = byteArrayToHexString(results);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * 轮换字节数组为十六进制字符串
         *
         * @param b 字节数组
         * @return 十六进制字符串
         */
        private static String byteArrayToHexString(byte[] b) {
            StringBuffer resultSb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                resultSb.append(byteToHexString(b[i]));
            }
            return resultSb.toString();
        }

        // 将一个字节转化成十六进制形式的字符串
        private static String byteToHexString(byte b) {
            int n = b;
            if (n < 0) {
                n = 256 + n;
            }
            int d1 = n / 16;
            int d2 = n % 16;
            return hexDigits[d1] + hexDigits[d2];
        }

        public static void main(String[] args) {

            String s = EncryptInner.md5("https://zhangvalue.blog.csdn.net/");

            System.out.println(s);
        }
    }
}
