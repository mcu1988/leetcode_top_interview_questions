package T0202_happy_number;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solu {

    static class Solution {

        // 方法1
        // 使用set记录中间产生的sum，如果sum重复，返回false
        // sum =1, 返回true
        // 任意32位正数，最多循环20次，就会有结果
        // 时间复杂度O(1), 空间复杂度O(1)
        public boolean isHappy1(int n) {
            if(n < 1) return false;

            int sum = 0;
            HashSet<Integer> set = new HashSet<>();

            while(sum != 1) {
               sum = sum(n);
               if(set.contains(sum)) return false;
               set.add(sum);
               n = sum;
            }
            return true;
        }

        // 计算数字按位拆分的平方和
        public int sum(int n) {
            int sum = 0;
            while(n != 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            return sum;
        }

        // 方法2
        // 查看sum是否重复，和链表是否有环类似
        // 使用快慢指针，指针相等时，代表有环
        // 时间复杂度O(1), 空间复杂度O(1)
        public boolean isHappy2(int n) {
            if(n < 1) return false;

            // 快慢指针，两指针相等时，代表右、有重复值
            int slow = n, fast = n;
            while(slow != 1 && fast != 1) {
               slow = sum(slow);
               fast = sum(sum(fast));
               if(fast == 1) return true;
               if(slow == fast) return false;
            }
            return true;
        }

        // 方法3
        // 任意数字最多经过4次变换，sum 就会 <= 100
        // 因此只用先统计100以内的快乐数，把元素自变换到100以内，查表
        // 时间复杂度O(1), 空间复杂度O(1)
        public boolean isHappy(int n) {
            if (n < 1) return false;

            Integer[] nums = {1,7,10,13,19,23,28,31,32,44,49,68,70,79,82,86,91,94,97,100};
            Set<Integer> set = new HashSet<> (Arrays.asList(nums));

            while(n > 100) {
                n = sum(n);
            }
            return set.contains(n);
        }

    }


    public static void main(String[] args) {

        Solution app = new Solution();

        int n = 19;

        System.out.println(app.isHappy(n));

    }
}
