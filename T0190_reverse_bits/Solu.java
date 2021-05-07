package T0190_reverse_bits;

class Solu {

    static class Solution {

        // 方法1
        // 逐个位置互换值
        // 时间复杂度O(16), 空间复杂度(1)
        public int reverseBits1(int n) {

            for(int i=0,j=31; i<j; i++,j--) {
                int tmp = n & (1 << j); // 保留j位置的值

                // j位置先置0，再或上i位置的值
                n = (n & (0xffffffff ^ (1 << j)) ) | ((n & (1 << i)) << (j - i));
                // i位置先置0，再或上j位置的值
                n = (n & (0xffffffff ^ (1 << i)) ) | ( (tmp != 0 ? 1 : 0) << i);
            }
            return n;
        }

        public void printBinary(int num){
            for(int i=31;i>=0;i--){
                System.out.print((num & (1 << i)) == 0 ? "0":"1");
            }
            System.out.println();
        }


        // 方法2
        // 重新生成reverse后的数字
        // 时间复杂度O(32), 空间复杂度(1)
        public int reverseBits2(int n) {

            int result = 0;
            for (int i = 0; i<32; i++) {
                result = (result << 1) | (n & 1);
                n >>= 1;
            }
            return result;
        }

        // 方法3
        // 先交换左16位和右16位
        // 再在16位内部交换左边8位和右边8位
        // 再在8位内部交换左边4位和右边4位
        // 再在4位内部交换左边2位和右边2位
        // 再在2位内部交换左边1位和右边1位
        // 交换借宿后，完成了对称互换
        // 时间复杂度O(5), 空间复杂度O(1)
        public int reverseBits(int n) {

            n = (n >>> 16) | (n << 16);
            n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
            n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
            n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
            n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
            return n;
        }
    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int n = 43261596;
        app.printBinary(n);
        app.printBinary(app.reverseBits(n));

    }
}
