package T0206_reverse_linke_list;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solu {

    static class Solution {

        static public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        // 方法1
        // 使用3个变量，分别记录上一个、当前、下一个的node
        // 逐个往右遍历，改变指针方向
        // 时间复杂度O(N), 空间复杂度O(1)
        public ListNode reverseList(ListNode head) {
            if(head == null) return null;

            ListNode pre = null, cur = head, next = null;
            while(cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
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

        Solution.ListNode head = new Solution.ListNode(1);
        head.next = new Solution.ListNode(2);
        head.next.next = new Solution.ListNode(3);

        app.printList(head);

        head = app.reverseList(head);

        app.printList(head);

    }
}
