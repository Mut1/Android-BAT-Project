package com.leetcode.editor.cn;
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 动态规划 
// 👍 1159 👎 0

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class P96UniqueBinarySearchTrees{
    public static void main(String[] args) {
        Solution solution = new P96UniqueBinarySearchTrees().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numTrees(int n) {

        int[] dp=new int[n+1];
        dp[0]=1;
        for (int i =1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }
        return dp[n];

    }

}
//leetcode submit region end(Prohibit modification and deletion)

}