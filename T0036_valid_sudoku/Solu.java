package T0036_valid_sudoku;

class Solu {

    private Solution app;

    static class Solution {

        // 方法
        // 时间复杂度O(81)，空间复杂度O(81)
        public boolean isValidSudoku(char[][] board) {
            if(board == null || board.length != 9 || board[0].length != 9) return false;

            // 9行，9列，9个小的九宫格中0~9是否出现过
            boolean[][] rowMap = new boolean[9][9];
            boolean[][] columnMap = new boolean[9][9];
            boolean[][] subBoardMap = new boolean[9][9];

            char tmp = ' ';
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    tmp = board[i][j];
                    if(tmp != '.') {
                        // 检查行
                        if(rowMap[i][tmp-'1']) return false;
                        else rowMap[i][tmp-'1'] = true;

                        // 检查列
                        if(columnMap[j][tmp-'1']) return false;
                        else columnMap[j][tmp-'1'] = true;

                        // 检查小的九宫格
                        if(subBoardMap[(i / 3) * 3 + j / 3][tmp-'1']) return false;
                        else subBoardMap[(i / 3) * 3 + j / 3][tmp-'1'] = true;
                    }
                }
            }
            return true;
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

        System.out.println(app.isValidSudoku(board));

    }
}
