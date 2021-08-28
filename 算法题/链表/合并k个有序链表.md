分治+合并

```java
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
    public ListNode mergeKLists(ListNode[] lists) {
            if(lists.length==0) return null;
            if(lists.length==1) return lists[0];
           return merge(lists,0,lists.length-1);
    }

    public ListNode merge(ListNode[] lists,int l,int r){
        if(l==r) return lists[l];
         if (l > r) {
            return null;
        }   
        int mid=(l+r)>>1;
        
        return mergeTwoList(merge(lists,l,mid),merge(lists,mid+1,r));
    }


    public ListNode mergeTwoList(ListNode l1,ListNode l2){
        ListNode newHead=new ListNode(0);
        ListNode cur=newHead;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                cur.next=l1;
                l1=l1.next;
                cur=cur.next;
            }else{
                  cur.next=l2;
                l2=l2.next;
                cur=cur.next;
            }
        }
        if(l1!=null) cur.next=l1;
        if(l2!=null) cur.next=l2;
        return newHead.next;
    }
}