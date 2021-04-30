
package leetcode.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//17. 电话号码的字母组合
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() <= 0) return combinations;
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrace(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    private void backtrace(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        // 匹配完整个数字（一轮），则直接添加到结果
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }

        char digit = digits.charAt(index);
        // 选择列表：可供选择的字符
        String letters = phoneMap.get(digit);
        int count = letters.length();
        for (int i = 0; i < count; i++) {
            // 作出选择
            combination.append(letters.charAt(i));
            // 进入下一层决策树
            backtrace(combinations, phoneMap, digits, index + 1, combination);
            // 回溯，撤销选择
            combination.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        LetterCombinations combinations = new LetterCombinations();
        List<String> list = combinations.letterCombinations(digits);
        for (String s : list) {

            System.out.println(s);
        }
    }
}
