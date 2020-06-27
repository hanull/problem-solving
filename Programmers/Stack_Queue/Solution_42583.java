package Programmers.Stack_Queue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Truck{
  int weight;
  int distance;
  public Truck(int weight, int distance) {
    this.weight = weight;
    this.distance = distance;
  }
}

public class Solution_42583 {
  
  public static int solution(int bridge_length, int weight, int[] truck_weights) {
    int answer_time = 0;
    int bridge_weight = 0;
    int move = 1;
    Queue<Truck> truck_que = new LinkedList<>();
    Queue<Truck> bridge_que = new LinkedList<>();
    for(int i=0; i<truck_weights.length; i++) {
      truck_que.add(new Truck(truck_weights[i], bridge_length));
    }
    
    while (!(truck_que.isEmpty() && bridge_que.isEmpty())) {
      // 다리를 통과할 수 있는 트럭이 있는지
      if (!bridge_que.isEmpty() && bridge_que.peek().distance == 0) {
        bridge_weight -= bridge_que.peek().weight;
        bridge_que.poll();
      }
      // 다리에 트럭을 추가할 수 있는지
      if (!truck_que.isEmpty()) {
        if (bridge_weight + truck_que.peek().weight <= weight) {
          bridge_weight += truck_que.peek().weight;
          bridge_que.add(truck_que.poll());
          move = 1;
        }else {
          bridge_weight -= bridge_que.peek().weight;
          move = bridge_que.poll().distance;
        }
      }
      Iterator<Truck> iter = bridge_que.iterator();
      while (iter.hasNext()) {
        iter.next().distance -= move;
      }
      answer_time += move;
    }
    return answer_time;
  }
  
  public static void main(String[] args) {
    int[] truck = {7,4,5,6};
    int weight = 10;
    int bridge_len = 2;
    System.out.println(solution(bridge_len, weight, truck));
  }
}
