package Leetcode.middleofthelinkedlist;

class Solution {
  //Definition for singly-linked list.
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public ListNode middleNode(ListNode head) {
      ListNode res = head;
      int size = getListsize(res);
      for(int i=0; i<size/2; i++){
          res=res.next;
      }
      return (res);
  }
  public static int getListsize(ListNode list){
      int size = 0;
      while (list != null){
          size++;
          list = list.next;
      }
      return (size);
  }
}