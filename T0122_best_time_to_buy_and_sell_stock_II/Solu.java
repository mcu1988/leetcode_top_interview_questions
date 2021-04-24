package T0122_best_time_to_buy_and_sell_stock_II;

class Solu {

    static class Solution {

        // 方法
        // 只要今天的价格比昨天的价格高，就把两天的差价累加到结果上
        // 时间复杂度O(N), 空间复杂度O(1）
        public int maxProfit(int[] prices) {

            if(prices == null || prices.length < 2) return 0;

            int result = 0;
            for(int i=1; i<prices.length; i++) {
                if(prices[i] > prices[i-1]) {
                    result += prices[i] - prices[i-1];
                }
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
