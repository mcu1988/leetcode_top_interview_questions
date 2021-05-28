package T0237_delete_node_in_a_linked_list;

import java.util.*;

class Solu {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {

        // 方法1
        // 把下一个节点的值赋值到要删除的节点上，要删除的节点next改为next.next
        // 时间复杂度O(N), 空间复杂度O(1)
        public void deleteNode(ListNode node) {
            if(node == null || node.next == null) return;

            node.val = node.next.val;
            node.next = node.next.next;
        }

        public void printList(ListNode head) {
            while(head != null) {
                System.out.print(head.val + " ");
                head = head.next;
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);

        app.printList(head);
        app.deleteNode(head.next);
        app.printList(head);
    }
}
