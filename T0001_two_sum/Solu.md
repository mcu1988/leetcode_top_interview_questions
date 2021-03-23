# leetcode t1
- 数组中2个数字和问题
- 给定一个整数数组nums和一个整数目标值target，返回数组中2元素相加和为target的index
- 每个数组只有1组答案，每个位置的元素只能使用一次
- eg， 
    - nums = [2,7,11,15], target = 9，return [0, 1]
    - nums = [3,3], target = 6，return [0,1]
    
# 思路
- 使用Map存储已经遍历的数值 -> 索引
- 对于当前值
    - 先查找target - nums[cur] 是否在map中，有则返回
    - 否则map中加入当前值
- 时间复杂度O(N), 空间复杂度O(N)