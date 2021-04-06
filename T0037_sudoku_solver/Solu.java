package T0037_sudoku_solver;

import java.util.ArrayList;
import java.util.List;

class Solu {

    private Solution app;

    static class Solution {

        // 方法
        // 使用递归依次从i行j列进行尝试
        public void solveSudoku(char[][] board) {
            if(board == null || board.length != 9 || board[0].length != 9) return;

            // 9行，9列，9个小的九宫格中0~9是否出现过
            boolean[][] rowMap = new boolean[9][9];
            boolean[][] columnMap = new boolean[9][9];
            boolean[][] subBoardMap = new boolean[9][9];

            // 把初始元素填入map
            initMap(board, rowMap, columnMap, subBoardMap);
            // 从 0,0处递归
            process(board, 0, 0, rowMap, columnMap, subBoardMap);

        }

        // 根据给定元素初始化map
        public void initMap(char[][] board, boolean[][] rowMap, boolean[][] columnMap, boolean[][] subBoardMap) {
            int tmp = ' ';
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    tmp = board[i][j];
                    if(tmp != '.') {
                        // 检查行
                        rowMap[i][tmp-'1'] = true;
                        columnMap[j][tmp-'1'] = true;
                        subBoardMap[(i / 3) * 3 + j / 3][tmp-'1'] = true;
                    }
                }
            }
        }

        // 从0,0处按行遍历，往下尝试
        // 如果下一层向当前层返回false，当前层继续尝试其他方案
        // 如果下一层向当前层返回true，当前层不在尝试，继续向上一层返回true，递归结束
        // 输入一定有一个解，一定会递归结束，此时就是最终答案
        public boolean process(char[][] board, int i, int j, boolean[][] rowMap, boolean[][] columnMap, boolean[][] subBoardMap) {
            if(i == 9 && j == 0) return true;

            // 计算下一跳的位置, 按行遍历，j==8时，跳往下一行开头
            int nexti = j == 8 ? i+1 : i;
            int nextj = j == 8 ? 0 : j+1;

            if(board[i][j] == '.') {
                // 当前位置填入数字

                // 获取候选数字集合
                List<Integer> list = new ArrayList<>();
                for(int k=0; k<9; k++) {
                    if (!(rowMap[i][k] || columnMap[j][k] || subBoardMap[(i / 3) * 3 + j / 3][k]))
                        list.add(k);
                }

                // 遍历候选数字
                for(int v : list) {
                    // 当天位置填入v，设置相应的值
                    board[i][j] = (char)(v + '1');
                    rowMap[i][v] = true;
                    columnMap[j][v] = true;
                    subBoardMap[(i / 3) * 3 + j / 3][v] = true;

                    // 如果有一个成功，就返回true，不在进行后续遍历
                    // 现场也不在恢复，因为此时的现场就是答案
                    if(process(board, nexti, nextj, rowMap, columnMap, subBoardMap))
                        return true;

                    // 退递归，当天位置的值恢复填入v之前的样子
                    board[i][j] = '.';
                    rowMap[i][v] = false;
                    columnMap[j][v] = false;
                    subBoardMap[(i / 3) * 3 + j / 3][v] = false;
                }

                // 候选集为空，或者候选集遍历完后，仍然没返回，说明当前路径走不通，向上层返回false
                return false;
            } else {
                // 当前是初始的数字时，遍历下一个位置
                return process(board, nexti, nextj, rowMap, columnMap, subBoardMap);
            }
        }

    }

    public static void main(String[] args) {

        Solution app = new Solution();

        char[][] board = {   {'8','3','.','.','7','.','.','.','.'}
                            ,{'6','.','.','1','9','5','.','.','.'}
                            ,{'.','9','8','.','.','.','.','6','.'}
                            ,{'8','.','.','.','6','.','.','.','3'}
                            ,{'4','.','.','8','.','3','.','.','1'}
                            ,{'7','.','.','.','2','.','.','.','6'}
                            ,{'.','6','.','.','.','.','2','8','.'}
                            ,{'.','.','.','4','1','9','.','.','5'}
                            ,{'.','.','.','.','8','.','.','7','9'}};

        app.solveSudoku(board);
    }
}
