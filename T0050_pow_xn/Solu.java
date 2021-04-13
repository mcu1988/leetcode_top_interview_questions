package T0050_pow_xn;

class Solu {

    static class Solution {

        // 方法1
        // 指数全部转为正数处理，单独判断负数最大值的情况
        // 根据指数正负返回结果
        // 每次让指数间1，循环
        // 时间复杂度O(n), 空间复杂度O(1)
        public double myPow1(double x, int n) {

            // 转为正数处理
            boolean isPos = n >= 0 ? true : false;
            int posN = isPos ? n : -n;

            // n为负数最大值时，先转成正数最大值，循环结束后再乘以一次x
            if(n == Integer.MIN_VALUE) {
                posN = Integer.MAX_VALUE;
            }

            double result = 1;
            while(posN > 0) {
                result *= x;
                posN--;
            }

            // 负数最大值的情况，多乘以一次x
            if(n == Integer.MIN_VALUE) result *= x;

            return isPos ? result : 1 / result;
        }


        // 方法2
        // 指数全部转为正数处理，单独判断负数最大值的情况
        // 根据指数正负返回结果
        // 把指数按位拆分，比如n=5=101(二进制), 3^5=3^4 * 3^1, 每一个位上对应的base是x^i, i是拆分后该位上的二进制值
        // 时间复杂度O(log(n)), 空间复杂度O(1)
        public double myPow(double x, int n) {

            // 转为正数处理
            boolean isPos = n >= 0 ? true : false;
            int posN = isPos ? n : -n;

            // n为负数最大值时，先转成正数最大值，循环结束后再乘以一次x
            if(n == Integer.MIN_VALUE) {
                posN = Integer.MAX_VALUE;
            }

            double base = x, result = 1;
            while(posN > 0) {
                // 如果该位上的值是1，就乘以base
                if((posN & 1) == 1) result *= base;
                // base每次平方
                base = base * base;
                posN >>>= 1;
            }

            // 负数最大值的情况，多乘以一次x
            if(n == Integer.MIN_VALUE) result *= x;

            return isPos ? result : 1 / result;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        System.out.println(app.myPow(-2, 3));

    }
}
