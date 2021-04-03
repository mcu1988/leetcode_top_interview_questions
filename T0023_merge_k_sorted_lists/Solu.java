package T0023_merge_k_sorted_lists;

import java.util.PriorityQueue;
import java.util.Queue;

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
        // 时间复杂度，O(k * n)，空间复杂度O(1)，n是合并后总结点个数
        public ListNode mergeKLists1(ListNode[] lists) {
            if(lists == null || lists.length == 0) return null;

            ListNode dummy = new ListNode(0);

            for (ListNode list : lists) {
                dummy.next = mergeTwoLists(dummy.next, list);
            }
            return dummy.next;
        }

        // 合并2个有序链表
        // 时间复杂度O(m), 空间复杂度O(1)，m是2个链表合并后的总结点数
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


        // 方法2
        // 使用归并排序
        // 时间复杂度，O(n * log(k))，空间复杂度O(log(k))
        public ListNode mergeKLists2(ListNode[] lists) {
            if(lists == null || lists.length == 0) return null;

            return process(lists, 0, lists.length-1);
        }

        // 归并排序，二分递归函数
        public ListNode process(ListNode[] lists, int l, int r) {
            if(l == r) return lists[l];

            int mid = (l + r) / 2;
            // 二分分别合并左边和右边的链表
            ListNode leftHead = process(lists, l, mid);
            ListNode rightHead = process(lists, mid+1, r);

            // 左边和右边已经合并后，最后将左右两个合并为一个
            return mergeTwoLists(leftHead, rightHead);
        }

        // 方法3
        // 使用最小堆，每次把k个链表的1个元素加入最小堆，弹出堆顶元素，将堆顶元素所在链表的下一个节点加入最小堆
        // 时间复杂度，O(n * log(k))，空间复杂度O(k)
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null || lists.length == 0) return null;

            // 新建一个大小为k的最小堆
            Queue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

            // 先把每个链表的头结点加入最小堆
            for(ListNode node : lists) {
                if(node != null) minHeap.add(node);
            }

            // 使用dummy节点作为合并后链表头结点的前导节点
            ListNode dummy = new ListNode(0);
            ListNode p = dummy;

            while(!minHeap.isEmpty()) {
                // 每次弹出一个堆顶元素，就是当前k个节点的最小值
                ListNode tmp = minHeap.poll();
                p.next = tmp;
                p = p.next;

                // 如果堆顶弹出节点所在的链表下一个元素不为空，重新加入链表
                if(tmp.next != null) minHeap.add(tmp.next);
            }

            return dummy.next;
        }

    }

    public static void main(String[] args) {
        Solution app = new Solution();

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode[] lists = {head1, head2, head3};

        app.mergeKLists(lists);

    }
}
