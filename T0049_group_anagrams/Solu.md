# leetcode t49
- 变位次分组
- 输一个字符串数组，字符串中只包含小写字母，对数组中的字符串进行分组
- eg,
    - strs = ["eat","tea","tan","ate","nat","bat"]， return [["bat"],["nat","tan"],["ate","eat","tea"]]
    
# 思路1
- 建立一个26大小的char数组，统计每个字符串中字符出现的次数，最后安保找字符+次数拼接起来行程key
- 使用map记录  key -> group的记录
- 时间复杂度O(N*K), 空间复杂度O(N*K)

# 思路2
- 对每个字符串进行排序，行程key
- 使用map记录  key -> group的记录
- 时间复杂度O(N*K*log(K)), 空间复杂度O(N*K)