package Leetcode.deletenode;

public class Solution {
  //Definition for singly-linked list.
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  } 
  public void deleteNode(ListNode node) {
    ListNode current = node;
    ListNode nextNode = current.next;
    current.val = nextNode.val;
    current.next = nextNode.next;
  }
}
