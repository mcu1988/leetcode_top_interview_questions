# leetcode t238
- 数组除自身以外的乘积
- 数组的长度>=2
- eg,
    - nums = [1,2,3,4], return [24,12,8,6]
    
# 方法1
- 使用2个数组分别存放前缀乘积和后缀乘积
- 时间复杂度O(N), 空间复杂度O(N)

# 方法2
- 使用result数组存放从后往前的乘积，从前往后的乘积遍历过程中逐步计算
- result数组不占用额外空间
- 时间复杂度O(N), 空间复杂度O(1)