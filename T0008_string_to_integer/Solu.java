package T0008_string_to_integer;

class Solu {
    static class Solution {

        // 逐个遍历字符，先去掉空格，再判断符号，再遍历整数
        // 注意提前判断是否溢出
        // 负数的范围比整数大1，需要单独区分
        // 时间复杂度O(n), 空间复杂度O(1)
        public int myAtoi(String s) {

            if(s == null || s.length() == 0) return 0;

            int index = 0;
            // 去掉前导空格
            while(index < s.length() && s.charAt(index) == ' ') index++;
            // 标记正负
            boolean isNeg = false;
            // 去掉前导空格后的第一个位置标记正负
            if(index < s.length() && (s.charAt(index) == '-' || s.charAt(index) == '+')) {
                if(s.charAt(index) == '-') isNeg = true;
                index++;
            }

            int result = 0;
            int max_div_10 = Integer.MAX_VALUE / 10;
            int max_mod_10 = Integer.MAX_VALUE % 10;
            // 遍历整数
            while(index < s.length() && s.charAt(index) >= '0' &&  s.charAt(index) <= '9') {
                // result * 10越界，根据正负提前返回
                // result == max_div_10， 当前位置查过7时，根据正负直接返回
                if(result > max_div_10 || (result == max_div_10 && s.charAt(index)-'0' > max_mod_10)) {
                    result = isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                    break;
                }
                result = result * 10 + s.charAt(index) - '0';
                index++;
            }

            // Integer.MIN_VALUE提前返回
            if(result == Integer.MIN_VALUE) return result;

            return isNeg ? -result : result;
        }

    }

}
