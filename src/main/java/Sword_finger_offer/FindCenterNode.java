package Sword_finger_offer;

// 求链表的中间节点
public class FindCenterNode {
    public ListNode findCenter(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = head;
        ListNode second = head;
        while (first.next != null) {
            first = first.next.next;
            second = second.next;
        }
        return second;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        FindCenterNode findCenterNode = new FindCenterNode();
        ListNode center = findCenterNode.findCenter(head);
        System.out.println(center.val);
    }

    private static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
