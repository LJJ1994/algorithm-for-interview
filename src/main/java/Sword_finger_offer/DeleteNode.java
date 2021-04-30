package Sword_finger_offer;

public class DeleteNode {
    public void deleteNode(ListNode head, ListNode toBeDelete) {
        if (head == null || toBeDelete == null) {
            return;
        }
        if (toBeDelete.next != null) {
            ListNode next = toBeDelete.next;
            toBeDelete.val = next.val;
            toBeDelete.next = next.next;
        } else if (head == toBeDelete) {
            head = null;
            toBeDelete = null;
            return;
        } else {
            ListNode pNode = head;
            while (pNode.next != toBeDelete) {
                pNode = pNode.next;
            }
            pNode.next = null;
            toBeDelete = null;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
