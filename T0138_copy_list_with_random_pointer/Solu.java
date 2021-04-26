package T0138_copy_list_with_random_pointer;

import java.util.HashMap;

class Solu {

    static class Solution {

        static class Node {
            int val;
            Node next;
            Node random;

            public Node(int val) {
                this.val = val;
                this.next = null;
                this.random = null;
            }
        }

        // 方法1
        // 先复制链表，不指定随机指针
        // 使用map记录新旧节点的映射关系
        // 再同步遍历原始链表和新链表，通过map找到随机指针指向的新节点，给新链表随机指针赋值
        // 时间复杂度O(N), 空间复杂度O(N)
        public Node copyRandomList1(Node head) {
            if(head == null) return null;

            // 建立老节点和新节点的映射关系
            HashMap<Node, Node> map = new HashMap<>();

            // 复制链表，不设置随机指针
            Node p1 = head, dummy = new Node(-1), p2 = dummy;
            while(p1 != null) {
                Node newNode = new Node(p1.val);
                p2.next = newNode;
                map.put(p1, newNode);
                p1 = p1.next;
                p2 = p2.next;
            }

            p1= head;
            p2 = dummy.next;
            // 复制随机指针
            while(p1 != null) {
                if(p1.random != null) // 跳过指向空的随机指针
                    p2.random = map.get(p1.random);
                p1 = p1.next;
                p2 = p2.next;
            }

            return dummy.next;
        }

        // 方法2
        // 先复制链表，把新节点插到原节点的后面，方便通过原节点找到新的随机指针
        // 赋值随机指针，旧链表随机指针指向的下一个节点就是新的随机指针节点
        // 最后把新链表从原始链表中拆解出来
        // 时间复杂度O(N), 空间复杂度O(1)
        public Node copyRandomList(Node head) {
            if(head == null) return null;

            // 复制链表，不设置随机指针，新链表节点接在原始链表对应节点的后面
            Node p1 = head;
            while(p1 != null) {
                Node newNode = new Node(p1.val);
                Node next = p1.next;
                p1.next = newNode;
                newNode.next = next;
                p1 = next;
            }

            p1= head;
            // 复制随机指针
            while(p1 != null) {
                Node newNode = p1.next;
                if(p1.random != null) newNode.random = p1.random.next;
                p1 = newNode.next;
            }

            // 把新链表从原始链表中拆解出来
            Node dummy = new Node(-1), p2 = dummy;
            p1 = head;
            while(p1 != null) {
                p2.next = p1.next;
                p1.next = p1.next.next;
                p1 = p1.next;
                p2 = p2.next;
            }

            return dummy.next;
        }

        public void printList(Node head) {
            while(head != null) {
                String randomVal = head.random == null ? "null" : String.valueOf(head.random.val);
                System.out.print(head.val + " " + randomVal + ", ");
                head = head.next;
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Solution app = new Solution();

        Solution.Node head = new Solution.Node(1);
        head.next = new Solution.Node(2);
        head.next.next= new Solution.Node(3);

        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next;

        app.printList(head);
        app.printList(app.copyRandomList(head));

    }
}
