package Sword_finger_offer;

import java.util.Arrays;
import java.util.Stack;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 *
 */
public class PrintListNode {
    public int[] reversePrint(ListNode head) {
        ListNode p = head;
        Stack<ListNode> stack = new Stack<>();

        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        int size = stack.size();
        int[] vals = new int[size];
        for (int i = 0; i < size; i++) {
            vals[i] = stack.pop().val;
        }
        return vals;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(3);
        ListNode third = new ListNode(2);

        head.next = second;
        second.next = third;

        PrintListNode printListNode = new PrintListNode();
        int[] ints = printListNode.reversePrint(head);
        System.out.println(Arrays.toString(ints));
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }
}
