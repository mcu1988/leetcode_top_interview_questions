# leetcode t44
- 特殊的正则匹配问题
- 给定2个字符串s, p，返回s和p是否匹配
- 匹配规则
    - ?匹配所有字符1次
    - *是单独使用，匹配任意字符0次或者多次
- s中只包含小写字母，p中只包含小写字母、'?'、'*'
- eg,
    - s = "cb", p = "?a", return false
    - s = "adceb", p = "\*a*\b", return true
    
# 思路1
- 递归解决
- 设计递归函数f(String s, int i, String p, int j)，返回s[i...end]和p[j...end]是否匹配
    - i表示s的i位置
    - j表示p的j位置
- f实现
    - i == s.length
        - 如果j == p.length(), 匹配成功
        - 如果j != p.length()
            - p[j] == '*', 继续递归f(s, i, p, j+1)
            - p[j] != '*'，匹配失败
    - j == p.length, 匹配失败
    - i, j都没结束时
        - 如果p[j] != '*',
            - s[i] == p[i]或者s[i] == '?'，继续递归f(s, i+1, p, j+1)
            - 否则匹配失败
        - 如果p[j] == '*'
            - j位置匹配0，1，2...次
    - 所有情况尝试完后，没有提前返回true，最后返回false
- 在递归过程中，会尝试所有情况，如果有一种情况返回true，那么递归会终止，不在继续尝试其他情况
- 存在重复的递归调用，java实现超时

# 思路2
- 思路1中存在重复的递归调用，使用二维数组int[][] dp = new int[s.length()][p.length()]记录已经递归过的值
- dp[i][j] == 0,表示没递归过
- dp[i][j] == 1表示匹配成功
- dp[i][j] == 1表示匹配失败
- java实现耗时380ms

# 思路3
- 动态规划，boolean[][] dp = new boolean[s.length()][p.length()]
- dp[i][j]表示s[i...end]和p[j...end]是否匹配成功
- 初始条件
    - dp[s.length()][p.length()] = true
    - [s.length()][i] = p[i] == '*' ? dp[s.length()][i+1] : false;
- 中间值i和j的匹配
    - 情况1，p[j] != '*', 如果s[i]和p[j]位置不匹配，那么dp[i][j] = false, 否则dp[i][j] = dp[i+1][j+1]
    - 情况2，p[j] == '*', 如果*匹配0次，dp[i][j] = dp[i][j+1],  
                         匹配1次，dp[i][j] = dp[i+1][j+1],  
                         匹配>=2次，可以把p[j]看成是**的格式，第一个*和p[i]抵消，p[j]还是*，dp[i][j] = dp[i+1][j]  
                         3种情况综合 dp[i][j] = dp[i][j+1] || dp[i+1][j+1] || dp[i+1][j] = dp[i][j+1] || dp[i+1][j]， 因为dp[i+1][j]依赖于dp[i+1][j+1]
- dp[i][j]依赖于dp[i][j+1]， dp[i+1][j]，dp[i+1][j+1]， 因此变量i从高往低遍历，j从高往低遍历
- 时间复杂度O(M*N)，空间复杂度O(M*N)
- java实现耗时23ms

# 思路4
- 压缩思路3中dp的空间
- dp[i][j]依赖于dp[i][j+1]， dp[i+1][j]，dp[i+1][j+1]，因此只需要使用一维数组boolean[] dp = new boolean[p.length()]来标记当前行的状态
- dp[i][j+1]， dp[i+1][j]是数组中保存的，dp[i+1][j+1]需要单独使用遍历记录并更新
- 时间复杂度O(M*N)，空间复杂度O(N)

# 其他思考
- 以上设计的递归函数f表示s[i...end]和p[j...end]的匹配情况，也可以把递归函数的含义设计成s[0...i]和p[0...j]的匹配情况, 此时需要从后往前遍历