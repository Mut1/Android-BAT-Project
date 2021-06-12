package com.leetcode.editor.cn;
//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// 
// 
// Related Topics 树 动态规划 
// 👍 881 👎 0

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class P95UniqueBinarySearchTreesIi {
    public static void main(String[] args) {
        Solution solution = new P95UniqueBinarySearchTreesIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<TreeNode> generateTrees(int n) {

            return getAns(1, n);
        }

        public List<TreeNode> getAns(int start, int end) {
            List<TreeNode> ans = new ArrayList<>();
            if (start > end) {
                ans.add(null);
                return ans;
            }
            if (start == end) {
                ans.add(new TreeNode(start));
                return ans;
            } else {

                for (int i = start; i <= end; i++) {
                    //所有可能的左子树
                    List<TreeNode> leftTree = new ArrayList<>();
                    //所有可能的右子树
                    List<TreeNode> rightTree = new ArrayList<>();
                    leftTree = getAns(start, i - 1);
                    rightTree = getAns(i + 1, end);
                    for (TreeNode leftNode : leftTree) {
                        for (TreeNode rightNode : rightTree) {
                            TreeNode cur = new TreeNode(i);
                            cur.left = leftNode;
                            cur.right = rightNode;
                            ans.add(cur);
                        }
                    }
                }
                return ans;

            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}