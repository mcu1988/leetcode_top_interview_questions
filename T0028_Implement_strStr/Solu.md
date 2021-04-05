# leetcode t28
- 查找一个字符串在另外一个字符串中出现的其实位置
- 给定字符串haystack和needle，如果b在a中出现，返回第一个在a中出现的起始位置，如果不出现，返回-1
- 如果needle为空，题目规定返回-1
- eg， 
    - haystack = "hello", return "ll"
    - haystack = "aaab", return "ab"
        
# 思路1
- 由于数组有序，重复值都相邻
- 遍历数组，查看数组后面一个元素和其哪一个元素是否相同，不同代表新增一个不同的元素
- 时间复杂度，O(n * m)，n是haystack长度，m是needle长度，空间复杂度O(1)

# 思路2
- KMP算法
- 先求解needle字符串的nexts数组
     - needle字符串i位置的nexts数组值含义是 i-1位置结束的后缀字符串和0位置开始的前缀字符串的最长匹配长度，后缀和前缀不包括0~i-1的整个字符串
     - nexts[0] = -1, nexts[1] = 0
     - 从2位置开始求解nexts数组，nexts[i]与nexts[i-1]的递推关系
         - needle[i-1] == needle[nexts[i-1]], nexts[i] = nexts[i-1] + 1
         - needle[i-1] != needle[nexts[i-1]], nexts数组往前窜，nexts[nexts[i-1]]，继续判断nexts[nexts[i-1]] == needle[i-1]， 否则继续往前窜
         - 直到nexts数组窜到第0个位置，此时，比较needle[0] == needle[i-1], 相等，nexts[i]=1，否则nexts[i]=0
 - KMP算法
    - 利用nexts数组加速匹配过程，算法主流程和nexts数组求解一致
    - 从haystack字符串的0位置开始遍历，needle也同步遍历
        - 如果haystack[i] == needle[j], i++, j++
        - 否则，j = nexts[j],继续比较haystack[i] == needle[j]，不相等继续沿着nexts数组往前跳
        - 直到j == 0时，如果haystack[i] == needle[0]，那么i++,j++，否则，i++
  - 返回值
    - 如果 j == needle.length ，返回i - j
    - 否则返回-1  
- 时间复杂度，O(n)，n是haystack长度，空间复杂度O(1)