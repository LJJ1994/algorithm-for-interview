package Sword_finger_offer;

// 链表反转
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode reversedHead = null;
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            if (next == null) {
                reversedHead = cur;
            }
            cur.next = prev;

            prev = cur;
            cur = next;
        }
        return reversedHead;
    }

    private static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
