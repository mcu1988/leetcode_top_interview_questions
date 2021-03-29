package T0011_container_with_most_water;

import org.omg.CORBA.MARSHAL;
import org.omg.PortableInterceptor.INACTIVE;

class Solu {
    static class Solution {

        // 解法1
        // 确定2个索引，计算面积，更新最大值
        // 时间复杂度O(n^2), 空间复杂度O(1)
        public int maxArea1(int[] height) {
            if(height == null || height.length < 2) return 0;

            int len = height.length;
            int result = 0, tmpArea = 0;
            for(int i=0; i<len-1; i++) {
                for(int j=i+1; j<len; j++) {
                    tmpArea = Math.min(height[i], height[j]) * (j - i);
                    result = Math.max(result, tmpArea);
                }
            }
            return result;
        }

        // 解法2
        // 使用左右两个双指针
        // 每次移动元素值小的那个指针
        // 每次移动在朝着可能出现更大解的方向移动
        public int maxArea(int[] height) {
            if(height == null || height.length < 2) return 0;

            int len = height.length;
            int result = 0, tmpArea = 0;
            // 初始左右指针的位置
            int l = 0, r = len - 1;
            while(l < r) {
                // 当前位置的面积不一定是要移动元素位置的最优解
                tmpArea = Math.min(height[l], height[r]) * (r - l);
                // 每次移动小的元素，寻找更大可能的面积
                if(height[l] <= height[r]) l++;
                else r--;
                result = Math.max(result, tmpArea);
            }
            return result;
        }

    }

}
