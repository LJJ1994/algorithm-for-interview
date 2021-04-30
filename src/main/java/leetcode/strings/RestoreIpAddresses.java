
package leetcode.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 93. 复原IP地址
public class RestoreIpAddresses {
    private final List<String> res = new ArrayList<>();
    private static final int SEGMENT_COUNTS = 4;
    private int[] segments = new int[SEGMENT_COUNTS];

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return res;
    }

    private void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEGMENT_COUNTS) {
            if (segStart == s.length()) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < SEGMENT_COUNTS; ++i) {
                    builder.append(segments[i]);
                    if (i != SEGMENT_COUNTS - 1) {
                        builder.append(".");
                    }
                }
                res.add(builder.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int ipAddr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            ipAddr = 10 * ipAddr + (s.charAt(segEnd) - '0');
            if (ipAddr > 0 && ipAddr <= 255) {
                segments[segId] = ipAddr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        List<String> list = restoreIpAddresses.restoreIpAddresses(s);
        for (String s1 : list) {
            System.out.println(s1);
        }

        System.out.println(1 / 2);
    }
}
