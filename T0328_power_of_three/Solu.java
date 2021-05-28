package T0328_power_of_three;

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
        // 使用快指针，每次移动2步
        // 快指针初始位置在第2个节点，快指针每次来到的位置是偶数索引，快指针的下一个位置是奇数索引
        // 使用2个链表分别把奇数索引和偶数索引的点串起来
        // 最后把2个链表连接起来
        // 时间复杂度O(N), 空间复杂度O(1)
        public ListNode oddEvenList(ListNode head) {
            // 节点个数 < 3的直接返回
            if(head == null || head.next == null || head.next.next == null) return head;

            // 初始化奇数链表的头结点，偶数链表的头结点
            ListNode oddHead = head, evenHead = head.next, oddP = oddHead, evenP = evenHead;
            // node初始化为第2个节点
            ListNode node = evenHead;

            // 当前位置node是偶数节点，在上一轮循环中已经处理过
            // 本次循环，处理node的下一个奇数节点和偶数节点
            // 处理完后，node跳转到下一个偶数节点
            while(node != null && node.next != null) { // 每次跳2步
                oddP.next = node.next;
                oddP = oddP.next;
                evenP.next = oddP.next;
                evenP = evenP.next;
                node = evenP;
            }

            // 奇偶链表串联起来
            oddP.next = evenHead;

            return oddHead;
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

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);


        app.printList(head);
        ListNode newHead = app.oddEvenList(head);
        app.printList(newHead);
    }
}
