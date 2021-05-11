package T0208_implement_trie_prefix_tree;


import java.util.*;

class Solu {

    static class Solution {

        // 方法
        // 建立前缀树，前缀是可以理解为多叉树
        // 输入是小写字母，是26叉树
        // 节点需要登记以该节点结尾的字符串数量
        static class Trie {

            class Node {
                Node[] nexts; // 多叉树路径上存储值
                int end; // 以该节点结尾的字符串数量
                Node() {
                    // 26个小写字母
                    nexts = new Node[26];
                    end = 0;
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
                }
                // 以该节点结尾的字符串数量+1
                p.end++;
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

    }


    public static void main(String[] args) {

        Solution app = new Solution();

        Solution.Trie trie = new Solution.Trie();

        System.out.println("insert apple");
        trie.insert("apple");

        System.out.println("search apple");
        System.out.println(trie.search("apple"));

        System.out.println("search app");
        System.out.println(trie.search("app"));

        System.out.println("start with app");
        System.out.println(trie.startsWith("app"));

        System.out.println("insert app");
        trie.insert("app");

        System.out.println("search app");
        System.out.println(trie.search("app"));

    }
}
