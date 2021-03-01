package leetcode.lists;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// 445. 两数相加 II
public class AddTwoNumbers {
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int step = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || step != 0) {
            int sum = step;
            sum += stack1.peek() == null ? 0 : stack1.pop();
            sum += stack2.peek() == null ? 0 : stack2.pop();
            cur.next = new ListNode(sum % 10);
            step = sum / 10;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r1 = reverse(l1);
        ListNode r2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int step = 0;
        while (r1 != null || r2 != null) {
            int sum = step;
            sum += r1 == null ? 0 : r1.val;
            sum += r2 == null ? 0 : r2.val;
            cur.next = new ListNode(sum % 10);
            step = sum / 10;

            cur = cur.next;

            if (r1 != null) {
                r1 = r1.next;
            }
            if (r2 != null) {
                r2 = r2.next;
            }
        }

        if (step == 1) {
            cur.next = new ListNode(step);
            cur = cur.next;
        }
        cur = dummy.next;
        return reverse(cur);
    }

    private ListNode tail;
    private ListNode dummy;

    public AddTwoNumbers() {
        dummy = new ListNode(0);
        tail = dummy;
    }

    public ListNode getHead() {
        return dummy.next;
    }

    public void insert(int x) {
        tail.next = new ListNode(x);
        tail = tail.next;
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
    }

    private static ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers numbers = new AddTwoNumbers();
        numbers.insert(7);
        numbers.insert(2);
        numbers.insert(4);
        numbers.insert(3);
        ListNode l1 = numbers.getHead();

        AddTwoNumbers numbers1 = new AddTwoNumbers();
        numbers1.insert(5);
        numbers1.insert(6);
        numbers1.insert(4);
        ListNode l2 = numbers1.getHead();

//        ListNode r1 = reverse(l1);
//        ListNode r2 = reverse(l2);

//        printList(r1);
//        System.out.println();
//        printList(r2);

        ListNode twoNumbers = addTwoNumbers(l1, l2);
        printList(twoNumbers);
    }
}
