package T0005_longest_palindromic_substring;

class Solu {
    static class Solution {

        // 解法1
        // 对原始字符串插空添加特殊字符串, eg"#"
        // 遍历插#后的字符串，记录最长回文子串
        // 时间复杂度O(N^2), 空间复杂度O(N)
        public String longestPalindrome1(String s) {
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
        // Manacher算法
        // 以每个字符为回文中心，遍历字符串
        // 记录最右的回文右边界r和回文中心c
        // 当前遍历到的index的半径分4种情况, 找到index关于回文中心c的对称点index', index'的回文半径是R', 右边界是r',左边界是l'
        // 情况1: index在r外，从index往左右两边扩展，确定半径
        // 情况2: index在r内，l' > l(当前点c的回文左边界)， 那么index的回文半径是R'
        // 情况3: index在r内，l' = l，那么index的回文半径最小是R'，继续判断r+1位置和l-1位置的值是否相等
        // 情况4: index在r内，l' < l，那么index的回文半径是l-index'
        // 时间复杂度O(N), 空间复杂度O(N)
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

            // 当前位置为回文中心的回文半径数组
            int[] pArr = new int[len];

            // c是回文半径，r是回文右边界再右一个位置
            int c = -1, r = -1;
            String result = "";
            int maxR = 0;
            for(int i=0; i<len; i++) {

                // i >= r是情况1
                // i < r是情况2，3，4，情况2是pArr[2*c-i]， 情况3最少是r-i，情况4是r-i
                pArr[i] = i < r ? Math.min(r-i, pArr[2*c-i]) : 1;

                // 情况1，3向左右两边扩展
                while(i+pArr[i] < len && i-pArr[i] > -1) {
                    if(arr[i+pArr[i]] == arr[i-pArr[i]]) {
                        pArr[i]++;
                    } else {
                        // 扩展不出去跳出循环
                        // 情况2，4直接跳出循环
                        break;
                    }
                }

                // 更新回文最右边界和回文中心
                if(i+pArr[i] > r) {
                    r = i + pArr[i];
                    c = i;
                }

                // 更新最长回文子串
                if(pArr[i] > maxR) {
                    maxR = pArr[i];
                    result = s.substring((i-pArr[i]+1) / 2, (i+pArr[i]-1) / 2);
                }

            }

            return result;
        }

    }

    public static void main(String[] args) {
        String s = "aaab";

        Solution app = new Solution();
        System.out.println(app.longestPalindrome(s));

    }
}
