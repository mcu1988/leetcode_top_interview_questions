package T0212_word_search_ii;


import java.util.*;

class Solu {

    static class Solution {

        // 方法1
        // 遍历矩阵的每一个位置，以该位置为起点，深度优先遍历，匹配1个单词
        public List<String> findWords1(char[][] board, String[] words) {
            if(board == null || board.length == 0 || words == null || words.length == 0)
                return new ArrayList<>();

            int m = board.length, n = board[0].length;

            List<String> result = new ArrayList<>();
            for(int i=0; i<m; i++) { // 以board[i][j]为起点
                for(int j=0; j<n; j++) {
                    for(String word : words) // 逐个单词匹配
                        if(process(board, i, j, word, 0)) result.add(word);
                }
            }

            // 匹配到的单词去重
            result = new ArrayList<>(new HashSet<>(result));
            return result;
        }

        // 深度优先遍历
        // i， j表示当前来到的board位置
        // k是word来到的位置
        public boolean process(char[][] board, int i, int j, String word, int k) {
            // word遍历结束，说明匹配成功
            if(k == word.length()) return true;

            // i， j越界，匹配失败
            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
                return false;

            // 当前位置不匹配
            if(word.charAt(k) != board[i][j]) return false;

            // 当前位置匹配，把当前位置改为'0', 因为不能走重复位置，改成'0'后下一次来到重复位置自动匹配失败
            char tmp = board[i][j];
            board[i][j] = '0';

            boolean result = false;
            // 继续匹配上、下、左、右的位置
            result = process(board, i-1, j, word, k+1)
                    || process(board, i+1, j, word, k+1)
                    || process(board, i, j-1, word, k+1)
                    || process(board, i, j+1, word, k+1);

            board[i][j] = tmp;

            return result;
        }


        class Trie {

            class Node {
                Node[] nexts; // 多叉树路径上存储值
                int end; // 以该节点结尾的字符串数量
                int pass; // 经过该节点的字符串数量
                Node() {
                    // 26个小写字母
                    nexts = new Node[26];
                    end = 0;
                    pass = 0;
                }
            }

            // 多叉树的根节点
            Node root;

            public Trie() {
                root = new Node();
            }

            public void insert(String word) {
                if(word == null || word.length() == 0)
                    return;

                Node p = root;
                for(int i=0; i<word.length(); i++) {
                    if(p.nexts[word.charAt(i) - 'a'] == null) {
                        p.nexts[word.charAt(i) - 'a'] = new Node();
                    }
                    p = p.nexts[word.charAt(i) - 'a'];
                    p.pass++;
                }
                // 以该节点结尾的字符串数量+1
                p.end++;
            }

            public void delete(String word) {
                if(word == null || word.length() == 0)
                    return;

                // 先搜索word是否在前缀树里
                if(!search(word)) return;

                Node p = root;
                for(int i=0; i<word.length(); i++) {
                    // 删除时如果该点的pass = 0，那么直接把该点=null
                    p.nexts[word.charAt(i) - 'a'].pass--;
                    Node next = p.nexts[word.charAt(i) - 'a'];
                    if(p.nexts[word.charAt(i) - 'a'].pass == 0) {
                        p.nexts[word.charAt(i) - 'a'] = null;
                    }
                    p = next;
                }
                // 以该节点结尾的字符串数量+1
                p.end--;
            }

            public boolean search(String word) {
                if(word == null || word.length() == 0)
                    return false;

                Node p = root;
                for(int i=0; i<word.length(); i++) {
                    if(p.nexts[word.charAt(i) - 'a'] == null) {
                        return false;
                    }
                    p = p.nexts[word.charAt(i) - 'a'];
                }
                // search的时候需要完全匹配
                return p.end > 0;
            }

            public boolean startsWith(String prefix) {
                if(prefix == null || prefix.length() == 0)
                    return false;

                Node p = root;
                for(int i=0; i<prefix.length(); i++) {
                    if(p.nexts[prefix.charAt(i) - 'a'] == null) {
                        return false;
                    }
                    p = p.nexts[prefix.charAt(i) - 'a'];
                }
                return true;
            }
        }

