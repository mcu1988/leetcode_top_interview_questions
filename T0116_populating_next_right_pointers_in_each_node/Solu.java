package T0116_populating_next_right_pointers_in_each_node;

import java.util.*;

class Solu {

    static class Solution {

        class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        }

        // 方法1
        // 层序遍历二叉树，加入队列时，先加入右节点，再加入左节点
        // 时间复杂度O(N), 空间复杂度O(N)
        public Node connect1(Node root) {
            if(root == null) return null;

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            Node cur = root, preNode = root;
            int curLevelSize = 0;
            while(!queue.isEmpty()) {
                curLevelSize = queue.size();
                preNode = null;
                for(int i=0; i<curLevelSize; i++) {
                    cur = queue.poll();
                    cur.next = preNode;
                    preNode = cur;

                    if(cur.right != null) queue.add(cur.right);
                    if(cur.left != null) queue.add(cur.left);
                }
            }
            return root;
        }


        // 方法2
        // 深度递归遍历二叉树
        // 当前节点的左孩子next指向右孩子，当前节点的右孩子的指针指向当前节点next的左孩子
        // 时间复杂度O(N), 空间复杂度O(log(N))
        public Node connect2(Node root) {
            if (root == null) return null;

            return process(root);
        }

        // 递归遍历
        // 在当前节点的层处理下一层的next指针
        // 如果当前节点的下一层出现了空节点，返回当前节点
        public Node process(Node head) {
            if(head.left == null || head.right == null) return head;

            // 左孩子指向右孩子
            head.left.next = head.right;

            // 右孩子指向父节点next的左孩子
            if(head.next != null) {
                head.right.next = head.next.left;
            }

            process(head.left);
            process(head.right);

            return head;
        }


        // 方法3
        // 由于node带有next指针，可以不使用队列，层序遍历二叉树
        // 在当前层处理此下一层的next指针
        // 时间复杂度O(N), 空间复杂度O(1)
        public Node connect(Node root) {
            if (root == null) return null;

            Node cur = root, levelFirstNode = root.left;
            while(cur.left != null && cur.right != null) {
                cur.left.next = cur.right; // 左孩子next指向右孩子
                if(cur.next != null) {
                    // 右孩子的next指向父节点next的左孩子
                    cur.right.next = cur.next.left;
                    // 当前层往右平移
                    cur = cur.next;
                }
                else {
                    // 移到最右端时，跳到下一层最左边的节点
                    cur = levelFirstNode;
                    // 更新下一层最左边的节点
                    levelFirstNode = cur.left;
                }
            }
            return root;
        }

    }

    public static void main(String[] args) {

    }
}
