package line.test2021.test3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
[1,3,2]	[1,2,3]	[0,1,1]
[1,4,2,3]	[2,1,3,4]	[2,2,1,3]
[3,2,1]	[2,1,3]	[1,1,2]
[3,2,1]	[1,3,2]	[2,2,2]
[1,4,2,3]	[2,1,4,3]	[2,2,0,2]
 */
public class Solution {

    static public int[] solution(int[] enter, int[] leave) {
        int[][] meetCount = new int[enter.length + 1][enter.length + 1];
        List<Integer> meetPeople = new ArrayList<>();

        for (int i = 0; i < leave.length; i++) {
            for (int j = 0; j < enter.length; j++) {
                if (leave[i] == enter[j]) {
                    meetPeople.add(enter[j]);
                    break;
                } else {
                    meetPeople.add(enter[j]);
                }
            }
            for (int a = 0; a < meetPeople.size() - 1; a++) {
                int peopleA = meetPeople.get(a);
                for (int b = a + 1; b < meetPeople.size(); b++) {
                    int peopleB = meetPeople.get(b);
                    meetCount[peopleA][peopleB]++;
                    meetCount[peopleB][peopleA]++;
                }
            }
            Iterator<Integer> iterator = meetPeople.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == leave[i]) {
                    iterator.remove();
                    break;
                }
            }
        }

        int[] result = new int[enter.length];
        for (int i = 1; i <= enter.length; i++) {
            int total = 0;
            for (int j = 1; j <= enter.length; j++) {
                total += meetCount[i][j];
            }
            result[i - 1] = total;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
