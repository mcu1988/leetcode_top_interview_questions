package T0108_convert_sorted_array_to_binary_search_tree;

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
        // 找到数组的中位数，中位数作为头结点，上半部分作为左子树，下半部分组作为右子树
        // 递归遍历左右子树
        // 时间复杂度O(N), 空间复杂度O(log(N))
        public TreeNode sortedArrayToBST(int[] nums) {
            if(nums == null || nums.length == 0) return null;

            return process(nums, 0, nums.length-1);
        }

        // 递归在[l, r]范围上建立子树
        public TreeNode process(int[] nums, int l, int r) {
            if(l > r) return null;

            // 找到数组中点，建立头结点
            int mid = (l + r) / 2;
            TreeNode head = new TreeNode(nums[mid]);
            // 分别递归遍历上半部分和下半部分
            head.left = process(nums, l, mid-1);
            head.right = process(nums, mid+1, r);

            return head;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {-10,-3,0,5,9};

        app.sortedArrayToBST(nums);

    }
}
