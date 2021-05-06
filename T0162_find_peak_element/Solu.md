# leetcode t162
- 找到数组的波峰
- 给定一个整数数组，数组相邻元素不相等，找到nums[i]>nums[i-1] && nus[i] > nums[i+1]的点，返回任意一个索引

# 方法1
- 判断nums[i] > nums[i-1] && nums[i] > nums[i+1]
- 遇到第一个波峰，返回索引
- 时间复杂度O(N), 空间复杂度O(1)

# 方法2
- 判断nums[i] > nums[i-1] && nums[i] > nums[i+1]
- 由于相邻元素不相等，nums[0] > nums[-1], 如果nums[0] > nums[1], return 0, 否则nums[0] < nums[1], 继续遍历1位置
- 只用判断nums[i] > nums[i+1]
- 时间复杂度O(N), 空间复杂度O(1)

# 方法3
- 由于相邻的值不相等，数组必存在有效解，二分判断
- 当nums[mid] < nums[mid-1], 说明左边必须在一个波峰，反之，右边必存在一个波峰
- 判断nums[mid] > nums[mid-1] && nums[i] > nums[mid+1], 返回mid就是答案
- 时间复杂度O(log(N)), 空间复杂度O(1)