        // 方法2
        // 遍历矩阵的每一个位置，以该位置为起点，深度优先遍历
        // 使用前缀树记录words
        // 每次匹配到一个单词后，就把单词从前缀树中删掉，减少后面的匹配次数
        public List<String> findWords2(char[][] board, String[] words) {
            if(board == null || board.length == 0 || words == null || words.length == 0)
                return new ArrayList<>();

            // 新建前缀树
            Trie trie = new Trie();
            for(String word: words) trie.insert(word);

            int m = board.length, n = board[0].length;
            List<String> result = new ArrayList<>();
            for(int i=0; i<m; i++) { // 以board[i][j]为起点
                for(int j=0; j<n; j++) {
                    process1(board, i, j, trie, "", result);
                }
            }
            return result;
        }

        // 深度优先遍历
        // i， j表示当前来到的board位置
        // trie前缀树
        // path 已经做过的路径构成的字符串
        public void process1(char[][] board, int i, int j, Trie trie, String path, List<String> result) {

            // i， j越界，匹配失败
            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
                return;

            // 已经遍历过，返回
            if(board[i][j] == '0') return;

            // path加上当前字符
            path = path + board[i][j];

            // 如果没哟path开头的字符串，返回
            if(!trie.startsWith(path)) return;

            // 如果有path字符串，加入到结果集，并删除当前字符串
            if(trie.search(path)) {
                result.add(path);
                trie.delete(path);
            }

            // 当前位置匹配，把当前位置改为'0', 因为不能走重复位置，改成'0'后下一次来到重复位置自动匹配失败
            char tmp = board[i][j];
            board[i][j] = '0';

            // 继续匹配上、下、左、右的位置
            process1(board, i-1, j, trie, path, result);
            process1(board, i+1, j, trie, path, result);
            process1(board, i, j-1, trie, path, result);
            process1(board, i, j+1, trie, path, result);

            board[i][j] = tmp;
        }

        // 方法3
        // 思路和方法2一样
        // 在遍历过程中使用前缀树的根节点往下遍历，不再使用search函数检索这个path
        public List<String> findWords(char[][] board, String[] words) {
            if(board == null || board.length == 0 || words == null || words.length == 0)
                return new ArrayList<>();

            // 新建前缀树
            Trie trie = new Trie();
            for(String word: words) trie.insert(word);

            int m = board.length, n = board[0].length;
            List<String> result = new ArrayList<>();
            for(int i=0; i<m; i++) { // 以board[i][j]为起点
                for(int j=0; j<n; j++) {
                    process2(board, i, j, trie, trie.root, "", result);
                }
            }

            return result;
        }

        // 深度优先遍历
        // i， j表示当前来到的board位置
        // trie前缀树
        // path 已经做过的路径构成的字符串
        public void process2(char[][] board, int i, int j, Trie trie, Trie.Node node, String path, List<String> result) {

            // i， j越界，匹配失败
            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
                return;

            // 已经遍历过，返回
            if(board[i][j] == '0') return;

            Trie.Node p = node.nexts[board[i][j] - 'a'];

            // 如果没哟path开头的字符串，返回
            if(p == null) return;

            // path加上当前字符
            path = path + board[i][j];

            // 如果有path字符串，加入到结果集，并删除当前字符串
            if(p.end > 0) {
                result.add(path);
                trie.delete(path);
            }

            // 当前位置匹配，把当前位置改为'0', 因为不能走重复位置，改成'0'后下一次来到重复位置自动匹配失败
            char tmp = board[i][j];
            board[i][j] = '0';

            // 继续匹配上、下、左、右的位置
            process2(board, i-1, j, trie, p, path, result);
            process2(board, i+1, j, trie, p, path, result);
            process2(board, i, j-1, trie, p, path, result);
            process2(board, i, j+1, trie, p, path, result);

            board[i][j] = tmp;
        }
    }



    public static void main(String[] args) {

        Solution app = new Solution();

        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};

        app.findWords(board, words);

        System.out.println(app.findWords(board, words));
    }
}
