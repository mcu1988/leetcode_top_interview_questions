package T0104_maximum_depth_of_binary_tree;

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

        // 方法
        // 递归遍历二叉树，把每个节点当头结点，得到左右子树的高度，取最大值+2，就是当前节点的高度
        // 时间复杂度O(N), 空间复杂度O(h), h是二叉树的高度
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;

            return process(root);
        }

        public int process(TreeNode head) {
            // 空节点高度为0
            if(head == null) return 0;

            // 获取左右节点的高度
            int left = process(head.left);
            int right = process(head.right);

            // 左右子树高度最大值加1，就是当前节点的高度
            return Math.max(left, right) + 1;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.TreeNode root = new Solution.TreeNode(3);
        root.left = new Solution.TreeNode(9);
        root.right = new Solution.TreeNode(20);
        root.right.left = new Solution.TreeNode(15);
        root.right.right = new Solution.TreeNode(7);

        System.out.println(app.maxDepth(root));
    }
}
