package T0002_add_2list_numbers;

class Solu {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {

        // 先遍历链表转成整数，再相加，最后再转成链表，存在整数溢出的问题

        // 先遍历链表逐个相加，使用进位标识，最后注意判断进位标识是不是0
        // 时间复杂度O(m+n), 空间复杂度O(1)
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if(l1 == null) return l2;
            if(l2 == null) return l1;

            int jinwei = 0;
            ListNode dummy = new ListNode(), p = dummy;
            int tmp = 0;
            while(l1 != null && l2 != null) {
                tmp = l1.val + l2.val + jinwei;
                l1 = l1.next;
                l2 = l2.next;
                p.next = new ListNode(tmp % 10);
                p = p.next;
                jinwei = tmp / 10;
            }

            // 单处处理第2个链表
            if(l1 == null) {
                while(l2 != null) {
                    tmp = l2.val + jinwei;
                    l2 = l2.next;
                    p.next  = new ListNode(tmp % 10);
                    p = p.next;
                    jinwei = tmp / 10;
                }
            }

            // 单独处理第1个链表
            if(l2 == null) {
                while(l1 != null) {
                    tmp = l1.val + jinwei;
                    l1 = l1.next;
                    p.next  = new ListNode(tmp % 10);
                    p = p.next;
                    jinwei = tmp / 10;
                }
            }

            // 最后的进位也要判断
            if(jinwei != 0) {
                p.next  = new ListNode(jinwei);
            }

            return dummy.next;
        }
    }
}
