package com.leetcode.editor.cn;
//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [], val = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [7,7,7,7], val = 7
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 列表中的节点在范围 [0, 104] 内 
// 1 <= Node.val <= 50 
// 0 <= k <= 50 
// 
// Related Topics 链表 
// 👍 574 👎 0

public class P203RemoveLinkedListElements{
    public static void main(String[] args) {
        Solution solution = new P203RemoveLinkedListElements().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
//        while (head!=null&&head.val==val)
//            head=head.next;
//
//        if (head==null)
//            return null;
//        ListNode prev=head;
//        while(prev.next!=null){
//            if (prev.next.val==val)
//                prev.next=prev.next.next;
//            else
//                prev=prev.next;
//
//        }
//        return head;

//        ListNode dummyHead= new ListNode(-1);
//        dummyHead.next=head;
//        ListNode prev=dummyHead;
//        while(prev.next!=null){
//            if (prev.next.val==val)
//                prev.next=prev.next.next;
//            else
//                prev=prev.next;
//
//        }
//        return dummyHead.next;


        if (head==null)
            return null;
        ListNode res=removeElements(head.next,val);
        if (head.val==val){
            return res;
        }
        else {
            head.next=res;
            return head;
        }


    }


}
//leetcode submit region end(Prohibit modification and deletion)

}