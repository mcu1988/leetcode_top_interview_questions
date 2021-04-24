package T0118_pascal_triangle;

import java.util.ArrayList;
import java.util.List;

class Solu {

    static class Solution {

        // 方法
        // 第一层和第二层单独处理
        // 其它层利用上一层的结果结算出来
        public List<List<Integer>> generate(int numRows) {

            if(numRows < 1) return new ArrayList<>();

            List<List<Integer>> result = new ArrayList<>();
            List<Integer> list= new ArrayList<>();
            list.add(1);
            result.add(new ArrayList<>(list));

            // 第一层单独处理
            if(numRows == 1) return result;

            list.add(1);
            result.add(new ArrayList<>(list));

            // 第二层单独处理
            if(numRows == 2) return result;

            // 剩下的每层，首尾添加1，中间的值利用上一层的值得出来
            for(int i=3; i<=numRows; i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(1);
                for(int j=1; j<=i-2; j++) {
                    tmp.add(result.get(i-2).get(j-1) + result.get(i-2).get(j));
                }
                tmp.add(1);
                result.add(tmp);
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int numRows = 5;

        System.out.println(app.generate(numRows));
    }
}
