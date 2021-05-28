package T0371_sum_of_two_integers;

class Solu {

    static class Solution {

        // 方法1
        // 使用位运算
        // a ^ b是 不带进位的结果
        // (a & b) << 1是进位结果
        // 不带进位的结果再和进位结果相加
        // 循环，直到进位变成0
        public int getSum(int a, int b) {

            int jinwei = (a & b) << 1;
            int yihuo = a ^ b;
            int result = yihuo;

            while(jinwei != 0) {
                result = yihuo ^ jinwei;
                jinwei = (yihuo & jinwei) << 1;
                yihuo = result;
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int a = -2, b = -3;
        app.getSum(a, b);
        System.out.println(app.getSum(a, b));

    }
}
