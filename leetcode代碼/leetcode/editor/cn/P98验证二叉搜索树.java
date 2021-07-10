package com.leetcode.editor.cn;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 1070 👎 0

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class P98ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new P98ValidateBinarySearchTree().new Solution();
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
        //注意点：pre定义的位置
        //long pre = Long.MIN_VALUE;
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
//            if (root == null)
//                return true;
//            //中序遍历后一个节点比前一个节点值大
//            if (!isValidBST(root.left))
//                return false;
//            if (root.val <= pre)
//                return false;
//            pre = root.val;
//            return isValidBST(root.right);
        }

        //判断节点左子树小于  右子树大于
        /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
        boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            if (root == null) return true;
            // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
            if (min != null && root.val <= min.val) return false;
            if (max != null && root.val >= max.val) return false;

// 限定左子树的最大值是 root.val，右子树的最小值是 root.val
            return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}