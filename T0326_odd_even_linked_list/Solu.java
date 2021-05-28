package T0326_odd_even_linked_list;

class Solu {

    static class Solution {

        // 方法1
        // 3^x > 0, 如果 n<=0, return false
        // n=1，返回true
        // n > 1时，依次除以3，如果能整除，并且最后结果是1，return true
        // 时间复杂度O(log3(N)), 空间复杂度O(1)
        public boolean isPowerOfThree1(int n) {
            if(n <= 0) return false;

            if(n == 1) return true;

            while(true) {
                if(n % 3 == 0) {
                    n /= 3;
                    if(n == 1) return true;
                } else return false;
            }
        }

        // 方法2
        // 1162261467是int型范围内，最大的3的幂，它是3的19次方
        // 如果1162261467能被某个数字整除，return true
        // 时间复杂度O(1), 空间复杂度O(1)
        public boolean isPowerOfThree(int n) {
            return (n > 0) && (1162261467 % n == 0);
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int n = 1;

        app.isPowerOfThree(n);
        System.out.println(app.isPowerOfThree(n));

    }
}
