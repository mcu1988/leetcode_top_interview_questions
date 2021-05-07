package T0172_factorial_trailing_zeroes;

import java.util.HashMap;

class Solu {

    static class Solution {

        // 方法
        // 5和偶数相乘会出现0，因此只用统计5的个数
        // 5的倍数至少含有1个5
        // 25的倍数至少含有2个5
        // 125的倍数至少含有3个5
        // 每次乘以5，统计1次5的个数
        // 时间复杂度O(log_5(N)), 空间复杂度(1)
        public int trailingZeroes(int n) {
            if(n < 5) return 0;

            int result =0, step = 5;
            while(n >= step) {
                result += n / step;
                step *= 5;
            }
            return result;
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int n= 5;
        app.trailingZeroes(n);
        System.out.println(app.trailingZeroes(n));

    }
}
