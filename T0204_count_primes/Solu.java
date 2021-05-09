package T0204_count_primes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solu {

    static class Solution {

        // 方法1
        // Sieve of Eratosthenes, 参考https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
        // 假设n是质数，那么n的倍数不是质数，可以清除
        // 从1遍历到sqrt(n)，使用数组标记每个数字是否是质数
        // 时间复杂度O(n*log(log(n))), 空间复杂度O(n)
        public int countPrimes1(int n) {
            if(n < 3) return 0;

            boolean[] dp = new boolean[n];
            dp[0] = true;
            dp[1] = true;

            int sqrtN = (int) Math.sqrt(n);
            for(int i=2; i<=sqrtN; i++) {
                if(!dp[i]) {
                    for(int j=2*i; j<n; j+=i) {
                        dp[j] = true;
                    }
                }
            }

            int result = 0;
            for(boolean v : dp) {
                if(!v) result += 1;
            }
            return result;
        }

        // 方法2
        // 思路和方法1一致
        // 倍数筛选时，从i^2开始，每次增加2i, 减少筛选次数
        // i从3开始遍历，每次增加2
        // 时间复杂度O(n*log(log(n))), 空间复杂度O(n)
        public int countPrimes(int n) {
            if(n < 3) return 0;

            boolean[] dp = new boolean[n];
            dp[0] = true;
            dp[1] = true;

            int sqrtN = (int) Math.sqrt(n);
            int result = 1;
            for(int i=3; i<n; i+=2) { // 从3开始遍历，使用一个遍历，完成筛选和统计
                if(!dp[i]) {
                    result += 1;
                    if(i > sqrtN) continue;

                    for(int j=i*i; j<n; j+=2*i) { // 从i^2开始筛选
                        dp[j] = true;
                    }
                }
            }

            return result;
        }

    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int n = 19;

        System.out.println(app.countPrimes(n));

    }
}
