package Sword_finger_offer;

// 复杂链表的复制
public class CopyRandomList {
    public Node copyRandomList(Node head) {
        cloneNodes(head);
        connectSiblingsNodes(head);
        return reconnectNodes(head);
    }

    // 第一步
    private void cloneNodes(Node head) {
        Node pNode = head;
        while (pNode != null) {
            Node cloned = new Node(pNode.val);
            cloned.next = pNode.next;
            cloned.random = null;

            pNode.next = cloned;
            pNode = cloned.next;
        }
    }

    // 第二步
    private void connectSiblingsNodes(Node head) {
        Node pNode = head;
        while (pNode != null) {
            Node cloned = pNode.next;
            if (pNode.random != null) {
                cloned.random = pNode.random.next;
            }
            pNode = cloned.next;
        }
    }

    // 第三步
    private Node reconnectNodes(Node head) {
        Node pNode = head;
        Node clonedHead = null;
        Node clonedNode = null;
        if (pNode != null) {
            clonedHead = clonedNode = pNode.next;
            pNode.next = clonedNode.next;
            pNode = pNode.next;
        }
        while (pNode != null) {
            clonedNode.next = pNode.next;
            clonedNode = clonedNode.next;
            pNode.next = clonedNode.next;
            pNode = pNode.next;
        }
        return clonedHead;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
