package T0103_binary_tree_zigzag_level_order_traversal;

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
        // 使用双端队列实现
        // 使用变量标记从头或者尾加入或者弹出
        // 时间复杂度O(N), 空间复杂度O(N)
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) return new ArrayList<>();

            // 队列中初始加入根节点
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);

            TreeNode cur = root;
            // 使用方向标记从头部或者尾部加入或者弹出
            boolean left2right = true;
            List<List<Integer>> result = new ArrayList<>();
            int curLevelSize = 0;
            while(!queue.isEmpty()) {
                // 记录当前层的大小
                curLevelSize = queue.size();
                List<Integer> list = new ArrayList<>();
                // 弹出当前层的元素

                // direction = true, 从头部弹出，从尾部加入，加入时先左后右
                // direction = false, 从尾部弹出，从头部加入，加入时先右后左
                for(int i=0; i<curLevelSize; i++) {
                    if(left2right) cur = queue.pollFirst();
                    else cur = queue.pollLast();

                    list.add(cur.val);
                    // 加入下一层的元素
                    if(left2right) {
                        if(cur.left != null) queue.addLast(cur.left);
                        if(cur.right != null) queue.addLast(cur.right);
                    } else {
                        if(cur.right != null) queue.addFirst(cur.right);
                        if(cur.left != null) queue.addFirst(cur.left);
                    }
                }
                // 没遍历完一层，反向改变
                left2right = !left2right;
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

        System.out.println(app.zigzagLevelOrder(root));
    }
}
