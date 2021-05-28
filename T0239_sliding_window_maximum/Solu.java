package T0239_sliding_window_maximum;

import java.util.*;

class Solu {

    static class Solution {


        // 方法1
        // 使用TreeMap排序， key是数组的值，value是对应的数组index
        // 初始时，加入k个元素，获取第一个窗口内的最大值
        // 循环，每次加入当前元素，弹出 i-k位置的窗口外的值，再找到窗口内的最大值
        // TreeMap的key不会重复，每次有重复的key加入时，新的key的index会覆盖旧的index，即窗口内最新的值会覆盖旧的值
        // 时间复杂度O(N*log(k)), 空间复杂度O(k)
        public int[] maxSlidingWindow1(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1 || k > nums.length)
                return new int[]{};

            // 使用TreeMap对数值进行排序， key是值，value是对应的数组索引，用来判断数值是否越界
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            // 初始加入前k个元素，得到第一个窗口内的最大值
            for(int i=0; i<k; i++) treeMap.put(nums[i], i);

            int N = nums.length;
            int[] result = new int[N-k+1];
            result[0] = treeMap.lastKey();

            // 后面的元素依次加入，把过期的元素清除掉，再获得当前窗口的最大值
            for(int i=k; i<N; i++) {
                treeMap.put(nums[i], i); // 加入当前元素
                // 删除窗口外的元素，如果窗口外的元素索引不是i-k，说明窗口内有重复值覆盖了前面的值
                if(treeMap.get(nums[i-k]) == i-k) treeMap.remove(nums[i-k]);
                result[i-k+1] = treeMap.lastKey(); // 找到窗口内的最大值
            }
            return result;
        }

        // 方法2
        // 使用单调双端队列，队列内的元素保持不严格的单调递减，新元素从队列尾部加入，窗口内的最大元素是队列的头部的第一个元素
        // 新的元素加入到队列如果小于队列尾部的元素，尾部元素出队列，直到尾部元素<=当前要加入的元素
        // 队列内存储数组元素的索引
        // 如果队列头部的元素超过了窗口范围，头部元素出队列
        // 如果元素相等，都要依次进入队列
        // 时间复杂度O(N), 空间复杂度O(k)
        public int[] maxSlidingWindow(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1 || k > nums.length)
                return new int[] {};

            int N = nums.length;
            // 使用双端队列，队列元素递减，新元素从尾部加入，队列头部是最大值
            LinkedList<Integer> maxQueue = new LinkedList<>();

            int[] result = new int[N-k+1];
            for(int i=0; i<N; i++) {
                // 把队列尾部比新元素小的清除，清除后再加入队列
                while(!maxQueue.isEmpty() && nums[maxQueue.peekLast()] < nums[i]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(i);

                int firstIndex = maxQueue.peekFirst();
                if(firstIndex < i-k+1) maxQueue.pollFirst(); // 清除过期元素
                if(i >= k-1) result[i-k+1] = nums[maxQueue.peekFirst()]; // 获得窗口内的最大值
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();


        int[] nums = {1,3,1,2,0,5};
        int k = 3;

        app.maxSlidingWindow(nums, k);
    }
}
