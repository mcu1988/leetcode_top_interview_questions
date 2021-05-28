package T0324_wiggle_sort_ii;

import java.util.Arrays;

class Solu {

    static class Solution {

        // 方法1
        // 先排序，然后把排序后的数组分为左右2半
        // 两部分穿插起来构成新数组
        public void wiggleSort(int[] nums) {
            if(nums == null || nums.length < 2)
                return;

            Arrays.sort(nums);
            int N = nums.length, half = N / 2;

            int[] newArr = new int[N];
            if(N % 2 == 1) newArr[N-1] = nums[half];

            for(int i=0; i<half; i++) {
                newArr[2*i] = nums[i];
                if(N % 2 == 1) newArr[2*i+1] = nums[half+1+i];
                else newArr[2*i+1] = nums[half+i];
            }

            for(int i=0; i<N; i++) nums[i] = newArr[i];

        }

        public void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {1,3,2};

        app.wiggleSort(nums);

    }
}
