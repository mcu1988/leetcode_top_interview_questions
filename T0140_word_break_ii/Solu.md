# leetcode t140
- 分割字符串
- 给定待分割的字符串，候选词典，返回所有可能的分割情况
- eg
    - s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]，return ["cats and dog","cat sand dog"]

# 思路1
- 递归遍历原始字符串，考虑cur开始到结束位置能不能被分割
- 时间复杂度O(2^N-1), 空间复杂度O(N)
- java实现超时