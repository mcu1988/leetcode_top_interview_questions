package T0029_divide_two_integers;

class Solu {

    static class Solution {

        // 方法1
        // 使用减法实现除法
        // 时间复杂度，O(n / m)，空间复杂度O(1)
        public int divide1(int dividend, int divisor) {
            if(divisor == 0) return Integer.MAX_VALUE;

            int result = 0;
            // 转为负数处理，因为int的负数范围比正数范围多一个
            int dividendP = dividend < 0 ? dividend : getOpposite(dividend);
            int divisorP = divisor < 0 ? divisor : getOpposite(divisor);

            if(dividend == Integer.MIN_VALUE) {
                // 当 Integer.MIN_VALUE / 1时，返回Integer.MIN_VALUE
                // 当 Integer.MIN_VALUE / -1时，因为返回值是int类型，不可能返回2147483648，返回2147483647
                // 这两种情况如果放在while中处理会导致result溢出
                if(divisor == 1) return Integer.MIN_VALUE;
                if(divisor == -1) return Integer.MAX_VALUE;
            }

            while(dividendP <= divisorP) {
                result++;
                dividendP -= divisorP;
            }

            // 根据正负，返回结果
            return ((dividend > 0 && divisor > 0)  || (dividend < 0 && divisor < 0)) ? result : getOpposite(result);
        }


        // 方法2
        // 使用位运算实现加减乘除
        // 时间复杂度，O(32)，空间复杂度O(1)
        public int divide(int dividend, int divisor) {
            if (divisor == 0) return Integer.MAX_VALUE;

            if(divisor == Integer.MIN_VALUE) {
                if(dividend == Integer.MIN_VALUE) return 1;
                else return 0;
            }

            if(dividend == Integer.MIN_VALUE) {
                // 题目规定
                if(divisor == -1) return Integer.MAX_VALUE;

                // int最小值先+1，再转为正数，计算得到结果
                int result = divide(add(dividend, 1), divisor);
                // 再用余数继续除以divisor, 两部分的商相加
                return result + divide(subtract(dividend, multiple(result, divisor)), divisor);
            }

            return div(dividend, divisor);
        }

        // 位运算实现加法
        public int  add(int a, int b) {
            int result = a;
            // b是进位，循环操作，直到进位是0
            while (b != 0) {
                // a和b异或的结果是a和b相加后不考虑进位的情况
                result = a ^ b;
                // a和b按位与，再左移一位就是进位
                b = (a & b) << 1;
                a = result;
            }
            return result;
        }

        // 位运算实现减法
        // a - b = a + (-b)
        public int subtract(int a, int b) {
            return add(a, getOpposite(b));
        }

        // 计算一个数的相反数，安慰取反+1
        // 使用位运算
        public int getOpposite(int a) {
            return add(~a,1);
        }

        // 位运算实现乘法
        // 把乘法拆解为如果位上的加法运算
        public int multiple(int a, int b) {
            int  result = 0;

            // b每次向右移动一位
            while(b != 0) {
                // b的最低位不是0，就相加
                if((b & 1) == 1) {
                    result = add(result, a);
                }
                // a左移
                a <<= 1;
                // b向右移动一位, 最高位使用0补齐
                b >>>= 1;
            }
            return result;
        }

        // 位运算实现除法
        // 负数的除法全部转为正数计算
        public int div(int a, int b) {
            // 转为正数处理
            int aP = a < 0 ? getOpposite(a) : a;
            int bP = b < 0 ? getOpposite(b) : b;

            int result = 0;
            for(int i=31; i>=0; i=subtract(i, 1)) {
                // 为什么使用aP >> i, 而不是 bP << i， 因为bp << i时，符号位可能变为负
                if((aP >> i) >= bP) {
                    result |= (1 << i);
                    aP  = subtract(aP, bP << i);
                }
            }

            // 根据a，b的正负返回结果
            return ((a < 0 && b > 0) || (a > 0 && b < 0)) ? getOpposite(result) : result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int dividend = Integer.MIN_VALUE;
        int divisor = -2;
        System.out.println(app.divide(dividend, divisor));


    }
}
