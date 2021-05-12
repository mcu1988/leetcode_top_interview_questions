# leetcode t215
- 在数组中寻找第k大的数
- eg,
    
# 方法1
- 使用一个大小为k的最小堆
- 初始加入k个元素到堆
- 再遍历剩下的元素，当元素大于堆顶元素时，弹出堆顶元素，加入当前元素
- 时间复杂度O(N*log(k)), 空间复杂度O(k)
- java实现耗时3ms

# 方法2
- 使用快排partition过程，随机选取pivot，换分为 <pivot、=pivot、>pivot的区域
- 把第k大转换为第nums.length-k+1小
- 平均时间复杂度O(N), 使用master公式计算,和partition过程的pivot选取有关，空间复杂度O(N)
- 使用最后一个元素作为pivot，java实现耗时18ms
- 随机选取一个元素作为pivot，java实现耗时2ms
