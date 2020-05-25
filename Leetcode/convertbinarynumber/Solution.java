package Leetcode.convertbinarynumber;

import java.util.LinkedList;


public class Solution {
  
  //Definition for singly-linked list.
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public int getDecimalValue(ListNode head) {
    LinkedList<Integer> list = new LinkedList<>();
    ListNode tmp = head;
    int size = 0;
    int res = 0;
    while (tmp != null){
        list.add(tmp.val);
        tmp = tmp.next;
    }
    size = list.size()-1;
    for(int i=size; i>=0; i--){
        res = (int) (res + Math.pow(2, size-i)*list.get(i));
    }
    return (res);
  }
}
