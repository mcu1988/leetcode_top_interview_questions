package T0042_trapping_rain_water;

import java.util.ArrayList;
import java.util.List;

class Solu {

    private Solution app;

    static class Solution {

        // 方法1
        // 考虑当前点上存储的水量
        // 使用个辅助数组，分别存放左边和右边的最大值，从左往右遍历，得到<=i位置的最大值，从右往左遍历，得到>=i的最大值
        // 当前点的蓄水量 min(left, right) - height[i]
        // 0和len-1位置没有水量，把[1...len-2]位置的蓄水量相加就是结果
        // 时间复杂度O(N), 空间复杂度O(N)
        public int trap1(int[] height) {
            // 0，1，2个元素时，没有蓄水
            if(height == null || height.length < 3) return 0;

            int len = height.length;
            // <=i位置的左边的最大值
            int[] leftMax = new int[len];
            leftMax[0] = height[0];
            // >=i位置的右边的最大值
            int[] rightMax = new int[len];
            rightMax[len-1] = height[len-1];

            // 填充左边的最大值数组
            for(int i=1; i<len; i++) {
                if(height[i] > leftMax[i-1]) leftMax[i] = height[i];
                else leftMax[i] = leftMax[i-1];
            }

            // 填充右边的最大值数组
            for(int i=len-2; i>=0; i--) {
                if(height[i] > rightMax[i+1]) rightMax[i] = height[i];
                else rightMax[i] = rightMax[i+1];
            }

            int result = 0;
            for(int i=1; i<len-1; i++) { // 遍历1...len-2位置，累加
                result += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
            return result;
        }

        // 方法2
        // 思路和方法1一致, 方法2只需要遍历数组一次，并且不需要辅助数组
        // 当前位置的蓄水量需要知道左边和右边最大值数组中的最小值
        // 使用左右两个指针，初始时，l=0, r=len-1, 使用两个最大值leftMax，rightMax，分别记录左边[0...l]和右边[r...len-1]的最大值
        // 找到leftMax和rightMax较小的那个，假设leftMax较小，那么l位置右边的最大值>=rihtMax, l位置的最小高度是leftMax, l位置可以结算, l++
        // l > r时循坏结束
        // 时间复杂度O(N), 空间复杂度O(1)
        public int trap(int[] height) {
            // 0，1，2个元素时，没有蓄水
            if(height == null || height.length < 3) return 0;

            int len = height.length;

            // 使用双指针，l表示左边待结算额位置，r表示右边要结算的位置
            int l = 1, r= len - 2;

            // <=l位置的左边的最大值，初始
            int leftMax = Math.max(height[0], height[1]);
            // >=r位置的右边的最大值
            int rightMax = Math.max(height[len-1], height[len-2]);

            int result = 0;
            // 每次循坏结算leftMax和rightMax小的一侧
            while(l <= r) { // l > r时所有位置结算完，循环终止
                if(leftMax <= rightMax) {
                    // 结算l位置, l++, 更新leftMax
                    result += leftMax - height[l];
                    l++;
                    leftMax = height[l] > leftMax ? height[l] : leftMax;
                } else {
                    // 结算r位置，r--，更新rightMax
                    result += rightMax - height[r];
                    r--;
                    rightMax = height[r] > rightMax ? height[r] : rightMax;
                }
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] height = {4,2,0,3,2,5};
        app.trap(height);
        System.out.println(app.trap(height));
    }
}
