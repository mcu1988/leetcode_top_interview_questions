package T0125_valid_palindrome;

class Solu {

    static class Solution {

        // 方法
        // 使用左右2个指针遍历
        // 跳过非字母的字符比较
        // 时间复杂度O(N), 空间复杂度O(1)
        public boolean isPalindrome(String s) {
            if(s == null || s.length() < 2) return true;

            int l = 0, r = s.length()-1;
            while(l < r) {
                // 跳过非字母的
                while(l < r && !isAlphanumeric(s.charAt(l))) l++;
                while(l < r && !isAlphanumeric(s.charAt(r))) r--;

                if(!equal(s.charAt(l++), s.charAt(r--))) return false;
            }
            return true;
        }

        // 判断是否字母和数字
        public boolean isAlphanumeric(char a) {
            return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z') || (a >= '0' && a <= '9');
        }

        // 判断两字母是否相等，忽略大小写
        public boolean equal(char a, char b) {
            return a == b ||
                    (
                        ((a < '0' || a > '9') && (b < '0' || b > '9'))
                        && (a+32 == b || a-32 == b)
                    );
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

//        String s = "0P";
        String s = "A man, a plan, a canal: Panama";


        System.out.println(app.isPalindrome(s));
    }
}
