package T0134_gas_station;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法1
        // 依次把每个位置作为出发点， gas[i] - cost[i] 代表当前位置跳转到下一个位置的剩余
        // 时间复杂度O(N^2), 空间复杂度O(1)
        public int canCompleteCircuit1(int[] gas, int[] cost) {
            if(gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
                return -1;

            int N = gas.length;
            for(int start=0; start<N; start++) { // 依次把每个位置当起始位置
                int restGas = 0;
                for(int cur=start; cur<start+N; cur++) { // 从start位置依次往下走
                    int tmp = cur % N; // 环形的索引重新定向
                    // gas[tmp] - cost[tmp]代表当前位置走到下一个位置的剩余
                    // 再累加上历史剩余
                    restGas += gas[tmp] - cost[tmp];
                    // 历史剩余是负数，start位置行不通
                    if(restGas < 0) break;
                }
                // 走完你一圈后，剩余>=0，返回start
                if(restGas >= 0) return start;
            }
            // 所以位置都失败，返回-1
            return -1;
        }

        // 方法2
        // 依次把每个位置作为出发点， gas[i] - cost[i] 代表当前位置跳转到下一个位置的剩余
        // 如果当前位置出发时gas[i] - cost[i] < 0, 出发位置直接换为当前位置的下一个位置
        // 时间复杂度O(N), 空间复杂度O(1)
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if(gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
                return -1;

            int N = gas.length;

            int start = 0, cur = start;
            while(start < N) {
                cur = start;
                int restGas = 0;
                while(cur < start+N) { // 从start位置依次往下走
                    int tmp = cur % N; // 环形的索引重新定向
                    // gas[tmp] - cost[tmp]代表当前位置走到下一个位置的剩余
                    // 再累加上历史剩余
                    restGas += gas[tmp] - cost[tmp];
                    // 历史剩余是负数，start位置行不通
                    if(restGas < 0) break;
                    cur++;
                }
                // 走完一圈后，剩余gas >= 0, 返回出发位置
                if(restGas >= 0) return start;

                // 否则出发位置更换为当前位置的下一个位置
                start = cur + 1;
            }

            return -1;
        }
    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] gas = {2,3,4};
        int[] cost = {3,4,3};

        app.canCompleteCircuit(gas, cost);
        System.out.println(app.canCompleteCircuit(gas, cost));
    }
}
