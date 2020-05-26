package Leetcode.reverselinkedlist;

import java.util.*;

public class Solution {
  //Definition for singly-linked list.
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public ListNode reverseList(ListNode head) {
    ListNode tmp = head;
    LinkedList<Integer> list = new LinkedList<>();
    while (tmp != null){
        list.add(tmp.val);
        tmp = tmp.next;
    }
    if (list.size()==0)
        return null;
    Collections.reverse(list);
    ListNode res = new ListNode(list.get(0));
    ListNode preNode = res;
    for(int i=1; i<list.size(); i++){
        ListNode nextNode = new ListNode(list.get(i));
        preNode.next = nextNode;
        preNode = nextNode;
    }
    return (res);
  }
}
