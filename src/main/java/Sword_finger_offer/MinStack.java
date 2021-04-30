package Sword_finger_offer;

import java.util.*;

public class MinStack {
    private Stack<Integer> A, B;
    public MinStack() {
        A = new Stack<>();
        B = new Stack<>();

    }

    public void push(int x) {
        A.add(x);
        if (B.isEmpty() || B.peek() >= x) {
            B.add(x);
        }
    }

    public void pop() {
        if (A.pop().equals(B.peek())) {
            B.pop();
        }
    }

    public int top() {
       return A.peek();
    }

    public int min() {
        return B.peek();
    }
}
