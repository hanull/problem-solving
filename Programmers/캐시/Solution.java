package Programmers.캐시;

import java.util.*;

public class Solution {

    static class Node {
        String value;
        int useTime;

        public Node(final String value, final int useTime) {
            this.value = value;
            this.useTime = useTime;
        }
    }

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        List<Node> orderUsed = new LinkedList<>();
        Map<String, Node> cache = new HashMap<>();
        int answer = 0;
        int useTime = 0;
        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.containsKey(city)) {
                cache.get(city).useTime = useTime;
                answer += 1;
            } else {
                if (cache.size() >= cacheSize) {
                    orderUsed.sort(Comparator.comparingInt(o -> o.useTime));
                    cache.remove(orderUsed.get(0).value);
                    orderUsed.remove(0);
                }
                Node newCity = new Node(city, useTime);
                orderUsed.add(newCity);
                cache.put(city, newCity);
                answer += 5;
            }
            useTime++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(3, new String[]{"a", "b", "c", "a", "b", "d", "c"})); // 27
        System.out.println(sol.solution(3,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(sol.solution(3,
                new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        System.out.println(sol.solution(2,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris",
                        "Jeju", "NewYork", "Rome"}));
        System.out.println(sol.solution(5,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris",
                        "Jeju", "NewYork", "Rome"}));
        System.out.println(sol.solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
        System.out.println(sol.solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
    }
}
