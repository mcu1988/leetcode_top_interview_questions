package T0020_valid_parentheses;

import java.util.Stack;

class Solu {

    static class Solution {

        // 方法
        // 使用栈存储之前的字符
        // 如果是左括号，入栈对应的右括号
        // 如果是有括号，出栈，查看是否匹配
        // 时间复杂度O(n), 空间复杂度O(n)
        public boolean isValid(String s) {
            if(s == null || s.length() == 0) return true;

            int len = s.length();
            Stack<Character> stack = new Stack<>();
            for(int i=0; i<len; i++) {
                // 左括号入栈对应的右括号
                if (s.charAt(i) == '(') stack.push(')');
                else if (s.charAt(i) == '[') stack.push(']');
                else if (s.charAt(i) == '{') stack.push('}');
                else {
                    // 右括号，出栈，查看是否匹配
                    // 出栈之前如果栈时空，匹配失败
                    if (stack.isEmpty() || stack.pop() != s.charAt(i)) return false;
                }
            }

            // 最后如果栈是空，就匹配成功
            return stack.isEmpty();
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        System.out.println(app.isValid("([)]"));

    }


}
