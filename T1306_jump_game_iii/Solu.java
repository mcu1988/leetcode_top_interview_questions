package T1306_jump_game_iii;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solu {

    static class Solution {

        // 方法1
        // 深度优先遍历，设计递归函数返回能够到达0位置
        // 递归时如果cur越界，返回false，同时如果重复走到一个位置时，陷入死循环，一定不是最优值，返回false
        // 使用boolean数组标记位置是否到达
        // start的索引从0开始
        public boolean canReach1(int[] nums, int start) {
            // 输入有效性判断
            if (nums == null || nums.length == 0 || start < 0 || start >= nums.length)
                return false;

            boolean[] isReached = new boolean[nums.length];
            boolean result = process(nums, start, isReached);

            return result;
        }

        // 递归函数，返回能否到达
        // cur表示当前来到的位置，cur索引从0开始
        // isReached中start到cur已经走过的元素标记为true
        // 递归函数会不断尝试，直到返回true时，不再尝试
        public boolean process(int[] nums, int cur, boolean[] isReached) {
            // cur 越界了，返回-1
            if (cur < 0 || cur >= nums.length) return false;

            // 第二次到达，返回false
            if(isReached[cur]) return false;

            // 已经到达0位置
            if (nums[cur] == 0) return true;

            // 当前位置标记为已走过
            isReached[cur] = true;

            // 找到左边的位置和右边的位置
            int left = cur - nums[cur];
            int right = cur + nums[cur];

            // 左边和右边只要有一种情况能到达，就返回true，不再尝试
            if(process(nums, left, isReached)) return true;
            if(process(nums, right, isReached)) return true;

            // 退递归时，当前位置标记为没走过
            isReached[cur] = false;

            // 向左走和向右走都失败，返回false
            return false;
        }

        // 方法2
        // 使用广度优先遍历
        // 建立一个遍历的二叉树，二叉树的层数代表走的步数
        // 按层遍历二叉树，当低层出现end时就是最小的步数
        // 使用map记录每个元素的层数
        public boolean canReach(int[] nums, int start) {
            // 输入有效性判断
            if (nums == null || nums.length == 0 || start < 0 || start >= nums.length)
                return false;

            // 队列层级遍历二叉树, 队列中存放数组的index
            Queue<Integer> queue = new LinkedList<>();
            // map记录 数组index -> 步数的映射
            HashMap<Integer, Integer> levelMap = new HashMap<>();
            // 初始记录start
            queue.add(start);
            levelMap.put(start, 0);
            while(!queue.isEmpty()) {
                // 出队列
                int index = queue.poll();
                int level = levelMap.get(index);
                // 已经是end时，直接返回
                if(nums[index] == 0) return true;

                int left = index - nums[index];
                int right = index + nums[index];

                // 分别判断左边和右边是否越界，并且是否已经遍历过
                if(left >= 0 && left < nums.length && !levelMap.containsKey(left)) {
                    queue.add(left);
                    levelMap.put(left, level + 1);
                }

                if(right >= 0 && right < nums.length && !levelMap.containsKey(right)) {
                    queue.add(right);
                    levelMap.put(right, level + 1);
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Solution app = new Solution();

        int[] nums = {4,2,3,0,3,1,2};
        int start = 5;

        System.out.println(app.canReach(nums, start));
    }
}

