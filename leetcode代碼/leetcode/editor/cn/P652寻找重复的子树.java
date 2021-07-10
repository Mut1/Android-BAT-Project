package com.leetcode.editor.cn;
//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。 
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。 
//
// 示例 1： 
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
// 
//
// 下面是两个重复的子树： 
//
//       2
//     /
//    4
// 
//
// 和 
//
//     4
// 
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 286 👎 0

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class P652FindDuplicateSubtrees {
    public static void main(String[] args) {
        Solution solution = new P652FindDuplicateSubtrees().new Solution();
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

        HashMap<String, Integer> map = new HashMap<>();
        LinkedList<TreeNode> res = new LinkedList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            traverse(root);
            return res;
        }

        String traverse(TreeNode root) {
            if (root == null)
                return "#";
            String left = traverse(root.left);
            String right = traverse(root.right);

            //加逗号是关键
            String subSting = left + ","+right + ","+ root.val;
            int freq = map.getOrDefault(subSting, 0);
            // 多次重复也只会被加入结果集一次
            if (freq == 1)
                res.add(root);
            // 给子树对应的出现次数加一
            map.put(subSting, freq + 1);
            return subSting;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}