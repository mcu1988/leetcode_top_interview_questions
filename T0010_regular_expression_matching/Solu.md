# leetcode t4
- 正则表达式匹配，返回是否匹配成功
- 给定字符串s和模板字符串p, s中只包含小写字母，p中包含小写字母, ".", "*"
    - "." 可以表示任意字符
    - "\*" 需要结合前一个字符使用，表示前一个字符出现0次或者多次
- 需要检查p字符串的有效性
     - "\*"，单个\*无效
     - "a**"，多个\*连在一起无效
- eg， 
    - s = "aa", p = "a*", return true
    - s = "aab", p = "c*a*b", return true
        
# 思路1，递归实现
- 从左往右遍历字符串s和p
- 设计递归函数f(char[] s, int si, char[] p, int pi)
    - si表示s当前遍历到的位置
    - pi表示p当前遍历到的位置
    - f函数表示s[si.....]和p[pi......]两个结尾字符串是否可以匹配上
- f实现逻辑
    - 以下所有分支，只要有一个返回true，就代表si开头和pi开头能成功匹配
    - 如果si == s.length(), 查看pi是否结束或者pi是否是x*的格式
    - 如果pi == p.length(), 查看si是否结束
    - si和pi都没结束的情况
        - pi+1位置不是*
            - si和pi位置能否配上并且递归调用检查后续字符串能否匹配上
        - pi+1位置是*
            - 如果si和pi位置不匹配，*格式抵消0个字符，继续递归检查pi+2和si结尾的能否匹配
            - 如果si和pi位置匹配
                - 不使用匹配，*格式抵消0个字符，递归检查si和pi+2能否匹配上
                - 使用匹配，依次匹配1，2，3...次，检查si+1, si+2, si+3位置和pi+2后续的字符串能否匹配上
        - 所有情况都尝试后，依然没有返回true，最后返回false

- 时间复杂度O(m*n), 空间复杂度O(1)
- java实现，lc耗时25ms

# 思路2，缓存版本的递归
- 递归里存在大量重复调用行为
- 使用二维数组存储si和pi位置的结果，重复调用时，直接从数组里取值
- java实现，lc耗时16ms

# 思路3，dp
- 建立二维dp表 new boolean[m+1][n+1]
- 先填充边界
    - si = m时
        - pi = n, dp[m][n] = true
        - 其余情况看pi+1是否是\*
            - 不是\*，dp[m][pi] = false
            - 是\*，dp[m][pi] = dp[m][pi+2]
    - pi = n时，dp[m][n] = true
- 中间值填充
    - s[si] 和 p[pi]能匹配上
        - 查看pi+1是否为\*
            - p[pi+1] = \*
                - pi和pi+1匹配0次，dp[si][pi] = dp[si][pi+2]
                - pi和pi+1匹配1次，dp[si][pi] = dp[si+1][pi+2]
                - pi和pi+1匹配>=2次，dp[si][pi] = dp[si+1][pi]
                    - eg, s[si...end] = aaabc, p[pi...end] = a\*bc,
                    - a\*bc写为aa\*bc，s和p的第一个a消掉，后面的继续匹配
                - 最终是3中情况的或运算，dp[si][pi] = dp[si][pi+2] || dp[si+1][pi+2] || dp[si+1][pi]
            - p[pi+1] != \*
                - dp[si][pi] = dp[si+1][pi+1]
    - s[si] 和 p[pi]不能匹配上
        - p[pi+1] == \*
            - dp[si][pi] = dp[si][pi+2]
        - p[pi+1] != \*
            - dp[si][pi] = false
- dp[si][pi]的值只依赖于si,si+1和pi,pi+1,pi+2的值，因此si从高往低遍历，pi从高往低遍历
- 时间复杂度O(m*n), 空间复杂度O(m*n)
- java实现lc耗时2ms

# 思路4，节省空间的dp
- dp[si][pi]的求解只依赖于 dp[si][pi+2]，dp[si+1][pi]，dp[si+1][pi+1]，dp[si+1][pi+2]
- si行的切结依赖于si+1行的状态，可以用2个一维数组滚动更新si和si+1行的状态
- 进一步的可以用4个变量标记依赖值即可