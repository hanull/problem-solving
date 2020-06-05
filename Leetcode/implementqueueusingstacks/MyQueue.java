package Leetcode.implementqueueusingstacks;

import java.util.Stack;

class MyQueue {
  Stack<Integer> newStack, oldStack;
  int queueSize;
  /** Initialize your data structure here. */
  public MyQueue() {
      newStack = new Stack<>();
      oldStack = new Stack<>();
      queueSize = 0;
  }
  
  /** Push element x to the back of queue. */
  public void push(int x) {
      newStack.push(x);
      queueSize++;
  }
  
  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
      shiftStack();
      queueSize--;
      return (oldStack.pop());
  }
  
  /** Get the front element. */
  public int peek() {
      shiftStack();
      return (oldStack.peek());
  }
  
  public void shiftStack(){
      if (oldStack.isEmpty()) {
          while (!newStack.isEmpty()){
              oldStack.push(newStack.pop());
          }
      }
  }
  
  /** Returns whether the queue is empty. */
  public boolean empty() {
      return (queueSize==0);
  }
}

/**
* Your MyQueue object will be instantiated and called as such:
* MyQueue obj = new MyQueue();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.peek();
* boolean param_4 = obj.empty();
*/