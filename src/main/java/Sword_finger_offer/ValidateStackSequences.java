package Sword_finger_offer;

import java.util.Stack;

// 栈的压入、弹出序列
public class ValidateStackSequences {
    private final Stack<Integer> stack = new Stack<>();
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null) return false;

        int pushIndex = 0;
        int popIndex = 0;
        while (popIndex < popped.length) {
            if (stack.isEmpty() || stack.peek() != popped[popIndex]) {
                // 如果压入栈的元素已经全部压入辅助栈，且没找到辅助栈顶的元素，则判断失败
                if (pushIndex == pushed.length) {
                    break;
                }
                stack.push(pushed[pushIndex]);
                pushIndex++;
            } else {
                stack.pop();
                popIndex++;
            }
        }
        return popIndex == popped.length;
    }
}
