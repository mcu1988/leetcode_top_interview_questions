package T0021_merge_two_sorted_lists;

class Solu {

    public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {

        // 方法
        // 时间复杂度O(n), 空间复杂度O(1)
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null) return l2;
            if(l2 == null) return l1;

            // 添加链表头结点的前导节点
            ListNode dummy = new ListNode(0);
            ListNode p = dummy;

            // l1和l2都不为空时，遍历
            while(l1 != null && l2 != null) {
                if(l1.val <= l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }

            // 有一个为空时，需要把不为空的接上
            if(l1 == null) p.next = l2;
            if(l2 == null) p.next = l1;

            return dummy.next;
        }

    }
}
