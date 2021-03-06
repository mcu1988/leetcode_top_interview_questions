# leetcode t131
- 字符串回文分割
- 把字符串分割，使每个子串都是回文，返回所有的分割方式
- eg
    - s = "aab"， return [["a","a","b"],["aa","b"]]

# 思路1
- 递归遍历字符串，使用start标记当前遍历的位置
- 依次尝试start开始的不同长度的字符串
- 时间复杂度O(2^N-1 * N), 总共有2^N-1种分割，每种分割判断回文O(N), 空间复杂度O(N)

# 思路2
- 思路和方法1一致，思路1中存放在重复判断回文的情况
- 使用缓存dp[i][j]记录i...j范围是否是回文
- dp[i][j]的递推关系
- 情况1：i==j, dp[i][j] = true
- 情况2：i+1==j, dp[i][j] = s[i] == s[j]
- 情况3：else, dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
- 时间复杂度O(2^N-1), 总共有2^N-1种分割, 空间复杂度O(N^2)