package T0148_sort_list;

class Solu {

    static class Solution {

        static public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }


        // 思路1
        // 快排
        // 使用partition过程，利用链表第一个元素作为pivot，划分为<=pivot的在左边，>pivot的在右边
        // 时间复杂度O(N*log(N)), 空间复杂度O(N)
        public ListNode sortList1(ListNode head) {
            if (head == null) return null;

            quickSort(head, null);
            return head;
        }

        // 快排
        // 把[start, end)的链表排序
        public void quickSort(ListNode start, ListNode end) {
            if(start == end || start.next == end) return;

            // 先进行partition过程，以head的值作为pivot，把<=pivot的放左边，大于pivot的放右边
            ListNode mid = partition(start, end); // mid位置的值时pivot
            quickSort(start, mid);
            quickSort(mid.next, end);

        }

        // 以head的值作为pivot，把<=pivot的放左边，大于pivot的放右边
        public ListNode partition(ListNode start, ListNode end){
            int pivot = start.val;
            ListNode p = start.next, left = start;
            // 从head的下一个位置开始遍历
            // left位置存放的是<=pivot的最后一个元素
            while(p != end) {
                if(p.val <= pivot) {
                    left = left.next;
                    int tmp = left.val;
                    left.val = p.val;
                    p.val = tmp;
                }
                p = p.next;
            }
            // 把最后一个<=pivot的位置和头结点start位置进行交换
            start.val = left.val;
            left.val = pivot;
            return left;
        }



        // 思路2
        // 归并排序
        // 先找到中位点mid，把mid.next开始的右半部分排序，返回排序号后的新头节点
        // 再把mid.next = null, 把mid结束的左半部分排序，返回排序号后的新头节点
        // 时间复杂度O(N*log(N)), 空间复杂度O(log(N))
        public ListNode sortList(ListNode head) {
            if (head == null) return null;

            ListNode newHead =  mergeSort(head);
            return newHead;
        }

        // 归并排序
        // 先找到中位点mid，把mid.next开始的右半部分排序，返回排序号后的新头节点
        // 再把mid.next = null, 把mid结束的左半部分排序，返回排序号后的新头节点
        // 最后合并左右两部分
        public ListNode mergeSort(ListNode start) {
            if(start == null || start.next == null) return start;

            // 获取上中位节点
            ListNode mid = getMid(start);
            // 排序好mid.next开始的右半部分
            ListNode right = mergeSort(mid.next);

            // 从mid位置截断
            mid.next = null;
            // 排序好mid结束的左半部分
            ListNode left = mergeSort(start);

            // 合并排序后的左右两部分
            return merge(left, right);
        }

        // 返回上中位点
        public ListNode getMid(ListNode start) {

            ListNode slow = start, fast = start;
            while(fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        // 合并两个有序链表
        public ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy= new ListNode(-1), p = dummy;

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

            if(l1 == null) p.next = l2;
            if(l2 == null) p.next = l1;

            return dummy.next;
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

        Solution.ListNode head = new Solution.ListNode(4);
        head.next = new Solution.ListNode(2);
        head.next.next = new Solution.ListNode(1);
        head.next.next.next = new Solution.ListNode(3);

        app.printList(head);
        Solution.ListNode newHead = app.sortList(head);
        app.printList(newHead);

    }
}
