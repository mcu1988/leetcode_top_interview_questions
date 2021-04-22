package T0106_construct_binary_tree_from_inorder_and_postorder_traversal;

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
        // 后序遍历数组的构成 left tree, right tree, head 设计递归函数，递归处理left tree， right tree
        // 中序遍历数组构成 left tree, head, right tree
        // 后先序数组中确定head，然后再中序数组中找到head的位置，就可以计算出左树的长度和右树的长度
        // 确定左树和右树的在先序遍历的数组索引范围后，继续在后序数组中遍历左树和右树
        // 时间复杂度O(N), 空间复杂度O(N)
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0 || inorder.length != postorder.length)
                return null;

            // 建立中序遍历数组的值和索引的map
            // 数组中不包含重复的值
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i=0; i<inorder.length; i++) {
                map.put(inorder[i], i);
            }

            return process(postorder, 0, postorder.length-1, map, 0);

        }

        // 确定后序遍历数组左子树和右子树的索引，递归遍历
        // l1 后序遍历数组中当前子树的开始位置
        // r1 后序遍历数组中当前子树的结束位置
        // l2 中序遍历数组子树的开始位置
        public TreeNode process(int[] postorder, int l1, int r1, HashMap<Integer, Integer> inorderMap, int l2) {

            // l1 > r1时，返回空
            if(l1 > r1) return null;

            // 后序数组的第一个元素就是头结点
            TreeNode head = new TreeNode(postorder[r1]);
            // 在中序数组中找到头结点的位置，并计算左子树的长度
            int pos = inorderMap.get(postorder[r1]);
            int leftLen = pos - l2;

            // 确定左子树和右子树的索引范围，递归遍历左右子树
            head.left = process(postorder, l1, l1+leftLen-1, inorderMap, l2);
            head.right = process(postorder, l1+leftLen, r1-1, inorderMap, pos+1);

            return head;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] postorder = {9,15,7,20,3};
        int[] inorder = {9,3,15,20,7};

        app.buildTree(inorder, postorder);
    }
}
