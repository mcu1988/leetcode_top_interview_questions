package T0017_letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.List;

class Solu {
    static class Solution {

        // 方法
        // 时间复杂度O(n^2), 空间复杂度O(1)
        public List<String> letterCombinations(String digits) {

            if (digits == null || digits.length() == 0) return new ArrayList<>();

            String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

            List<String> result = new ArrayList<>();

            process(digits, 0, "", result, map);

            return result;

        }

        // 递归处理
        // index 当前处理到第几个字符
        public void process(String s, int index, String tmp, List<String> result, String[] map) {
            if(index == s.length()) {
                result.add(tmp);
                return;
            }

            // 当前字符的候选集
            String curCharMap = map[s.charAt(index)-'2'];
            // 一个个遍历候选集
            for(int i=0; i<curCharMap.length(); i++) {
                process(s, index+1, tmp+curCharMap.charAt(i), result, map);
            }
        }
    }

    public static void main(String[] args) {
        Solution app = new Solution();
        String s = "23";
        app.letterCombinations(s);
        System.out.println(app.letterCombinations(""));

        char[][] phone = {
                { 'a', 'b', 'c' }, // 2    0
                { 'd', 'e', 'f' }, // 3    1
                { 'g', 'h', 'i' }, // 4    2
                { 'j', 'k', 'l' }, // 5    3
                { 'm', 'n', 'o' }, // 6
                { 'p', 'q', 'r', 's' }, // 7
                { 't', 'u', 'v' },   // 8
                { 'w', 'x', 'y', 'z' }, // 9
        };

        System.out.println(phone.length + " " + phone[0].length);
    }

}
