# leetcode t88
- 合并2个有序数组
- 给定2个有序数组，以及对应的长度，合并2个数组，生成的结果放第一个数组中
- 第一个数组的长度是两数组长度之和
- eg
    - nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3，return nums1 = [1,2,2,3,5,6]

# 思路
- 使用2个索引，从后往前遍历2个数组
- 填充nums1时也是从后往前填充
- 时间复杂度O(m+n), 空间复杂度O(1)