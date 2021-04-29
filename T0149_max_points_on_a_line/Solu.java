package T0149_max_points_on_a_line;

import java.util.HashMap;

class Solu {

    static class Solution {

        // 方法1
        // 2点确定一条直线，然后看剩下的点在不在直线上
        // 使用i, j索引确定一条直线，j=i+1...，看以k为索引的点在不在直线上， k = j+1...
        // 时间复杂度O(N^3), 空间复杂度O(1)
        public int maxPoints1(int[][] points) {
            if(points == null || points.length < 3)
                return points.length;

            int N = points.length;
            int result = 0, tmp = 2;
            // i从0开始遍历，j从i+1开始遍历，点i,j确定一条直线
            // k从j+1开始遍历，统计点k落在点i,j确定的直线上
            for(int i=0; i<N-2; i++) {
                int sameWithI = 0; // 统计在遇到和i点不一样的j点之前的与i点重复的点的数量
                for(int j=i+1; j<N-1; j++) {
                    tmp = 2; // 点i,j已经是两个点了
                    int deltaX = points[j][0] - points[i][0];
                    int deltaY = points[j][1] - points[i][1];
                    if(deltaX == 0 && deltaY == 0) { // 点i和点j重复，不能确定一条直线，跳过，直到找到一个和i不同的点j
                        sameWithI++;
                        continue;
                    }
                    for(int k=j+1; k<N; k++) {
                        // (y3 - y1) / (x3 - x1) = (y2 - y1) / (x2 - x1), 除法转换为乘法，避免除以0的问题
                        // 兼容如果i, j两点确定的直线与x轴和y轴平行的情况
                        // 兼容k点和i点和j点重复的情况
                        // 如果点i和点j重复，在上一步直接跳过
                        if(deltaY * (points[k][0] - points[i][0]) == deltaX * (points[k][1] - points[i][1]))
                            tmp++;
                    }
                    result = Math.max(result, tmp + sameWithI);
                }
            }
            return result;
        }


        // 思路2
        // 确定3点共线的方法：斜率相同，并且经过同一个点
        // 先确定点i，考虑i+1...的点与i点构成的直线的斜率，使用HashMap存储斜率，最后看经过点i且斜率相同的点的数量
        // HashMap存储斜率的方法， deltaX, deltaY都是整数，先约分，最后使用64位的数字存储，deltaY<< 32 | deltaX
        // 如果后面的点和点i重复， deltaX=0， deltaY=0，无法约分，使用变量记录重复的点
        // 时间复杂度O(N^2), 空间复杂度O(N)
        public int maxPoints(int[][] points) {
            if (points == null || points.length < 3)
                return points.length;

            int N = points.length;
            // 存储斜率 -> 计数的映射
            HashMap<Long, Integer> map = new HashMap<>();

            int result = 0;
            for(int i=0; i<N-1; i++) { // 统计与i点共线的数量
                map.clear(); // 每次统计完，清空map
                int samePoint = 0; // 统计和i点重复的点的数量
                for (int j = i + 1; j < N; j++) {
                    long deltaX = points[j][0] - points[i][0];
                    long deltaY = points[j][1] - points[i][1];
                    if(deltaX == 0 && deltaY == 0) { // 和i点重复
                        samePoint++;
                        continue;
                    }

                    // 找公约数，时间复杂度O(log(32))
                    int yueshu = maxGongyueshu((int)deltaX, (int)deltaY);
                    // 约分后使用一个64位的key存储
                    // 兼容与x轴和y轴平行的点的情况
                    long key = (deltaY / yueshu) << 32 | (deltaX / yueshu);
                    int cnt= map.getOrDefault(key, 0) + 1;
                    map.put(key, cnt);
                    result = Math.max(result, cnt+1+samePoint);
                }
            }
            return result;
        }

        // 求公约数，时间复杂度O(32)
        // 当a或者b有1个等于0时，返回您改一个不是0的数字作为公约数
        public int maxGongyueshu(int a, int b) {
            return b == 0 ? a : maxGongyueshu(b, a%b);
        }
    }


    public static void main(String[] args) {
        Solution app = new Solution();

        int[][] points = {{1,1},{2,2},{3,3}};

        app.maxPoints(points);


    }
}
