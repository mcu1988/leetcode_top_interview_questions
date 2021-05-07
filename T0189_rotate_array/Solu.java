package T0189_rotate_array;

import java.util.Arrays;
import java.util.HashMap;

class Solu {

    static class Solution {

        // 方法
        // 先把整个数组逆序，再逆序前k个数字得到前半部分，再逆序后半部分
        // k > N时，先对N取余
        // 时间复杂度O(N), 空间复杂度(1)
        public void rotate(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1 || k == nums.length)
                return;

            int N = nums.length;
            // k > N时，对N取余
            k = k % N;
            reverse(nums, 0, N-1);
            reverse(nums, 0, k-1);
            reverse(nums, k, N-1);
        }

        // 把数组i...j范围上的数字逆序
        public void reverse(int[] nums, int i, int j) {
            for(; i<j; i++, j--) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        public void printArr(int[] nums) {
            for(int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        app.printArr(nums);
        app.rotate(nums, k);
        app.printArr(nums);
    }
}
