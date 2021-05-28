package T0238_product_of_array_except_self;

import java.util.*;

class Solu {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {

        // 方法1
        // 使用2个数组分别存放前缀乘积和后缀乘积
        // 时间复杂度O(N), 空间复杂度O(N)
        public int[] productExceptSelf1(int[] nums) {
            if(nums == null || nums.length == 0) return new int[] {};

            int N = nums.length;
            int[] pre = new int[N];
            pre[0] = nums[0];
            int[] post = new int[N];
            post[N-1] = nums[N-1];
            for(int i=1; i<N; i++) {
                pre[i] = pre[i-1] * nums[i];
            }
            for(int i=N-2; i>=0; i--) {
                post[i] = post[i+1] * nums[i];
            }

            int[] result = new int[N];
            result[0] = post[1]; result[N-1] = pre[N-2];
            for(int i=1; i<N-1; i++) {
                result[i] = pre[i-1] * post[i+1];
            }
            return result;
        }

        // 方法2
        // 使用result数组存放从后往前的乘积，从前往后的乘积遍历过程中逐步计算
        // result数组不占用额外空间
        // 时间复杂度O(N), 空间复杂度O(1)
        public int[] productExceptSelf(int[] nums) {
            if (nums == null || nums.length == 0) return new int[]{};

            int N = nums.length;
            int[] result = new int[N];
            result[N-1] = nums[N-1];
            for(int i=N-2; i>=0; i--) result[i] = result[i+1] * nums[i];

            result[0] = result[1];
            int pre = nums[0];
            for(int i=1; i<N-1; i++) {
                result[i] = pre * result[i+1];
                pre *= nums[i];
            }
            result[N-1] = pre;

            return result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {-1};

        app.productExceptSelf(nums);
    }
}
