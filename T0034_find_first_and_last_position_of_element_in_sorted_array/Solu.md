# leetcode t34
- 包含重复值的有序数组中，找到target的起始下标位置，如果不存在，返回{-1, -1}
- eg， 
    - nums = [5,7,7,8,8,10], target = 8, return [3,4]
    
# 思路1
- 使用二分找到一个target，然后从target往左右扩展能找到所有target    
- 时间复杂度O(log(n))，最坏情况下O(n), 空间复杂度O(1)

# 思路2
- 设计一个递归函数f，返回<=target的最后一个元素的位置
    - 如果target存在，返回target的最后一个位置
    - 如果target不存在，返回<target的最后一个位置
- 调用f(nusm, target)，返回target的最右一个位置end
- 调用f(nums, target-1)，返回target-1的最后一个位置start
- 如果start = end，说明target在数组中不存在
- 否则返回[start+1, end]
- 时间复杂度O(log(n)), 空间复杂度O(1)


# 思路3
- 设计一个递归函数f，返回<=target的最后一个元素的位置
    - 如果target存在，返回target的最后一个位置
    - 如果target不存在，返回<target的最后一个位置
- 设计一个递归函数g，返回>=target的最后一个元素的位置
    - 如果target存在，返回target的第一个位置
    - 如果target不存在，返回>target的第一个位置
- 调用f(nusm, target)，返回target的最右一个位置end
- 调用f(nums, target)，返回target的第一个位置start
- 如果start 或者 end 越界 或者 nums[start] != nums[end]，说明target在数组中不存在
- 否则返回[start, end]
- 时间复杂度O(log(n)), 空间复杂度O(1)
