package T0045_jump_game_ii_follow_up;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solu {

    static class Solution {

        // 方法1
        // 深度优先遍历，设计递归函数返回当前位置cur到end的最小步数
        // 递归时如果cur越界，返回-1，同时如果重复走到一个位置时，陷入死循环，一定不是最优值，返回-1
        // 使用boolean数组标记位置是否到达
        // start和end的索引从1开始
        public int jump(int[] nums, int start, int end) {
            // 输入有效性判断
            if (nums == null || nums.length == 0 || start < 1 || start > nums.length || end < 1 || end > nums.length)
                return -1;

            // 标记元素是否到达过，用于判断是否重复到达
            boolean[] isReached = new boolean[nums.length];
            int minStep = process(nums, start, end, 0, isReached);

            return minStep;
        }

        // 递归函数，返回当前位置cur到end最小的步数
        // cur表示当前来到的位置，cur索引从1开始
        // end表示目标位置
        // step表示从start到cur的步数
        // isReached中start到cur已经走过的元素标记为true
        public int process(int[] nums, int cur, int end, int step, boolean[] isReached) {
            // cur 越界了，返回-1
            if (cur < 1 || cur > nums.length) return -1;
            // 第二次到达cur时，返回-1
            if (isReached[cur-1]) return -1;
            // cur == end，返回step，就是当前的步数，但不是最小步数
            if (cur == end) return step;

            // 标记当前位置为true
            isReached[cur-1] = true;
            // 找到左边的位置和右边的位置
            int left = cur - nums[cur - 1];
            int right = cur + nums[cur - 1];

            // 走左边和走右边的情况，去最小的作为cur到end的最小步数
            int minStep = -1;
            int leftStep = process(nums, left, end, step + 1, isReached);
            int rightStep = process(nums, right, end, step + 1, isReached);
            if (leftStep != -1 && rightStep != -1) {
                minStep = Math.min(leftStep, rightStep);
            } else if (leftStep != -1) {
                minStep = leftStep;
            } else if (rightStep != -1) {
                minStep = rightStep;
            }

            // 退递推时把当前位置标记为false
            isReached[cur-1] = false;

            return minStep;
        }

        // 方法2
        // 方法1中的递归函数可变状态太多，很难改递归，方法2尝试减少递归函数的可变参数
        // 去掉isReached数组；由于递归过程不能到达同一个位置2次，隐含条件：step <= nums.length-1
        // 递归时如果cur越界，返回-1，同时如果重复走到一个位置时，陷入死循环，一定不是最优值，返回-1
        // 使用boolean数组标记位置是否到达
        // start和end的索引从1开始
        public int jump2(int[] nums, int start, int end) {
            // 输入有效性判断
            if (nums == null || nums.length == 0 || start < 1 || start > nums.length || end < 1 || end > nums.length)
                return -1;

            // 标记元素是否到达过，用于判断是否重复到达
            boolean[] isReached = new boolean[nums.length];
            int minStep = process(nums, start, end, 0, isReached);

            return minStep;
        }

        // 递归函数，返回当前位置cur到end最小的步数
        // cur表示当前来到的位置，cur索引从1开始
        // end表示目标位置
        // step表示从start到cur的步数
        // 隐含条件 step <= nums.length - 1
        // 可变参数只有cur和step，可以改动态规划
        public int process2(int[] nums, int cur, int end, int step) {
            // cur 越界了，返回-1, 或者已经走的步数查过了N-1, 出现了重复
            if (cur < 1 || cur > nums.length || step > nums.length-1) return -1;

            // cur == end，返回step，就是当前的步数，但不是最小步数
            if (cur == end) return step;

            // 找到左边的位置和右边的位置
            int left = cur - nums[cur - 1];
            int right = cur + nums[cur - 1];

            // 走左边和走右边的情况，去最小的作为cur到end的最小步数
            int minStep = -1;
            int leftStep = process2(nums, left, end, step + 1);
            int rightStep = process2(nums, right, end, step + 1);
            if (leftStep != -1 && rightStep != -1) {
                minStep = Math.min(leftStep, rightStep);
            } else if (leftStep != -1) {
                minStep = leftStep;
            } else if (rightStep != -1) {
                minStep = rightStep;
            }

            return minStep;
        }

        // 方法3
        // 使用广度优先遍历
        // 建立一个遍历的二叉树，二叉树的层数代表走的步数
        // 按层遍历二叉树，当低层出现end时就是最小的步数
        // 使用map记录每个元素的层数
        public int jump3(int[] nums, int start, int end) {
            // 输入有效性判断
            if (nums == null || nums.length == 0 || start < 1 || start > nums.length || end < 1 || end > nums.length)
                return -1;

            // 队列层级遍历二叉树, 队列中存放数组索引
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
                if(index == end) return level;

                int left = index - nums[index-1];
                int right = index + nums[index-1];

                // 分别判断左边和右边是否越界，并且是否已经遍历过
                if(left >= 1 && left <= nums.length && !levelMap.containsKey(left)) {
                    queue.add(left);
                    levelMap.put(left, level + 1);
                }

                if(right >= 1 && right <= nums.length && !levelMap.containsKey(right)) {
                    queue.add(right);
                    levelMap.put(right, level + 1);
                }
            }

            return -1;
        }
    }


    // for test
    public static int[] gerRandomArray(int N, int R) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * R);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 对数器验证
    public static void main(String[] args) {

        Solution app = new Solution();

        int maxN = 20;
        int maxV = 10;
        int testTimes = 200000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerRandomArray(maxN, maxV);
            int N = arr.length;
            int start = (int) (Math.random() * N) + 1;
            int end = (int) (Math.random() * N) + 1;
            int ans1 = app.jump(arr, start, end);
            int ans2 = app.jump2(arr, start, end);
            int ans3 = app.jump3(arr, start, end);

            if (ans1 != ans3 && ans1 != ans3 && ans2 != ans3) {
                printArray(arr);
                System.out.println("start : " + start);
                System.out.println("end : " + end);
                System.out.println("Oops!");
                System.out.println("ans1 : " + ans1);
                System.out.println("ans2 : " + ans2);
                System.out.println("ans3 : " + ans3);
                break;
            }
        }
        System.out.println("test end");
    }

}

