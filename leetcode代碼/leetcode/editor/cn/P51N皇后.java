package com.leetcode.editor.cn;
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 数组 回溯 
// 👍 930 👎 0

import java.util.ArrayList;
import java.util.List;

public class P51NQueens {
    public static void main(String[] args) {
        Solution solution = new P51NQueens().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
         List<List<String>> res = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            ArrayList<StringBuilder> track = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(".");
                }
                track.add(sb);
            }
            backtrack(track, 0);
            return res;
        }

        void backtrack(ArrayList<StringBuilder> track, int row) {
            if (row == track.size()) {
                ArrayList<String> track1 = new ArrayList<>();
                for (int i = 0; i < track.size(); i++) {
                    track1.add(track.get(i).toString());
                }
                res.add(track1);
                return;
            }

            //列数
            int n = track.get(row).length();
            for (int col = 0; col < n; col++) {
                if (!valid(track, row, col)) {
                    continue;
                }
                //做选择
                track.get(row).setCharAt(col, 'Q');
                //进入下一行放皇后
                backtrack(track, row + 1);
                //撤销选择
                track.get(row).setCharAt(col, '.');
            }

        }

        boolean valid(ArrayList<StringBuilder> track, int row,int col){
            int n=track.size();
            // 检查列是否有皇后冲突
            for(int i = 0; i < n; i++){
                if(track.get(i).charAt(col) == 'Q')
                    return false;
            }
            // 检查右上方是否有皇后冲突
            for(int i = row-1, j = col+1; i>=0 && j <n; i--,j++){
                if(track.get(i).charAt(j) == 'Q')
                    return false;
            }
            // 检查左上方是否有皇后冲突
            for(int i= row-1, j = col-1; i>=0 && j >=0; i--,j--){
                if(track.get(i).charAt(j) == 'Q')
                    return false;
            }
            return true;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}