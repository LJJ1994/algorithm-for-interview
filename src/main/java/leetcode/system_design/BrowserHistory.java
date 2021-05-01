package leetcode.system_design;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// 1472. 设计浏览器历史记录
public class BrowserHistory {
    private String curPage;
    private Deque<String> backStack;
    private Deque<String> forwardStack;
    public BrowserHistory(String homepage) {
        this.curPage = homepage;
        backStack = new LinkedList<>();
        forwardStack = new LinkedList<>();
    }

    // 跳转
    public void visit(String url) {
        if ("".equals(url)) {
            return;
        }
        backStack.push(curPage);
        curPage = url;
        forwardStack.clear();
    }

    public String back(int steps) {
        int size = backStack.size();
        if (!backStack.isEmpty()) {
            if (steps > size) {
                while (!backStack.isEmpty()) {
                    forwardStack.push(curPage);
                    curPage = backStack.pop();
                }
                return curPage;
            } else {
                for (int i = 0; i < steps; i++) {
                    forwardStack.push(curPage);
                    curPage = backStack.pop();
                }
                return curPage;
            }
        }
        return curPage;
    }

    /**
     *                                                      back             forward                 cur
     * current:         1, 2, 3, 4, [5], 6, 7, 8, 9, 10     1, 2, 3, 4      n6, 7, 8, 9, 10          5
     * forward 1 step:  1, 2, 3, 4, 5, [6], 7, 8, 9, 10     1, 2, 3, 4, 5   7, 8, 9, 10             6
     * forward 3 step:  1, 2, 3, 4, 5, 6, 7, 8, [9], 10     1,2,3,..., 7,8    10                      9
     * forward 2 step: 1, 2, 3, 4, 5, 6, 7, 8, 9, [10]      1,2,3,..., 8,9   null                    10
     */

    public String forward(int steps) {
        int size = forwardStack.size();
        if (!forwardStack.isEmpty()) {
            if (steps > size) {
                while (!forwardStack.isEmpty()) {
                    backStack.push(curPage);
                    curPage = forwardStack.pop();
                }
                return curPage;
            } else {
                for (int i = 0; i < steps; i++) {
                    backStack.push(curPage);
                    curPage = forwardStack.pop();
                }
                return curPage;
            }
        }
        return curPage;
    }

    public static void main(String[] args) {
        // [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.forward(1));
        browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        System.out.println(browserHistory.forward(2));
        System.out.println(browserHistory.back(2));
        System.out.println(browserHistory.back(7));

        System.out.println("test main update 01");
    }
}
