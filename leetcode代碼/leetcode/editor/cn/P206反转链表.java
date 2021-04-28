package com.leetcode.editor.cn;
//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1693 👎 0

public class P206ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();
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
    public ListNode reverseList(ListNode head) {
        /**
         * 非递归
         */
//        ListNode pre=null;
//        ListNode cur=head;
//        while (cur!=null) {
//            ListNode next = cur.next;
//            cur.next=pre;
//            pre=cur;
//            cur=next;
//        }
//        return pre;

        if(head==null||head.next==null)
            return head;
        ListNode rev = reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return rev;


    }
}
//leetcode submit region end(Prohibit modification and deletion)

}