package T0019_remove_nth_node_from_end_of_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        // 先遍历一遍链表，确定链表长度，确定要删除的正数第几个节点
        // 使用新的指针把不包含待删除节点的所有节点重新串起来
        // 需要遍历链表2次
        // 时间复杂度O(n), 空间复杂度O(1)
        public ListNode removeNthFromEnd1(ListNode head, int n) {
            if(head == null || n < 1) return head;

            // 遍历一遍节点，确定节点个数
            int len = 0;
            ListNode p = head;
            while(p != null) {
                len++;
                p = p.next;
            }

            // 正数第几个节点
            int k = len - n + 1;
            if(k < 1) return head;

            int index = 0;
            // dummy节点表示头结点的前一个节点
            ListNode dummy = new ListNode(0);
            p = dummy;

            // 使用新的指针把原来的节点串起来
            while(head != null) {
                // 正数第k个节点不拼接
                if(index != k-1) {
                    p.next = head;
                    p = p.next;
                }
                head = head.next;
                index++;
            }
            // 当删除倒数最后一个节点时需要把新链表的最后一个节点下一个元素指向null
            p.next = null;
            return dummy.next;
        }

        // 方法2
        // 先使用指针p1,遍历一遍链表，找到整数第n个位置
        // 在使用另外一个指针p2从head开始同步遍历，p1最后一个元素时，p2刚好指向要删除节点的头一个节点
        // 为了兼容删除头指针的情况，给原始链表添加一个dummy节点
        // 只便利了1次链表
        // 时间复杂度O(n), 空间复杂度O(1)
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null || n < 1) return head;

            // 使用dummy节点可以兼容删除链表头节点的情况
            ListNode dummy = new ListNode(0);
            ListNode p1 = dummy, p2 = dummy;
            dummy.next = head;

            // 找到head链表的正数第n-1个节点, 从0开始计数
            // 由于新增了头结点的前导节点，因此遍历了n次，其实是找到了head链表的第n-1个节点
            for(int i=0; i<n; i++) {
                p1 = p1.next;
                // 如果n超过了链表长度直接返回头结点
                if(p1 == null) return dummy.next;
            }

            // p1和p2同步遍历
            // p1指向数组结尾时，p2正好指向head链表的倒数第n-1个节点
            while(p1.next != null) {
                p1 = p1.next;
                p2 = p2.next;
            }
            // 删除倒数第n个节点
            p2.next = p2.next.next;

            return dummy.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution app = new Solution();
        app.removeNthFromEnd(head, 5);



    }


}
