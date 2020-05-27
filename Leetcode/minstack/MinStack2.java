package Leetcode.minstack;

import java.util.Stack;

class MinStack2 {
    
    class Node{
        int val, min;
        Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }
    
    Stack<Node> stack = new Stack<>();
    
    /** initialize your data structure here. */
    public MinStack2() {
        
    }
    
    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(new Node(x,x));
        }else{
            stack.push(new Node(x, x<stack.peek().min ? x : stack.peek().min));
        }
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek().val;
    }
    
    public int getMin() {
        return stack.peek().min;
    }
}