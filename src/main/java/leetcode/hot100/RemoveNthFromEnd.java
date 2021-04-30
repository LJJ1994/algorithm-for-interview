
package leetcode.hot100;

// 19. 删除链表的倒数第N个节点

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len += 1;
            cur = cur.next;
        }
        // 如果删除的是头结点
        if (len == n) {
            return head.next;
        }
        ListNode del = head;
        // 找到删除节点的前驱节点
        for (int i = 0; i < len - n -1; i++) {
            del = del.next;
        }
        // 删除节点
        if (del.next != null) {
            del.next = del.next.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
