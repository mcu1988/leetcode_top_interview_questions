package T0069_sqrtx;

import java.util.Arrays;

class Solu {

    static class Solution {

        // 方法1
        // 二分查找
        // 时间复杂度O(log(N)), 空间复杂度O(1)
        public int mySqrt1(int x) {
            if(x < 0) return -1;

            int l = 0, r = x;
            while(l <= r) { // 0~x范围内二分查找
                int mid = ((r - l) >> 1) + l;
                // mid的平方可能会超过int的最大值，需要使用long
                long midSqure = (long)mid * (long)mid;

                if(x == midSqure) return mid;
                else if(x > midSqure) l = mid + 1;
                else r = mid - 1;
            }

            // 停止循环时，r是目标位置，l是r+1
            return r;
        }

        // 方法2
        // 使用牛顿迭代法求解方程 iter^2 - n = 0的近似解
        // 时间复杂度O(log(N)), 空间复杂度O(1)
        public int mySqrt(int x) {
            if (x < 0) return -1;

            // 求解方程 iter^2 - n = 0的近似解
            long iter = x;
            while(iter * iter > x) {
                // 输入的迭代公式
                iter = (iter + x / iter) /  2;
            }
            return (int)iter;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int x = 2147395599;

        app.mySqrt(x);

        System.out.println(app.mySqrt(x));

    }
}
