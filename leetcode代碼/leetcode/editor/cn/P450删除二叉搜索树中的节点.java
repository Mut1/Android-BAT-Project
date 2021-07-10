package com.leetcode.editor.cn;
//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。 
//
// 一般来说，删除节点可分为两个步骤： 
//
// 
// 首先找到需要删除的节点； 
// 如果找到了，删除它。 
// 
//
// 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。 
//
// 示例: 
//
// 
//root = [5,3,6,2,4,null,7]
//key = 3
//
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//
//    5
//   / \
//  4   6
// /     \
//2       7
//
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//    5
//   / \
//  2   6
//   \   \
//    4   7
// 
// Related Topics 树 二叉搜索树 二叉树 
// 👍 475 👎 0

import javax.swing.tree.TreeNode;

public class P450DeleteNodeInABst {
    public static void main(String[] args) {
        Solution solution = new P450DeleteNodeInABst().new Solution();
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
        public TreeNode deleteNode(TreeNode root, int key) {

            if (root == null) return null;
            if (root.val == key) {
                //情况 1：A 恰好是末端节点，两个子节点都为空，那么它可以当场去世了。
                //情况 2：A 只有一个非空子节点，那么它要让这个孩子接替自己的位置。
                //情况 3：A 有两个子节点，麻烦了，为了不破坏 BST 的性质，A 必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己。
//                if (root.left == null && root.right == null) return null;
//                if (root.left == null && root.right != null) return root.right;
//                if (root.right == null && root.left != null) return root.left;

                if (root.left==null) return root.right;
                if (root.right==null) return root.left;

                TreeNode minNode = getMin(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);

            }

            else if (root.val < key)
                root.right = deleteNode(root.right, key);
            else if (root.val > key)
                root.left = deleteNode(root.left, key);
            return root;
        }

        TreeNode getMin(TreeNode root) {
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}