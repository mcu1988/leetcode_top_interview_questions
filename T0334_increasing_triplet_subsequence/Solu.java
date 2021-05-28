package T0334_increasing_triplet_subsequence;

class Solu {

    static class Solution {

        // 方法1
        // 设置3个变量，first, second, third, 分别代表3个递增的数字
        // 遍历数组，如果当前位置 <= first, 更新第一个数
        // 如果当前位置 >first, <= second, 更新第二个数
        // 如果当前位置 >second,  返回true
        // 最终结束时的3个值不一定是最终的3个递增的值，eg, [1,2,0,3]
        // 时间复杂度O(N), 空间复杂度O(1)
        public boolean increasingTriplet1(int[] nums) {
            if (nums == null || nums.length < 3)
                return false;

            int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE, third = Integer.MAX_VALUE;
            for(int i=0; i<nums.length; i++) {
                if(nums[i] <= first) first = nums[i];
                else if(nums[i] <= second) second = nums[i];
                else {
                    third = nums[i];
                    return true;
                }
            }
            return false;
        }

        // 方法2
        // 方法1中当前数组先跟第1个元素比较，再跟第2个比较，...
        // 可以使用二分法，确定当前元素落在那个位置上
        // 二分法适用于找到递增的k个数字
        // 最终结束时的3个值不一定是最终的3个递增的值，eg, [1,2,0,3]
        // 时间复杂度O(N), 空间复杂度O(1)
        public boolean increasingTriplet(int[] nums) {
            if(nums == null || nums.length < 3)
                return false;

            int[] arr = new int[3];
            arr[0] = nums[0];
            int l = 0, r = 0, mid = 0, arrRight = 0;
            for(int i=1; i<nums.length; i++) {
                l = 0;
                r = arrRight;
                while(l <= r) {
                    mid = (l + r) / 2;
                    if(nums[i] > arr[mid]) l = mid + 1;
                    else r = mid - 1;
                }

                arrRight = Math.max(arrRight, l);
                arr[l] = nums[i];
                if(l == 2) return true;
            }
            return false;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {1,2,0,3};
        app.increasingTriplet(nums);


    }
}
