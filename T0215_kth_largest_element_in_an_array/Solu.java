package T0215_kth_largest_element_in_an_array;


import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 使用一个大小为k的最小堆
        // 初始加入k个元素到堆
        // 再遍历剩下的元素，当元素大于堆顶元素时，弹出堆顶元素，加入当前元素
        // 时间复杂度O(N*log(k)), 空间复杂度O(k)
        public int findKthLargest1(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1 || k > nums.length) return -1;

            // 使用1个k大小的最小堆
            Queue<Integer> minHeap = new PriorityQueue<>(k);

            for(int i=0; i<k; i++) {
                minHeap.add(nums[i]);
            }

            for(int i=k; i<nums.length; i++) {
                if(nums[i] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.add(nums[i]);
                }
            }

            return minHeap.poll();
        }

        // 方法2
        // 使用快排partition过程，随机选取pivot，换分为 <pivot、=pivot、>pivot的区域
        // 把第k大转换为第nums.length-k+1小
        // 时间复杂度O(N), 空间复杂度O(N)
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1 || k > nums.length) return -1;

            return process(nums, 0, nums.length-1, nums.length-k+1);
        }

        // 递归在[l, r]上寻找第k小的数字
        // k从1开始计数
        public int process(int[] nums, int l, int r, int k) {

            // 荷兰国旗过程
            int[] edge = partition(nums, l, r);

            if(edge[0] <= k-1 && k-1 <= edge[1]) return nums[edge[0]];
            else if(edge[0] > k-1) return process(nums, l, edge[0]-1, k);
            else return process(nums, edge[1]+1, r, k);
        }

        public int[] partition(int[] nums, int l, int r) {
            int pivot = nums[l + (int)(Math.random() * (r-l+1))]; // 随机选取pivot
            int p1 = l-1, p2 = r+1;
            int i = l;
            while(i < p2) {
                if(nums[i] < pivot) swap(nums, ++p1, i++);
                else if(nums[i] == pivot) i++;
                else swap(nums, --p2, i);
            }
            return new int[] {p1+1, p2-1};
        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }



    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {3,2,1,5,6,4};
        int k = 2;


        app.findKthLargest(nums, k);

    }
}
