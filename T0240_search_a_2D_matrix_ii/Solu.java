package T0240_search_a_2D_matrix_ii;

import java.util.*;

class Solu {

    static class Solution {


        // 方法1
        // 从矩阵的右上角开始遍历
        // 当前元素 = target, return true
        // 当前元素 < target, i++, 走向同一列下一行
        // 当前元素 > target, j--, 走向同一行左边
        // 时间复杂度O(m+n), 空间复杂度O(1)
        public boolean searchMatrix(int[][] matrix, int target) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return false;

            int m = matrix.length, n = matrix[0].length;
            int i = 0, j = n - 1;
            while(i < m && j >= 0) {
                if(target == matrix[i][j]) return true;
                else if(target < matrix[i][j]) j--;
                else i++;
            }

            return false;
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;

        app.searchMatrix(matrix, target);
    }
}
