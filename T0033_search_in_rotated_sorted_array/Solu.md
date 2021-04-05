# leetcode t33
- 在旋转有序数组中查找目标值，返回下标，不存在返回-1
- 数组中不包含你重复元素
- eg， 
    - nums = [4,5,6,7,0,1,2], target = 0, return 4
    - nums = [5,2], target = 2, return 1
# 思路
- 使用二分
- 确定mid值的左边部分还是右边部分有序
- 如果mid左边有序
     - target落在左边范围内，r = mid - 1
     - 否则 l = mid + 1
- 如果mid右边有序
     - target落在右边范围内，l = mid + 1
     - 否则 r = mid - 1     
- 时间复杂度，O(log(n))，空间复杂度O(1)