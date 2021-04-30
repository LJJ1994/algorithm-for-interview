
package leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//22 括号生成
public class GenerateParenthesis {
    // 1. 动态规划
    public List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<>();
        List<LinkedList<String>> total = new ArrayList<>();
        LinkedList<String> list1 = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        list1.add("");
        list2.add("()");
        total.add(list1);
        total.add(list2);
        for (int i = 2; i <= n; ++i) {
            LinkedList<String> result = new LinkedList<>();
            for (int j = 0; j < i; ++j) {
                LinkedList<String> nowList1 = total.get(j);
                LinkedList<String> nowList2 = total.get(i - 1 - j);
                for (String k1 : nowList1) {
                    for (String k2 : nowList2) {
                        if (k1 == null) {
                            k1 = "";
                        }
                        if (k2 == null) {
                            k2 = "";
                        }
                        String el = "(" + k1 + ")" + k2;
                        result.add(el);
                    }
                }
            }
            total.add(result);
        }
        return total.get(n);
    }

    // 回溯
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs("", n, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, List<String> res) {
        // 左括号剩余量 和 右括号剩余量 均为0，则匹配成功
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left > right) return;

        // 左括号剩余量减1，dfs
        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        // 右括号剩余量减1，dfs
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }
}
