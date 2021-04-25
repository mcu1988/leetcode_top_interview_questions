package T0127_word_ladder;

import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 找到wordlist中所有词的全排列，查看每一种排列是否有效，无效返回0，有效返回长度
        // 时间复杂度O(N! * k), N是wordlist的长度，k是单词的长度， 空间复杂度O(N)
        public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
            if(beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0
                || beginWord.length() != endWord.length() || wordList == null || wordList.size() == 0)
                return 0;

            int cnt = process(wordList, 0, beginWord, endWord);
            return  cnt == Integer.MAX_VALUE ? 0 : cnt+1;
        }

        // 深度优先遍历，生成wordList的全排列，每生成一个词，判断与前一个词之间是否只哟偶1个字符的差别
        // index: 全排列当前来到的位置
        // lastWord: 上一个全排列生成的单词
        // endWord: 目标单词
        // 递归的最大深度为N，因为wordList的词值值出现1次
        // 如果相邻单词差异不是1个字符，提前返回
        public int process(List<String> wordList, int index, String lastWord, String endWord) {

            // 全排列已经生成，仍然没有匹配到endWord，返回0
            if(index == wordList.size()) return 0;

            int minCnt = Integer.MAX_VALUE;
            for(int i=index; i<wordList.size(); i++) {
                // 交换当前位置和当前位置后面的每一个位置，生成当前位置打的全排列字符串
                swap(wordList, index, i);
                String cur = wordList.get(index);

                // 当前位置的string和上一个位置的string是否只相差一个字符
                if(!isValid(cur, lastWord)) {
                    // 当前string无效，恢复原样，继续把当前位置和其他位置交换
                    swap(wordList, index, i);
                    continue;
                }

                // 当前位置已经是目标单词
                if(cur.equals(endWord)) {
                    // 恢复原样，向上一层返回
                    swap(wordList, index, i);
                    return index+1;
                }

                // 当前位置有效，继续递归生成下一个位置的值
                int cnt = process(wordList, index+1, cur, endWord);

                // 寻找所有方案的最小值
                if(cnt > 0) minCnt = Math.min(minCnt, cnt);

                // 退递归，恢复原样
                swap(wordList, index, i);
            }
            return minCnt;
        }

        public void swap(List<String> wordList, int i, int j) {
            String tmp = wordList.get(i);
            wordList.set(i, wordList.get(j));
            wordList.set(j, tmp);
        }

        // 判断相连2个单词是否有效
        public boolean isValid(String a, String b) {
            if(a.length() != b.length()) return false;
            int notEqualCnt = 0;
            for(int i=0; i<a.length(); i++) {
                if(a.charAt(i) != b.charAt(i)) notEqualCnt++;
                if(notEqualCnt > 1) return false;
            }
            return notEqualCnt == 1;
        }


        // 方法2
        // 以beginWord为根节点，建立多叉树，层序遍历多叉树，当某一层第一次出现endWord时，返回层数，就是答案
        // 使用set记录已经加入到队列中的单词，因为一个单词只能出现在队列中一次，因此，队列中最多有N个单词
        // 时间复杂度O(N*K^2), N是wordlist的长度，k是单词的长度， 空间复杂度O(N^2)
        public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
            if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0
                    || beginWord.length() != endWord.length() || wordList == null || wordList.size() == 0)
                return 0;

            int K = beginWord.length();
            int N = wordList.size();

            // 使用HashMap记录每个单词的下一个邻接单词
            // 时间复杂度O(N*K^2)
            HashMap<String, HashSet<String>> map = getNexts(beginWord, wordList);

            // 存放层序遍历中已经到达的单词，每个单词在路径中只能出现一次，如果上层路径中已经出现过，下一层不需要再出现
            HashSet<String> reached = new HashSet<>();

            // 宽度优先生成多叉树
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            String cur = null;
            // 记录每层的节点数和当前层
            int curLevel = 1, curLevelSize = 0;
            while(!queue.isEmpty()) {
                curLevelSize = queue.size();
                // 依次弹出每一层的节点
                for(int i=0; i<curLevelSize; i++) {
                    cur = queue.poll();
                    // 如果当前节点已经是endWord，返回层数
                    if(cur.equals(endWord)) return curLevel;
                    // 加入当前节点的邻接词
                    for(String v: map.get(cur)) {
                        // 只加入没有出现过的邻接词
                        if(!reached.contains(v)) {
                            queue.add(v);
                            reached.add(v);
                        }
                    }
                }

                // 层数加1，层数最大为N+1, 因为一个词语只能出现在路径上1次
                // 层数超过N+1时，说明无法找到有效路径
                curLevel++;
                if(curLevel > N+1) return 0;
            }
            return 0;
        }

        // 建立每个单词的邻接词
        // 时间复杂度O(N*K^2)
        public HashMap<String, HashSet<String>> getNexts(String beginWord, List<String> wordList) {

            HashMap<String, HashSet<String>> result = new HashMap<>();

            HashSet<String> set = new HashSet<>(wordList);
            set.add(beginWord);

            for(String v : set) { // 堆每个单词，建立邻接词
                result.put(v, new HashSet<>());
                char[] arr = v.toCharArray();
                for(int j=0; j<arr.length; j++) {
                    // 把当前单词的每一个位置依次换为'a'~'z'之间的值，查看是否是邻接词
                    char ori = arr[j];
                    for(char k='a'; k<='z'; k++) {
                        // 跳过原单词
                        if(k == ori) continue;
                        arr[j] = k;
                        String tmp = String.valueOf(arr);
                        if(set.contains(tmp)) result.get(v).add(tmp); // hash查找，时间复杂度O(N)
                    }
                    // 每次遍历完一个位置后，恢复原始值
                    arr[j] = ori;
                }
            }
            return result;
        }

        // 方法3
        // 对方法2进行改进，从开始节点和结束节点同时寄到哪里多叉树，每次让节点少的层偶先遍历
        // 当头和尾的层节点有交集时，说返回层数
        // 使用set记录已经遍历的节点，每个节点只能出现1次
        // 初始不简历所有单词的邻接词，在遍历的过程中建立
        // 时间复杂度最大为O(N*K^2), N是wordlist的长度，k是单词的长度， 空间复杂度O(N)
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0
                    || beginWord.length() != endWord.length() || wordList == null || wordList.size() == 0)
                return 0;

            HashSet<String> wordSet = new HashSet<>(wordList);

            if(!wordSet.contains(endWord)) return 0;

            int K = beginWord.length();
            int N = wordList.size();

            // 生成开始层和终止层
            HashSet<String> startSet = new HashSet<>();
            startSet.add(beginWord);
            HashSet<String> endSet = new HashSet<>();
            endSet.add(endWord);

            // 记录已经访问过的节点
            HashSet<String> reached = new HashSet<>();
            reached.add(beginWord);
            reached.add(endWord);

            int level = 1;
            while(!startSet.isEmpty()) {

                // 记录下层的节点
                HashSet<String> nextSet = new HashSet<>();
                for(String v : startSet) { // 依次遍历当前层节点
                    char[] arr = v.toCharArray();
                    // 改变当前接待你每一个位置的值
                    for(int i=0; i<v.length(); i++) {
                        char ori = arr[i];
                        for(char j='a'; j<='z'; j++) {
                            // 跳过原始值
                            if(j == v.charAt(i)) continue;
                            arr[i] = j;
                            String str = String.valueOf(arr);
                            // 当下层和终止层有交集时，返回结果
                            if(endSet.contains(str)) return level + 1;
                            // 新生成的词有效，并且没有到达过
                            if(wordSet.contains(str) && !reached.contains(str)) {
                                nextSet.add(str);
                                reached.add(str);
                            }
                        }
                        // 恢复当前位置的值
                        arr[i] = ori;
                    }
                }

                // 层数加1
                level++;

                // 更新开始层和结束层
                startSet = (nextSet.size() <= endSet.size()) ? nextSet : endSet;
                endSet = (startSet == nextSet) ? endSet : nextSet;
            }
            return 0;
        }


    }

    public static void main(String[] args) {

        Solution app = new Solution();

        String[] arr = {"miss","dusk","kiss","musk","tusk","diss","disk","sang","ties","muss"};
        List<String> wordList = Arrays.asList(arr);
        String beginWord = "kiss", endWord = "tusk";

        app.ladderLength(beginWord, endWord, wordList);

        System.out.println(app.ladderLength(beginWord, endWord, wordList));
    }
}
