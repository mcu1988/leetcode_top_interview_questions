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
        public int findKthLargest2(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1 || k > nums.length) return -1;

            return process(nums, 0, nums.length-1, nums.length-k);
        }

        // 递归在[l, r]上寻找第k小的数字
        // k从0开始计数
        public int process(int[] nums, int l, int r, int k) {

            // 荷兰国旗过程
            int[] edge = partition(nums, l, r);

            if(k >= edge[0] && k <= edge[1]) return nums[edge[0]];
            else if(edge[0] > k) return process(nums, l, edge[0]-1, k);
            else return process(nums, edge[1]+1, r, k);
        }

        // 荷兰国旗partition过程
        // 返回大小为2的数组
        // [ ans[0], ans[1] ] 范围内的数字等于pivot
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

        // 方法3
        // 方法2的pivot是随机选取的，平均时间复杂度O(N), 最差退化为O(N^2)
        // 方法3使用bfprt算法选择pivot，使时间复杂度严格收敛于O(N)
        // 时间复杂度O(N), 空间复杂度O(N)
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1 || k > nums.length) return -1;

            return bfprt(nums, 0, nums.length-1, nums.length-k);
        }

        // bfprt算法
        // 在[l, r]范围上找到第k小的数
        // k从0开始计数
        public int bfprt(int[] nums, int l, int r, int k) {
            if(l == r) return nums[l];
            // 确定pivot
            int pivot = medianOfMedian(nums, l, r);
            // 荷兰国旗partition过程, 返回大小为2的数组，数组范围内是等于pivot的数
            int[] edge = partition1(nums, l, r, pivot);

            if(k >= edge[0] && k <= edge[1]) return nums[edge[0]];
            else if(edge[0] > k) return bfprt(nums, l, edge[0]-1, k);
            else return bfprt(nums, edge[1]+1, r, k);
        }

        // 荷兰国旗partition过程
        // 返回大小为2的数组
        // [ ans[0], ans[1] ] 范围内的数字等于pivot
        public int[] partition1(int[] nums, int l, int r, int pivot) {
            int p1 = l-1, p2 = r+1;
            int i = l;
            while(i < p2) {
                if(nums[i] < pivot) swap(nums, ++p1, i++);
                else if(nums[i] == pivot) i++;
                else swap(nums, --p2, i);
            }
            return new int[] {p1+1, p2-1};
        }

        // 获取[l, r]范围上的中位数数组的中位数
        // 先把[l, r]范围上的数字划分为5个是数字一组
        // 组内排序
        // 找到组内的中位数，组成新数组
        // 找到新数组的中位数
        public int medianOfMedian(int[] nums, int l, int r) {

            // [l, r]上的元素个数是否是5的倍数
            int yushu = (r-l+1) % 5 == 0 ? 0 : 1;
            // 分组大小为5
            int[] median = new int[(r-l+1) / 5 + yushu];

            for(int i=0; i<median.length; i++) {
                int localL = l + i * 5;
                int localR = Math.min(localL + 4, r);
                median[i] = median(nums, localL, localR);
            }

            // 找到中位数数组的中位数
            return bfprt(median, 0, median.length-1, median.length/2);
        }

        // 返回上中位数
        public int median(int[] nums, int l ,int r) {
            // [l, r]范围上排序
            Arrays.sort(nums, l, r+1);
//            insertSort(nums, l, r);
            return nums[l + (r-l)/2];
        }

        // 插入排序
        public void insertSort(int[] nums, int l, int r) {
            for(int i=l; i<=r; i++) {
                int j = i;
                while(j > 0 && nums[j] < nums[j-1]) {
                    swap(nums, j, j-1);
                    j--;
                }
            }
        }

    }



    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {3,2,1,5,6,4};
        int k = 2;


        app.findKthLargest(nums, k);

    }
}