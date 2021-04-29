package T0150_evaluate_reverse_polish_notation;

import java.util.HashMap;
import java.util.Stack;

class Solu {

    static class Solution {

        // 方法
        // 依次遍历数组，遇到数字压栈，遇到运算符出栈2个数字，运算后，压栈
        // 最后栈中只剩一个结果
        // 时间复杂度O(N), 空间复杂度O(1)
        public int evalRPN(String[] tokens) {
            if(tokens == null || tokens.length == 0)
                return 0;

            Stack<Integer> stack = new Stack<>();
            for(String str : tokens) {
                if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                    if(stack.size() < 2) return 0; // 如果栈中元素数量小于2个，表达式不合法
                    int second = stack.pop();
                    int first = stack.pop();

                    if(str.equals("+")) {
                        stack.push(first + second);
                    }
                    if(str.equals("-")) {
                        stack.push(first - second);
                    }
                    if(str.equals("*")) {
                        stack.push(first * second);
                    }
                    if(str.equals("/")) {
                        stack.push(first / second);
                    }
                } else {
                    try {
                        Integer val = Integer.valueOf(str);
                        stack.push(val);
                    } catch (Exception e) {
                        return 0; // 表达式无效，当前字符串不是数字
                    }
                }
            }
            return stack.size() == 1 ? stack.pop() : 0;
        }

    }


    public static void main(String[] args) {
        Solution app = new Solution();

        String[] tokens = {"2","1","+","3","*"};

        app.evalRPN(tokens);

        System.out.println(app.evalRPN(tokens));
    }
}
