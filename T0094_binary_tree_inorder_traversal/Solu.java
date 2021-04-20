package T0094_binary_tree_inorder_traversal;

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
        // 使用递归实现
        // 时间复杂度O(N), 空间复杂度O(h)，h是树的高度
        public List<Integer> inorderTraversal1(TreeNode root) {
            if(root == null) return new ArrayList<>();

            List<Integer> result = new ArrayList<>();
            process(root, result);
            return result;
        }

        // 中序递归
        public void process(TreeNode root, List<Integer> result) {
            if(root == null) return;

            process(root.left, result);
            result.add(root.val);
            process(root.right, result);
        }

        // 方法2
        // 使用非递归实现
        // 将树按照左边界划分，当前节点一直往左边界遍历，遍历结束后，再把当前节点指向左边界的右节点
        // 时间复杂度O(N), 空间复杂度O(1)
        public List<Integer> inorderTraversal(TreeNode root) {
            if(root == null) return new ArrayList<>();

            Stack<TreeNode> stack = new Stack<>();
            List<Integer> result = new ArrayList<>();

            TreeNode cur = root;
            while(cur != null || !stack.isEmpty()) {
                // 当前节点一直往左节点窜
                while(cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }

                // 弹出最左的节点
                cur = stack.pop();
                // 加入到结果集
                result.add(cur.val);
                // 当前节点指向右节点
                cur = cur.right;
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.TreeNode root = new Solution.TreeNode(1);
        root.left = null;
        root.right = new Solution.TreeNode(2);
        root.right.left = new Solution.TreeNode(3);
        root.right.right = null;

        System.out.println(app.inorderTraversal(root));


    }
}
