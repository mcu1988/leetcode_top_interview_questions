package T0279_perfect_squares;

import java.util.*;

class Solu {

    static class Solution {


        // 方法1
        // 使用dp数组，dp[i]表示i的答案，依次记录好1...n的dp
        // dp[n]的值是 min(dp[n-1], dp[n-4], dp[n-9], ... ) + 1
        // 时间复杂度O(N*sqrt(N)), 空间复杂度O(N)
        public int numSquares1(int n) {

            // dp[i]表示i的答案
            int[] dp = new int[n+1];
            // 初始时dp[1]=1
            dp[1] = 1;
            for(int i=2; i<=n; i++) { // 循环填充每个i的结果
                dp[i] = i; // 初始时dp[i]总是可以由i个1累加得来
                for(int j=1; j*j <= i; j++) // 依次尝试 i - j^2
                    dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
            return dp[n];
        }

        // 方法2
        // 把n当做根节点，分解成多叉树，节点是 n-i^2, 求解到达0的最短高度即可
        // 如果某个节点之前到达过，那么之前的方案一定是更优的，当前节点不用再加入到下一层中
        // 时间复杂度O(N*sqrt(N)), 空间复杂度O(N*sqrt(N))，最多N层，每层sqrt(N)个节点
        public int numSquares2(int n) {

            // 用于多叉树按层遍历
            Queue<Integer> queue = new LinkedList<>();
            queue.add(n);
            // 每个节点是否访问过
            boolean[] isVisited = new boolean[n + 1];

            int level = 0; // 记录层数
            while (!queue.isEmpty()) {
                level++;

                int curLevelSize = queue.size();
                for(int size=0; size<curLevelSize; size++) {
                    int cur = queue.poll();

                    // 遍历每层的节点
                    for (int i = 1; i*i<=cur; ++i) {
                        int x = cur - i * i;
                        if (x == 0) { // 当前层已经到达0，返回当前层数，就是结果
                            return level;
                        }

                        // 当前节点如果已经访问过，那么之前到达过的时候，已经是比当前更优的方案，因此，不用再考虑当前方案
                        if (!isVisited[x]) {
                            queue.add(x);
                            isVisited[x] = true;
                        }
                    }
                }
            }
            return -1;
        }

        // 方法3
        // 数学解法
        // 拉格朗日四平方和定理：https://zh.wikipedia.org/wiki/%E5%9B%9B%E5%B9%B3%E6%96%B9%E5%92%8C%E5%AE%9A%E7%90%86
        // 每个正整数都可以表示为4个整数的平方和
        // 勒让德3平方和定理：https://en.wikipedia.org/wiki/Legendre%27s_three-square_theorem
        // 当 n != 4^a*(8b+7)时，n可以表示成3个整数的平方和
        // 再判断n是否是一个数的平方，或者枚举判断n是否是2个数的平方
        // 时间复杂度O(sqrt(N)), 空间复杂度O(1)
        public int numSquares(int n) {

            // n可以表示为某个数的平方
            if(isSquare(n)) return 1;

            // 枚举判断是否可以表示为2个数的平方
            for(int i=1; i*i<n; i++) {
                if(isSquare(n-i*i)) return 2;
            }

            // 勒让德三平方和定理
            while(n%4 == 0) n /= 4;
            if(n % 8 != 7) return 3;

            // 4平方和定理，其他3种判断后，都不是，返回4
            return 4;
        }

        public boolean isSquare(int n) {
            int a = (int)Math.sqrt(n);
            return a * a == n;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int n = 12;

        app.numSquares(n);
        System.out.println(app.numSquares(n));
    }
}
