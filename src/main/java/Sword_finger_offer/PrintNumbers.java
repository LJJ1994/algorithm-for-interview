package Sword_finger_offer;

public class PrintNumbers {
    int n, count = 0;
    StringBuilder res;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public String printNumbers(int n) {
        this.n = n;
        res = new StringBuilder();
        num = new char[n];
        dfs(0);
        res.deleteCharAt(num.length - 1);
        return res.toString();
    }

    private void dfs(int x) {
        if (x == n) {
            res.append(String.valueOf(num) + ", ");
            return;
        }
        for (char c : loop) {
            num[x] = c;
            dfs(x + 1);
        }
    }
}
