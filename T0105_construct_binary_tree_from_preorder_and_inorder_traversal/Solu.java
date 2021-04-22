package T0105_construct_binary_tree_from_preorder_and_inorder_traversal;

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
        // 先序遍历数组的构成  head, left tree, right tree, 设计递归函数，递归处理left tree， right tree
        // 中序遍历数组构成 left tree, head, right tree
        // 在先序数组中确定head，然后再中序数组中找到head的位置，就可以计算出左树的长度和右树的长度
        // 确定左树和右树的在先序遍历的数组索引范围后，继续在先序数组中遍历左树和右树
        // 时间复杂度O(N), 空间复杂度O(N)
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || preorder.length != inorder.length)
                return null;

            // 建立中序遍历数组的值和索引的map
            // 数组中不包含重复的值
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i=0; i<inorder.length; i++) {
                map.put(inorder[i], i);
            }

            return process(preorder, 0, preorder.length-1, map, 0);

        }

        // 确定先序遍历数组左子树和右子树的索引，递归遍历
        // l1 前序遍历数组中当前子树的开始位置
        // r1 前序遍历数组中当前子树的结束位置
        // l2 中序遍历数组子树的开始位置
        public TreeNode process(int[] preorder, int l1, int r1, HashMap<Integer, Integer> inorderMap, int l2) {

            // l1 > r1时，返回空
            if(l1 > r1) return null;

            // 先序数组的第一个元素就是头结点
            TreeNode head = new TreeNode(preorder[l1]);
            // 在中序数组中找到头结点的位置，并计算左子树的长度
            int pos = inorderMap.get(preorder[l1]);
            int leftLen = pos - l2;

            // 确定左子树和右子树的索引范围，递归简历左右子树
            head.left = process(preorder, l1+1, l1+leftLen, inorderMap, l2);
            head.right = process(preorder, l1+leftLen+1, r1, inorderMap, pos+1);

            return head;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        System.out.println(app.buildTree(preorder, inorder));
    }
}
