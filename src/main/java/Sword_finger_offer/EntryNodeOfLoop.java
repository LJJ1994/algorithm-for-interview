package Sword_finger_offer;

//链表中环的入口点
public class EntryNodeOfLoop {
    public ListNode findEntryNodeOfLoop(ListNode head) {
        ListNode meetingNode = meetingNode(head);
        if (meetingNode == null) return null;

        // 得到环中的节点数目
        ListNode pNode1 = meetingNode;
        int nodesInLoop = 1;
        while (pNode1.next != meetingNode) {
            pNode1 = pNode1.next;
            nodesInLoop++;
        }
        // 先移动pNode1
        pNode1 = head;
        for (int i = 0; i < nodesInLoop; i++) {
            pNode1 = pNode1.next;
        }
        // 再移动pNode1 pNode2
        ListNode pNode2 = head;
        while (pNode1 != pNode2) {
            pNode1 = pNode1.next;
            pNode2 = pNode2.next;
        }
        return pNode1;
    }

    // 在环存在的前提下找到快慢指针相遇的节点
    // 如果链表中不存在环，则返回Null
    private ListNode meetingNode(ListNode head) {
        if (head == null) return null;
        ListNode slow = head.next;
        if (slow == null) return null;

        ListNode fast = slow.next;
        while (fast != null && slow != null) {
            if (fast == slow)  return fast;

            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return null;
    }
    private static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
