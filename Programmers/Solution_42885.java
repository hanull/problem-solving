package Programmers;

import java.util.Arrays;

public class Solution_42885 {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int len = people.length;
        int front = 0;
        int rear = len-1;
        Arrays.sort(people);
        while (front <= rear) {
            if (people[front] + people[rear] <= limit) {
                answer++;
                front++;
                rear--;
            }
            else {
                answer++;
                rear--;
            }
        }
        return answer;
    }
}
