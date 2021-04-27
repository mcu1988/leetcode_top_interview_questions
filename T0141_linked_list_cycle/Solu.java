package T0141_linked_list_cycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solu {

    static class Solution {

        static class ListNode {
            int val;
            ListNode next;
            ListNode(int x) {
                val = x;
                next = null;
            }
        }

        // 方法
        // 使用快慢两个指针，快指针一次走两步，慢指针一次走一步
        // 向前遍历过程中，如果遇到空，说明无环
        // 如果快慢指针相等，证明有环
        // 时间复杂度O(N), 空间复杂度O(1)
        public boolean hasCycle(ListNode head) {

            if(head == null) return false;

            ListNode slow = head, fast = head;
            while(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast) return true;
            }
            return false;
        }

    }


    public static void main(String[] args) {
        Solution app = new Solution();

        Solution.ListNode head = new Solution.ListNode(1);
        head.next = new Solution.ListNode(2);
        head.next.next = head;

        app.hasCycle(head);

        System.out.println(app.hasCycle(head));

    }
}
