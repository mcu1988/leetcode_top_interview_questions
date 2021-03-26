package T0005_longest_palindromic_substring;

class Solu {
    static class Solution {

        // 解法1
        // 对原始字符串插空添加特殊字符串, eg"#"
        // 遍历插#后的字符串，记录最长回文子串
        // 时间复杂度O(N^2), 空间复杂度O(1)
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) return "";

            // 原始字符串插入特殊字符#，长度是 2 * len + 1
            char[] arr = new char[s.length() * 2 + 1];
            int len = arr.length;
            // 原始字符串插入特殊字符， "aa" -> "#a#a#"
            arr[0] = '#';
            for(int i=0; i<s.length(); i++) {
                arr[2*i+1] = s.charAt(i);
                arr[2*i+2] = '#';
            }

            // 回文区间是[start, end]
            int start = 0, end = 0;
            int maxLen = 0;
            String result = "";
            for(int i=1; i<len-1; i++) { // 插#后的字符串第一个元素和最后一个元素#不用遍历
                // 从当前位置i往左右两边扩展
                start = i;
                end = i;
                while(start >=0 && end < len && arr[start] == arr[end]) {
                    start--;
                    end++;
                }
                // 推出while循环时，start和end刚好在回文区间外的第一个位置
                start++;
                end--;
                if(end - start + 1 > maxLen) {
                    maxLen = end - start + 1;
                    // 从原始字符串中取回文串, [start/2, end/2)
                    result = s.substring(start/2, end/2);
                }
            }
            return result;
        }


        // 解法2

    }

    public static void main(String[] args) {
        String s = "baa";

        Solution app = new Solution();
        System.out.println(app.longestPalindrome(s));

    }
}
