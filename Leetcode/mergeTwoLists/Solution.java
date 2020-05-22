package mergeTwoLists;

import java.util.*;


class Solution {
  
    //Definition for singly-linked list.
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode tmp = l1;
        ListNode res = new ListNode();
        LinkedList<Integer> list = new LinkedList<>();
        int len;
        if (l1 == null && l2 == null)
            return null;
        while (tmp != null){
            list.add(tmp.val);
            tmp = tmp.next;
        }
        tmp = l2;
        while (tmp != null){
            list.add(tmp.val);
            tmp = tmp.next;
        }
        Collections.sort(list);
        len = list.size();
        res.val = list.get(len-1);
        for(int i=len-2; i>=0; i--){
            ListNode newNode = new ListNode(list.get(i), res);
            res = newNode;
        }
        return (res);
    }
}