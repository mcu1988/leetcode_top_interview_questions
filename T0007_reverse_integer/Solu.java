package T0007_reverse_integer;

class Solu {
    static class Solution {

        public int reverse(int x) {

            // 判断正负, 转为正数处理
            // 每次取尾数
            // 提前判断 result * 10是否越界
            // 时间复杂度O(log10(n)), 空间复杂度O(1)
            boolean isNegative = x < 0 ? true : false;
            // 转为整数处理
            x = isNegative ? -x : x;

            int max_div_10 = Integer.MAX_VALUE / 10;
            int yushu = 0;
            int result= 0;
            while(x > 0) {
                // 每次堆10取模，对10取余
                yushu = x % 10;
                x /= 10;
                if(result > max_div_10 ) {
                    // 如果 result * 10 会溢出，提前返回0
                    result = 0;
                    break;
                }
                result = result * 10 + yushu;
            }

            return isNegative ? -result : result;
        }

    }

}
