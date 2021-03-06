# leetcode t15
- 给定一个无序数组，数组元素类型为整数，在数组中找到3个不同位置的数字，累加和为0，返回所有的组合
- 重复的组合只返回一次
- 如果没有满足条件的集合，返回空
- eg， 
    - nums = [-1,0,1,2,-1,-4], return [[-1,-1,2],[-1,0,1]]
    - nums = [0], return []
        
# 思路
- 先考虑2个数字累加和为k的情况
    - 思路1，使用hashhmap，即leetcode 1
        - 不要求数组有序
        - 使用hashset记录出现过的所有数字
        - 遍历到当前元素时，查看set中是否存在k - 当前值，并把当前元素加入到set
    - 思路2，首尾双指针
        - 先对数组排序
        - 使用指针l和r分别指向头部和尾部，初始l=0, r=len-1
        - 如果arr[l] + arr[r] < k, 向右移动l
        - 如果arr[l] + arr[r] == k, 向右移动l
        - 如果arr[l] + arr[r] > k, 向左移动r
        - 移动的时候，如果有相同元素，直接跳过
- 再考虑三个数累加和为k的情况
    - 将3个数的情况转化为先固定一个数，在后面的数中寻找累加和为k - 当前数的2个数
    - 先对数组排序
    - 遍历数组，确定3元祖的第一个数字，在后面的数字中找到累加和为k - 当前数 的 2元组
    - 遍历过程中如果遇到相同数字，跳过循环
    - 循环加速
        - 第一个数字如果大于 k / 3，跳过循环
        - 第二个数字如果大于 (k-当前数) / 2，跳过循环
- 时间复杂度O(n^2)，空间复杂度O(1)
