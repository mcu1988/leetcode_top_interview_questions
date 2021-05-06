package T0160_intersection_of_two_linked_lists;

import java.util.Stack;

class Solu {

    static class Solution {

        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) {
                val = x;
                next = null;
            }
        }

        // 方法
        // 使用2个指针p1, p2
        // p1走到底时，从headB开始继续遍历
        // p2走到底时，从headA开始继续遍历
        // 循坏要求p1或者p2不能同时为null
        // 循环中如果p1==p2，返回交点
        // 时间复杂度O(N), 空间复杂度O(1)
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if(headA == null || headB == null) return null;

            ListNode p1 = headA, p2 = headB;
            while(p1 != null || p2 != null) {
                if(p1 == p2) return p1;

                p1= p1.next == null ? headB : p1.next;
                p2 = p2.next == null ? headA : p2.next;
            }
            return null;
        }

    }


    public static void main(String[] args) {
        Solution app = new Solution();
    }
}
