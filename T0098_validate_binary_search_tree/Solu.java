package T0098_validate_binary_search_tree;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        // 看递归中序遍历结果是否是递增的
        // 时间复杂度O(N), 空间复杂度O(N)
        public boolean isValidBST1(TreeNode root) {
            if(root == null) return false;
            List<Integer> result = new ArrayList<>();
            process(root, result);

            for(int i=1; i<result.size(); i++) {
                if(result.get(i) <= result.get(i-1)) return false;
            }
            return true;
        }

        // 递归中序递归
        public void process(TreeNode root, List<Integer> result) {
            if(root == null) return;

            process(root.left, result);
            result.add(root.val);
            process(root.right, result);
        }

        // 方法2
        // 递归遍历每个节点，把每个节点当父节点，查看是否BST
        // 每个节点需要返回3个信息：是否BST, 最小值，最大值
        // 时间复杂度O(N), 空间复杂度O(h), h是树的高度
        public boolean isValidBST2(TreeNode root) {
            if (root == null) return false;

            return process1(root).isBST;
        }

        // 递归判断当前节点是不是平衡二叉树
        public Info process1(TreeNode head) {
            // 当前节点为空，返回null
            if(head == null) return null;

            // 或者左右子树的返回信息
            Info left = process1(head.left);
            Info right = process1(head.right);

            boolean isBST = false;
            // 当前节点左右子树都是空，返回当前节点的信息
            if(left == null && right == null) return new Info(true, head.val, head.val);
            // 当前节点左子树为空，考虑当前节点和右子树是否是平衡二叉树
            if(left == null) return new Info(right.isBST && right.min > head.val, head.val, right.max);
            // 当前节点右子树为空，考虑当前节点和左子树是否是平衡二叉树
            if(right == null) return new Info(left.isBST && left.max < head.val, left.min, head.val);

            // 当前节点左右子树都不是空，考虑当前节点和左右子树合在一起是不是平衡二叉树
            if(left.isBST && right.isBST && left.max < head.val && right.min > head.val)
                isBST = true;
            return new Info(isBST, left.min, right.max);
        }

        public class Info {
            boolean isBST;
            int min;
            int max;
            Info(boolean isBST, int min, int max) {
                this.isBST = isBST;
                this.min = min;
                this.max = max;
            }
        }

        // 方法3
        // 从上往下遍历，每个节点在遍历过程中的边界范围会受限制，查看当前节点是否在[min, max]范围内，并迪许递归左右子树
        // 时间复杂度O(N), 空间复杂度O(h), h是树的高度
        public boolean isValidBST(TreeNode root) {
            if (root == null) return false;

            // 初始时，父节点没有范围限制
            return process3(root, null, null);
        }

        // 递归函数，返回当前节点开始的子树是否合规
        // 从上往下遍历时，每个节点都会有一个范围，查看当前值是否在范围内，并继续递归查看左右节点
        public boolean process3(TreeNode head, TreeNode min, TreeNode max) {
            // 当前节点为空，返回true
            if(head == null) return true;

            // 分别查看head的值是否在min和max范围内
            if(min != null && head.val <= min.val) return false;
            if(max != null && head.val >= max.val) return false;

            // 当前节点不违规，继续查看左节点和右节点
            return process3(head.left, min, head) && process3(head.right, head, max);
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.TreeNode root = new Solution.TreeNode(Integer.MAX_VALUE);
        root.left = new Solution.TreeNode(Integer.MIN_VALUE);

        System.out.println(app.isValidBST(root));


    }
}
