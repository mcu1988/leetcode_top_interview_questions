package T0101_binary_tree_level_order_traversal;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

class Solu {

    static class Solution {
        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode() {}
            TreeNode(int val) { this.val = val; }
            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        // 方法1
        // 设计递归函数，每次比较左右2个节点是否对称，并递归向下比较
        // 时间复杂度O(N), 空间复杂度O(h), h是树的高度
        public boolean isSymmetric1(TreeNode root) {
            if(root == null) return true;

            return process(root.left, root.right);
        }

        // 递归函数，传入左右两个节点，查看当前节点是否对称，并继续递归向下比较
        public boolean process(TreeNode left, TreeNode right) {
            // 当前2个节点都是空节点，返回true
            if(left == null && right == null) return true;

            // 一个为空，另一个不为空时，不是对称的
            if(left == null || right == null) return false;

            // 两个节点都不是空，并且值不相等时，不是对称的
            if(left.val != right.val) return false;
            // 当前的2个节点是对称的，继续继续向下比较
            return process(left.left, right.right) && process(left.right, right.left);
        }

        // 方法2
        // 层序遍历二叉树，依次对比每一层是否对称
        // 时间复杂度O(N), 空间复杂度O(N)
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;

            // 队列中初始加入根节点的左右节点
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root.left);
            queue.add(root.right);

            TreeNode left = root, right = root;
            while(!queue.isEmpty()) {
                // 每次弹出待比较的2个节点
                left = queue.poll();
                right = queue.poll();
                if(left == null && right == null) continue;
                if(left == null || right == null) return false;
                if(left.val != right.val) return false;

                // 下一层的节点加入队列
                queue.add(left.left);
                queue.add(right.right);
                queue.add(left.right);
                queue.add(right.left);
            }
            return true;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.TreeNode root = new Solution.TreeNode(1);
        root.left = new Solution.TreeNode(2);
        root.right = new Solution.TreeNode(2);
        root.left.left = new Solution.TreeNode(3);
        root.left.right = new Solution.TreeNode(4);
        root.right.left = new Solution.TreeNode(4);
        root.right.right = new Solution.TreeNode(3);

        System.out.println(app.isSymmetric(root));


    }
}
