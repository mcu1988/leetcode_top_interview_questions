package T0088_merge_sorted_array;

import java.util.Stack;

class Solu {

    static class Solution {

        // 方法
        // 使用2个索引，从后往前遍历2个数组
        // 填充nums1时也是从后往前填充
        // 时间复杂度O(m+n), 空间复杂度O(1)
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || nums1.length != m+n || m < 0 || m > nums1.length ||  n < 0 || n > nums2.length)
                return;

            int p1 = m-1, p2 = n-1, index = m+n-1;
            // 当两数组都没遍历完时
            while(p1 >= 0 && p2 >= 0) { // 从后往前遍历
                if(nums1[p1] >= nums2[p2]) nums1[index--] = nums1[p1--];
                else nums1[index--] = nums2[p2--];
            }

            // 退出while后，2数组至少有一个遍历完了
            // nums2遍历完了，nums1剩下的不用管

            // nums2没遍历完的拼接到nums1
            while(p2 >= 0) nums1[index--] = nums2[p2--];

        }

        public void printArr(int[] arr, int len) {
            for(int i=0; i<len; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        int m = 3, n = 3;

        app.printArr(nums1, m);
        app.printArr(nums2, n);

        app.merge(nums1, m, nums2, n);

        app.printArr(nums1, m+n);


    }
}
