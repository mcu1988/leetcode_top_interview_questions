# leetcode t347
- 取数组中词频最大的k个元素
- 输出顺序没有要求
- 输出的答案是unique的，没有词频相等导致输出结果多样的情况
- eg,
    - nums = [1,1,1,2,2,3], k = 2, return [1,2]
    
    
# 方法1    
- 使用HashMap统计词频
- 使用大小为k的最小堆，获得前k个词频大的词
- 时间复杂度O(N*log(K)), 空间复杂度O(N)

# 方法2
- 使用HashMap统计词频
- 使用长度为N的List<List>，把词频当索引，把单词放在对应的位置，实现词频的排序
- 通词频的单词放入一个list内
- 逆序读数组，取词频最大的k个值
- 时间复杂度O(N), 空间复杂度O(N)