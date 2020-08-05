package Programmers;


import java.util.Arrays;

public class Solution_42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] people = new int[n];
        Arrays.fill(people, 1);
        for (int i : lost)
            people[i-1]--;
        for (int i : reserve)
            people[i-1]++;
        for (int t : reserve) {
            int i=t-1;
            if (i>0 && people[i]==2 && people[i-1]==0) {
                people[i]-=1;
                people[i-1]+=1;
            }
            if (i+1<n && people[i]==2 && people[i+1]==0) {
                people[i]-=1;
                people[i+1]+=1;
            }
        }
        for (int i=0; i<n; i++) {
            if (people[i]>=1) answer++;
        }
        return answer;
    }
}
