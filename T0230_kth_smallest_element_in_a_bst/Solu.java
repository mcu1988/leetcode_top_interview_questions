package T0230_kth_smallest_element_in_a_bst;


import java.util.*;

class Solu {

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

    static class Solution {

        // 方法1
        // 中序遍历，到第k个时，返回
        // 时间复杂度O(k), 空间复杂度O(1)
        public int kthSmallest(TreeNode root, int k) {
            if(root == null) return -1;

            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = root;
            int index = 0;
            while(p!= null || !stack.isEmpty()) {
                while(p != null) {
                    stack.push(p);
                    p = p.left;
                }
                p = stack.pop();
                index++;
                if(index == k) return p.val;
                p = p.right;
            }
            return -1;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        int k = 1;

        app.kthSmallest(root, k);
    }
}
