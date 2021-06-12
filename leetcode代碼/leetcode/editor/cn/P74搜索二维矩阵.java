package com.leetcode.editor.cn;
//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 437 👎 0

public class P74SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new P74SearchA2dMatrix().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {

            //两次二分
            int l = 0, r = matrix.length - 1;
            while (l < r) {
                int mid = l + (r - l+1) / 2;
                if (matrix[mid][0] == target)
                    l = mid;
                else if (matrix[mid][0] < target)
                    l = mid;
                else r = mid - 1;
            }

            int row =l;
            // <=target
            l=0;
            r = matrix[row].length - 1;
            while (l < r) {
                int mid = l + (r - l+1) / 2;
                if (matrix[row][mid] == target)
                    l = mid;
                  else if (matrix[row][mid] < target)
                    l = mid ;
                else r = mid - 1;
            }
            return matrix[row][l]==target;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}