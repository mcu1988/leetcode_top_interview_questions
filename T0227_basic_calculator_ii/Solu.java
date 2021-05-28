package T0227_basic_calculator_ii;


import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 先计算乘除，把表达式划分为加减法
        // 对于减法，转换为加法运算
        // 预先读取一个操作符和数字，根据操作符确定是否优先计算乘除法
        // 在原表达式前面添加一个 0 + 原表达式
        // 使用变量part和sum，part表示当前步的待操作的部分，初始时part=0, op='+'
        // 根据符号做变换
        // 1. 遇到 +，把part累加到sum，更新part
        // 2. 遇到 -, 把part累加到sum，更新part为负数
        // 3. 遇到 *, 先计算乘法，更新part
        // 4. 遇到 /, 先计算除法，更新part
        // 时间复杂度O(N), 空间复杂度O(1)
        public int calculate(String s) {
            if(s == null || s.length() == 0) return -1;

            int sum = 0, part = 0;
            char op = '+';
            int index = 0, N = s.length();
            while(index < N) {

                // 跳过空格
                while(index < N && s.charAt(index) == ' ') index++;

                // 读取数字
                int num = 0;
                while(index < N && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                    num = num * 10 + (s.charAt(index) - '0');
                    index++;
                }

                if(op == '+') {
                    sum += part;
                    part = num;
                } else if(op == '-') {
                    sum += part;
                    part = -num; // 负号的时候，把减法转化为加法，part变为负数
                } else if(op == '*') {
                    part *= num;
                } else if(op == '/') {
                    part /= num;
                }

                // 跳过空格
                while(index < N && s.charAt(index) == ' ') index++;

                // 表达式结束，后面没有运算符了
                if(index == N) break;

                // 读取运算符
                op = s.charAt(index++);

                // 跳过空格
                while(index < N && s.charAt(index) == ' ') index++;
            }

            // 结尾时，把最后的part累加到sum
            return sum + part;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String s = "1+3 + 2*6 / 3 - 2";
        app.calculate(s);
    }
}
