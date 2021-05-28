package T0236_lowest_common_ancestor_of_a_binary_tree;

import java.util.*;

class Solu {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static class Solution {

        // 方法1
        // 后续遍历二叉树
        // 如果当前节点是null，或者当前节点是p或者q，直接返回
        // 否则，取左右子树的信息left和right
        // left和right同时为空，返回null
        // left和right有一个为空，返回不为空的那个，不为空的可能是p或q的一个，也可能就是最近公共祖先
        // left和right都不为空，说明p和q分别在当前节点的左右子树上，返回当前节点
        // 时间复杂度O(N), 空间复杂度O(1)
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // root节点为空，或者root节点是p，q的一个，直接返回root节点
            if(root == null || root == p || root == q) return root;

            // 获取左右节点的信息
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            // left和right都不为空，说明p和q分别在当前节点的左右子树上，返回当前节点
            if(left != null && right != null) return root;

            // left和right有一个为空，返回不为空的那个，不为空的可能是p或q的一个，也可能就是最近公共祖先
            // 或者left和right同时为空，返回null
            return left != null ? left : right;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

    }
}
