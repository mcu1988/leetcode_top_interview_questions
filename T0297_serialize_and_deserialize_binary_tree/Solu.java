package T0297_serialize_and_deserialize_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

class Solu {

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static class Solution {

        // 方法1
        // 使用前序遍历序列化
        // 时间复杂度O(N), 空间复杂度O(N)
        public static class Codec1 {

            // 前序遍历序列化
            // 把空节点使用null表示，节点之间使用特殊字符"#"标记
            public String serialize(TreeNode root) {
                if(root == null) return "null";

                String result = String.valueOf(root.val);
                result = result + "#" + serialize(root.left);
                result = result + "#" + serialize(root.right);
                return result;
            }

            public TreeNode deserialize(String data) {
                String[] arr = data.split("#");

                int[] index = {0};
                return process(arr, index);

            }

            // 前序遍历反序列化
            // 使用index来控制数组来到的位置
            public TreeNode process(String[] arr, int[] index) {
                if(index[0] >= arr.length || arr[index[0]].equals("null")) {
                    index[0]++;
                    return null;
                }

                TreeNode root = new TreeNode(Integer.valueOf(arr[index[0]]));
                index[0]++;
                root.left = process(arr, index);
                root.right = process(arr, index);
                return root;
            }
        }

        // 方法2
        // 使用后序遍历序列化
        // 时间复杂度O(N), 空间复杂度O(N)
        public static class Codec2 {

            // 后序遍历序列化
            // 把空节点使用null表示，节点之间使用特殊字符"#"标记
            public String serialize(TreeNode root) {
                if(root == null) return "null";

                String result = serialize(root.left);
                result = result + "#" + serialize(root.right);
                result = result + "#" + String.valueOf(root.val);
                return result;
            }

            public TreeNode deserialize(String data) {
                String[] arr = data.split("#");

                int[] index = {arr.length-1};
                return process(arr, index);

            }

            // 后序遍历反序列化
            // 使用index来控制数组来到的位置
            public TreeNode process(String[] arr, int[] index) {
                if(index[0] < 0 || arr[index[0]].equals("null")) {
                    index[0]--;
                    return null;
                }

                TreeNode root = new TreeNode(Integer.valueOf(arr[index[0]]));
                index[0]--;
                root.right = process(arr, index);
                root.left = process(arr, index);
                return root;
            }
        }


        // 方法3
        // 层序遍历序列化
        // 时间复杂度O(N), 空间复杂度O(N)
        public static class Codec {

            // 层序遍历序列化
            // 把空节点使用null表示，节点之间使用特殊字符"#"标记
            public String serialize(TreeNode root) {
                if(root == null) return "null";

                String result = String.valueOf(root.val);
                Queue<TreeNode> queue = new LinkedList<>();
                queue.add(root);

                while(!queue.isEmpty()) {
                    TreeNode node = queue.poll();

                    if(node.left != null) {
                        result += "#" + node.left.val;
                        queue.add(node.left);
                    } else {
                        result += "#" + "null";
                    }

                    if(node.right != null) {
                        result += "#" + node.right.val;
                        queue.add(node.right);
                    } else {
                        result += "#" + "null";
                    }
                }

                return result;
            }

            // 使用队列加入新构建的当前层的节点
            // 原始序列化的队列和新队列同步弹出
            public TreeNode deserialize(String data) {
                String[] arr = data.split("#");

                if(arr.length == 1) return null;

                TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
                Queue<TreeNode> queue = new LinkedList<>();
                queue.add(root);

                int index = 1;
                while(!queue.isEmpty()) {
                    TreeNode node = queue.poll();

                    node.left = arr[index].equals("null") ? null : new TreeNode(Integer.valueOf(arr[index]));
                    index++;

                    node.right = arr[index].equals("null") ? null : new TreeNode(Integer.valueOf(arr[index]));
                    index++;

                    // 新节点不是null时，才加入新队列
                    if(node.left != null) queue.add(node.left);
                    if(node.right != null) queue.add(node.right);
                }
                return root;
            }

        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.Codec codec = new Solution.Codec();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String seried = codec.serialize(root);
        System.out.println("序列化后： ");
        System.out.println(seried);

        TreeNode newRoot = codec.deserialize(seried);
        System.out.println("反序列化后：");
        System.out.println(codec.serialize(newRoot));
    }
}
