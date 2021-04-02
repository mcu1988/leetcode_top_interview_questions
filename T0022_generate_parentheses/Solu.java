package T0022_generate_parentheses;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 从左往右递归待生成字符串的位置，考虑该位置可以放哪些括号
        // 时间复杂度O(4^n/(sqrt(n)))，与卡特兰数有关空间复杂度O(n)
        public List<String> generateParenthesis(int n) {
            if(n < 1) return new ArrayList<>();

            List<String> result = new ArrayList<>();
            process("", 0, 0, 0, n, result);
            return result;
        }

        public void process(String s, int index, int leftUsed, int rightUsed, int total, List<String> result) {
            if(index == total * 2) {
                result.add(s);
                return;
            }

            // 任何时候都可以添加左括号，只要左括号剩余数量>0
            if(total - leftUsed > 0)
                process(s+'(', index+1, leftUsed+1, rightUsed, total, result);

            // 只有左括号数量大于右括号数量，添加右括号
            if(leftUsed - rightUsed > 0) {
                process(s+')', index+1, leftUsed, rightUsed+1, total, result);
            }
        }

    }

    public static void main(String[] args) {
        Solution app = new Solution();
        app.generateParenthesis(1);
        System.out.println(app.generateParenthesis(3));
    }
}
