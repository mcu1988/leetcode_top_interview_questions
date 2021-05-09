package T0191_number_of_1_bits;

class Solu {

    static class Solution {

        // 方法1
        // 逐个统计每个位上是否是1
        // 时间复杂度O(32), 空间复杂度O(1)
        public int hammingWeight1(int n) {

            int result = 0;

            // n每次右移1位，查看该位置是否是1
            while(n != 0) {
                if((n & 1) == 1) result += 1;
                n >>>= 1;
            }
            return result;
        }

        // 方法2
        // n & (n - 1)消去最低位上的1
        // 时间复杂度O(32), 空间复杂度O(1)
        public int hammingWeight(int n) {

            int result = 0;
            // n & (n - 1)每次消去最低位置的1
            while(n != 0) {
                result += 1;
                n = n & (n - 1);
            }
            return result;
        }

        public void printBinary(int num){
            for(int i=31;i>=0;i--){
                System.out.print((num & (1 << i)) == 0 ? "0":"1");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int n = 3;
        app.printBinary(n);
        System.out.println(app.hammingWeight(n));

    }
}
