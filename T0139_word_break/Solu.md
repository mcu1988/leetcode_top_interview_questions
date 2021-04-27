# leetcode t139
- 分割字符串
- 给定待分割的字符串，候选词典，返回字符串能不能分割成词典中的单词
- eg
    - s = "leetcode", wordDict = ["leet","code"]，return true

# 思路1
- 递归遍历原始字符串，考虑cur开始到结束位置能不能被分割
- 时间复杂度O(2^N-1), 空间复杂度O(N)

# 思路2
- 思路和方法1一致，方法1中有很多重复的递归，考虑使用缓存，减少递归调用
- dp[i]表示i...end能不能分割成功
- 时间复杂度O(2^N-1), 空间复杂度O(N)

# 思路3
- 思路和方法1一致，方法1中有很多重复的递归，考虑使用缓存，减少递归调用
- dp[i]表示i...end能不能分割成功
- 时间复杂度O(N^2), 空间复杂度O(N)