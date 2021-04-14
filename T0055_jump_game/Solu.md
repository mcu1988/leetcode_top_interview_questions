# leetcode t55
- 数组中跳转
- 给定一个非负的整数数组，数组中元素表示能跳的步数，初始时位于0位置，求是否能够跳到数组最后一个位置
- eg
    - nums = [2,3,1,1,4]，return true

# 思路
- 遍历数组，当前位置i能够跳到的最远位置是 i+nums[i]
- 遍历过程中更新能跳到的最远位置max
- 如果max >= nums.length-1, return true
- 如果i > max， return false
- 时间复杂度O(N), 空间复杂度O(1)