package T0234_palindrome_linked_list;

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
        // 使用快慢指针，找到上中位点
        // 把右半部分压栈
        // 对比左边和右边是否一致
        // 时间复杂度O(N), 空间复杂度O(N)
        public boolean isPalindrome1(ListNode head) {
            if(head == null || head.next == null) return true;

            // 快慢指针找到上中位点
            ListNode slow = head, fast = head;
            while(fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            Stack<ListNode> stack = new Stack<>();
            slow = slow.next;
            while(slow != null) {
                stack.push(slow);
                slow = slow.next;
            }

            slow = head;
            while(!stack.isEmpty()) {
                if(slow.val != stack.pop().val) return false;
                slow = slow.next;
            }
            return true;
        }

        // 方法2
        // 使用快慢指针，找到上中位点
        // 逆序右半部分
        // 对比左边和右边是否一致
        // 恢复右半部分
        // 时间复杂度O(N), 空间复杂度O(N)
        public boolean isPalindrome(ListNode head) {
            if(head == null || head.next == null) return true;

            // 快慢指针找到上中位点
            ListNode slow = head, fast = head;
            while(fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode rightStart = slow.next;
//            slow.next = null;
            ListNode pre = null, cur= rightStart, next = null;
            while(cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            ListNode rightEnd = pre;

            boolean result = true;

            while(pre != null) {
                if(head.val != pre.val) {
                    result = false;
                    break;
                }
                head = head.next;
                pre = pre.next;
            }

            // 右半部分逆序回来
            pre = null;
            cur= rightEnd;
            next = null;
            while(cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            return result;
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
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        app.printList(head);
        app.isPalindrome(head);
        app.printList(head);
    }
}
