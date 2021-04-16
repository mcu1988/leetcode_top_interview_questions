# leetcode t45
- 本题不是高频题，是T55的升级版
- 数组中跳转
- 给定一个非负的整数数组，数组中元素表示能跳的步数，初始时位于0位置，求跳到数组最后一个位置的最小步数
- 给定数组一定能到达最后位置
- eg
    - nums = [2,3,1,1,4]，return 2

# 思路1
- 第一步能到达一个范围max1，这个范围的最小步数是1，在这个范围内继续跳，能达到的最大步数max2, (max1, max2]之间的步数为2，以此内推
- 建立一个和nums等长的数组minStep, minStep[i]代表到达i位置的最小步数
- 遍历nums数组，当i+nums[i] > max时，更新minStep[max+1...new_max]
- 当max >= nums.length-1时，返回minStep[nums.length-1]
- 时间复杂度O(N)，空间复杂度O(N)

# 思路2
- 第一步能到达一个范围max1，这个范围的最小步数是1，在这个范围内继续跳，能达到的最大步数max2, (max1, max2]之间的步数为2，以此内推
- 思路和方法1一致，只是方法1中的minStep数组取值是一段1，一段2，...的形式，因此只用记录临界点即可
- 用变量记录当前的步数curStep以及能跳到的最大位置curStepReachMax，当i>curStepReachMax时，step+1, 更新curStepReachMax
- 时间复杂度O(N)，空间复杂度O(1)