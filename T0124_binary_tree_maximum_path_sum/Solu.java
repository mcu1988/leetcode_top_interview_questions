package T0124_binary_tree_maximum_path_sum;

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

        class Info {
            int max;
            int fromHeadMax;
            Info(int max, int fromHeadMax) {
                this.max = max;
                this.fromHeadMax = fromHeadMax;
            }
        }
        // 方法
        // 递归遍历左右子树，返回节点信息，节点子树上的最大路径值，节点子树从head出发的最大路径值
        // 当前节点为头结点的情况
        // 情况1: 最大值不经过头结点，是左子树的最大值
        // 情况2: 最大值不经过头结点，是右子树的最大值
        // 情况3: 最大值只有头结点
        // 情况4: 最大值是头结点出发的向左边的
        // 情况5: 最大值是头结点出发的向右边的
        // 情况6: 最大值是头结点结合左边的一部分和右边的一部分
        // 时间复杂度O(N), 空间复杂度O(h), h是树的高度
        public int maxPathSum(TreeNode root) {
            if (root == null) return Integer.MIN_VALUE;

            return process(root).max;
        }

        public Info process(TreeNode head) {
            if(head == null) return null;

            // 获取左右节点信息
            Info left= process(head.left);
            Info right = process(head.right);

            // 情况3
            int max = head.val;
            int fromHeadMax = head.val;
            if(left != null) {
                // 情况4
                max = Math.max(max, fromHeadMax + left.fromHeadMax);
                // 情况1
                max = Math.max(max, left.max);
                fromHeadMax = Math.max(fromHeadMax, head.val + left.fromHeadMax);
            }

            if(right != null) {
                // 情5和情况6
                max = Math.max(max, fromHeadMax + right.fromHeadMax);
                // 情况2
                max = Math.max(max, right.max);
                fromHeadMax = Math.max(fromHeadMax, head.val + right.fromHeadMax);
            }

            return new Info(max, fromHeadMax);
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.TreeNode root = new Solution.TreeNode(-10);
        root.left = new Solution.TreeNode(9);
        root.right = new Solution.TreeNode(20);
        root.right.left = new Solution.TreeNode(15);
        root.right.right = new Solution.TreeNode(7);

        System.out.println(app.maxPathSum(root));
    }
}
