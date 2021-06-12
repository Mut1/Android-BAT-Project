package com.leetcode.editor.cn;
//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 591 👎 0

import javax.swing.tree.TreeNode;
import java.util.*;

public class P145BinaryTreePostorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P145BinaryTreePostorderTraversal().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res=new LinkedList<>();
        if (root==null) return res;
        Deque<TreeNode> stack=new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur=stack.pop();
            res.addFirst(cur.val);
            if (cur.left!=null)
                stack.push(cur.left);
            if (cur.right!=null)
                stack.push(cur.right);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}