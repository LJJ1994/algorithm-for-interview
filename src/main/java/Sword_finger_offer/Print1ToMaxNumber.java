package Sword_finger_offer;

public class Print1ToMaxNumber {
    int n, count = 0;
    StringBuilder res;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public String printNumber(int n) {
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

    public void print(int n) {
        int i = 0;
        int number = 1;
        while (i++ < n) {
            number *= 10;
        }

        for (int j = 1; j < number; ++j) {
            System.out.println(j);
        }
    }

    public static void main(String[] args) {
        Print1ToMaxNumber print = new Print1ToMaxNumber();
//        print.print(2);
        System.out.println(print.printNumber(2));
    }
}
