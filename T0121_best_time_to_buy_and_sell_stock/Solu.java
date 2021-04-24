package T0121_best_time_to_buy_and_sell_stock;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 遍历过程中记录0...当前节点的最小值min
        // 考虑当前位置最多能卖多少钱，当前值-min就是当前值最多卖的钱数
        // 时间复杂度O(N), 空间复杂度O(1）
        public int maxProfit(int[] prices) {

            if(prices == null || prices.length == 0) return 0;

            // min初始值为第0个元素
            int min = prices[0], result = 0;
            for(int i=0; i<prices.length; i++) {
                // 不断更新0...当前位置的最小值
                min = Math.min(min, prices[i]);
                // 当前位置能卖的最大的钱数是 prices[i] - min
                result = Math.max(result, prices[i] - min);
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] prices = {7,1,5,3,6,4};

        System.out.println(app.maxProfit(prices));
    }
}
