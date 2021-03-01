package leetcode.strings;

//58. 最后一个单词的长度
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        String[] strs = s.split(" ");
        String lastWord = strs[strs.length - 1];
        return lastWord.length();
    }

    public int lengthOfLastWord01(String s) {
        if (s == null || s.length() == 0) return 0;
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    public static void main(String[] args) {
        String s = "Hello World you know my love ";
//        String[] strings = s.split(" ");
//        System.out.println(strings[strings.length - 1]);

        LengthOfLastWord lastWord = new LengthOfLastWord();
        int i = lastWord.lengthOfLastWord01(s);
        System.out.println(i);
    }
}
