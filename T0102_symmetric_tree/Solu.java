package T0102_symmetric_tree;

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
        // 层序遍历二叉树
        // 使用队列实现
        // 时间复杂度O(N), 空间复杂度O(N)
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return new ArrayList<>();

            // 队列中初始加入根节点
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            TreeNode cur = root;
            List<List<Integer>> result = new ArrayList<>();
            int curLevelSize = 0;
            while(!queue.isEmpty()) {
                // 记录当前层的大小
                curLevelSize = queue.size();
                List<Integer> list = new ArrayList<>();
                // 弹出当前层的元素
                for(int i=0; i<curLevelSize; i++) {
                    cur = queue.poll();
                    list.add(cur.val);
                    // 加入下一层的元素
                    if(cur.left != null) queue.add(cur.left);
                    if(cur.right != null) queue.add(cur.right);
                }
                result.add(list);
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.TreeNode root = new Solution.TreeNode(3);
        root.left = new Solution.TreeNode(9);
        root.right = new Solution.TreeNode(20);
        root.right.left = new Solution.TreeNode(15);
        root.right.right = new Solution.TreeNode(7);

        System.out.println(app.levelOrder(root));
    }
}
