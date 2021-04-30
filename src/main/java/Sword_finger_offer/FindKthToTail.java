package Sword_finger_offer;

import java.util.List;

// 链表中倒数第K个节点
// 使用双指针法
public class FindKthToTail {
    public ListNode findKthNode(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode pHead = head;
        ListNode behind = null;
        for (int i = 0; i < k - 1; i++) {
            if (pHead.next != null) {
                pHead = pHead.next;
            } else {
                return null;
            }
        }
        behind = head;
        while (pHead.next != null) {
            pHead = pHead.next;
            behind = behind.next;
        }
        return behind;
    }

    private static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
