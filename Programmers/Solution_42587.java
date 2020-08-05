package Programmers;

import java.util.*;

class Node{
    int val;
    int location;
    public Node(int val, int location) {
        this.val = val;
        this.location = location;
    }
}

public class Solution_42587 {
    public int solution(int[] priorities, int location) {
        int len=priorities.length;
        Queue<Node> q = new LinkedList<>();
        int[] maxVal = priorities.clone();
        Arrays.sort(maxVal);
        for(int i=0; i<len; i++) {
            q.offer(new Node(priorities[i], i));
        }
        int index=len-1;
        Queue<Node> resQue = new LinkedList<>();
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            if (tmp.val==maxVal[index]) {
                index--;
                resQue.offer(new Node(tmp.val, tmp.location));
            }
            else {
                q.offer(tmp);
            }

        }
        int res = 1;
        while (!resQue.isEmpty()) {
            Node tmp = resQue.poll();
            if (tmp.location == location) break;
            res++;
        }
        return res;
    }
}
