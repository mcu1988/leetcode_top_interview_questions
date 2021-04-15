# leetcode t56
- 合并多个区间
- 给定一个二维数组，表示多个区间，返回合并后的区间
- eg
    - intervals = [[1,3],[2,6],[8,10],[15,18]]，return [[1,6],[8,10],[15,18]]

# 思路
- 按照数组的start排序
- 遍历数组，比较当前区间end和待结算区间的preEnd
    - 如果end > preEnd, 把待结算的区间加入结果集
    - 如果end <= preEnd, 更新end，end = max(end, preEnd), eg, [1,4],[2,3]
- 时间复杂度O(N*log(N)), 空间复杂度O(1)