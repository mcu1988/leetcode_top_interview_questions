package T0045_jump_game_ii;

class Solu {

    static class Solution {

        // 方法1
        // 第一步能到达一个范围max1，这个范围的最小步数是1，在这个范围内继续跳，能达到的最大步数max2, (max1, max2]之间的步数为2，以此内推
        // 建立一个和nums等长的数组minStep, minStep[i]代表到达i位置的最小步数
        // 遍历nums数组，当i+nums[i] > max时，更新minStep[max+1...new_max]
        // 当max >= nums.length-1时，返回minStep[nums.length-1]
        // 时间复杂度O(N)，空间复杂度O(N)
        public int jump1(int[] nums) {
            if(nums == null || nums.length == 0) return -1;

            // 建立一个与nums等长的数组，minStep[i]表示到达i的最小步数
            int[] minStep = new int[nums.length];
            minStep[0] = 0;
            int max = 0;
            for(int i=0; i<nums.length; i++) {
                if(i > max) return -1; // 无法到达，返回-1
                if(max >= nums.length - 1) return minStep[nums.length-1]; // 已经到达，返回minStep[nums.length-1]
                if(i + nums[i] > max) { // 最大值更新
                    // max...new_max之间填充为minStep[i] + 1
                    for(int k=max+1; k<=i+nums[i] && k<nums.length; k++) minStep[k] = minStep[i] + 1;
                    // 更新max
                    max = i + nums[i];
                }
            }
            // 无法到达返回-1
            return -1;
        }

        // 方法2
        // 第一步能到达一个范围max1，这个范围的最小步数是1，在这个范围内继续跳，能达到的最大步数max2, (max1, max2]之间的步数为2，以此内推
        // 用变量记录当前的步数curStep以及能跳到的最大位置curStepReachMax，当i>curStepReachMax时，step+1, 更新curStepReachMax
        // 时间复杂度O(N)，空间复杂度O(1)
        public int jump(int[] nums) {
            if (nums == null || nums.length < 2) return 0;

            // max代表能跳的最远的位置，curStep表示当前的步数
            // curStepReachMax表示当前步数能到达的最大位置
            int max = 0, curStep = 0, curStepReachMax = 0;
            for(int i=0; i<nums.length; i++) {
                if(i > max) return  -1;
                if(max >= nums.length - 1) return curStep + 1; // max >= 最末位置时，返回curStep+1
                if(i == curStepReachMax+1) { // i超过了curStepReachMax
                    // curStep+1, 更新curStepReachMax为max
                    curStep++;
                    curStepReachMax = max;
                }
                // 更新max
                max = Math.max(max, i+nums[i]);
            }
            return -1;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[] nums = {7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};

        app.jump(nums);

        System.out.println(app.jump(nums));

    }
}
