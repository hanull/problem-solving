package Leetcode.minstack;
class MinStack {
    private Node topNode;
    private int size;
    private class Node{
        private int data;
        public Node next;
        public Node(int data){
            this.data = data;
        }
    }
    /** initialize your data structure here. */
    public MinStack() {
        topNode = null;
        size=0;
    }
    
    public void push(int x) {
        Node nextNode = new Node(x);
        if (size == 0){
            topNode = nextNode;
        }else{
            nextNode.next = topNode;
            topNode = nextNode;
        }
        size++;
    }
    
    public void pop() {
        topNode = topNode.next;
        size--;
    }
    
    public int top() {
        return (topNode.data);
    }
    
    public int getMin() {
        int res=Integer.MAX_VALUE;
        Node tmp = topNode;
        while (tmp != null){
            int k = tmp.data;
            if (res > k)
                res = k;
            tmp = tmp.next;
        }
        return (res);
    }
}