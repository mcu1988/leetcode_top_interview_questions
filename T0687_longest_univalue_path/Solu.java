package T0687_longest_univalue_path;

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

        public class Info {
            int max; // 当前头结点返回的最大路径
            int fromHeadLen; // 当前以头结点出发向左或者向右返回的最大路径
            Info(int max, int fromHeadLen) {
                this.max = max;
                this.fromHeadLen = fromHeadLen;
            }
        }

        // 方法
        // 递归遍历每个节点，把每个节点当父节点，返回节点的Info信息
        // 当前节点有7中情况
        // 情况1，最长路径和当前父节点无关，出现在左节点上，是左节点的最长路径
        // 情况2，最长路径和当前父节点无关，出现在右节点上，是右节点的最长路径
        // 情况3，最长路径就是当前父节点
        // 情况4，当前路径是从父节点出发往左边的最长路径
        // 情况5，当前路径是从父节点出发往右边的最长路径
        // 情况7，当前路径是从左边到头结点再到右边的路径
        // 时间复杂度O(N), 空间复杂度最大为O(N), N是节点个数
        public int longestUnivaluePath(TreeNode root) {
            if(root == null) return 0;

            // info中的max是路径中的节点个数，题要求返回路径的长度
            return process(root).max - 1;
        }

        public Info process(TreeNode node) {
            if(node == null) return new Info(0, 0);

            // 获取左右节点的信息
            Info left = process(node.left);
            Info right = process(node.right);

            // max默认值是1，即只有当前父节点
            int max = 1;
            int fromHeadLen = 1;

            // 情况2和情况3，最大路径和头结点无关
            max = Math.max(max, left.max);
            max = Math.max(max, right.max);

            // 情况5，最大节点是从父节点出发往左边节点的路径
            if(node.left != null && node.left.val == node.val) {
                fromHeadLen = Math.max(fromHeadLen, left.fromHeadLen + 1);
            }

            // 情况6，最大节点是从父节点出发往右边节点的路径
            if(node.right != null && node.right.val == node.val) {
                fromHeadLen = Math.max(fromHeadLen, right.fromHeadLen + 1);
            }

            max = Math.max(max, fromHeadLen);
            // 情况7，最大节点是左边节点到父节点再到右节点的路径
            if(node.left != null && node.left.val == node.val
                    && node.right != null && node.right.val == node.val) {
                max = Math.max(max, left.fromHeadLen + right.fromHeadLen + 1);
            }

            return new Info(max, fromHeadLen);
        }
    }

    public static void main(String[] args) {
        Solution app = new Solution();

        Solution.TreeNode root = new Solution.TreeNode(1);
        root.left = new Solution.TreeNode(4);
        root.right = new Solution.TreeNode(5);
        root.left.left = new Solution.TreeNode(4);
        root.left.right = new Solution.TreeNode(4);
        root.right.right = new Solution.TreeNode(5);

        System.out.println(app.longestUnivaluePath(root));
    }
}

