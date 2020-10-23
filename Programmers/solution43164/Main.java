package Programmers.solution43164;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets = {{"ICN", "A"}, {"A", "C"}, {"A", "D"}, {"D", "B"}, {"B", "A"}};
        System.out.println(Arrays.toString(sol.solution(tickets)));
    }
}

class Solution {

    static HashMap<String, ArrayList<Ticket>> hashMap = new HashMap<>();
    static boolean[] visited;
    static ArrayList<String> res = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        for (int i = 0; i < tickets.length; i++) {
            String from = tickets[i][0];
            String dest = tickets[i][1];
            if (!hashMap.containsKey(from)) {
                hashMap.put(from, new ArrayList<>());
            }
            ArrayList<Ticket> lists = hashMap.get(from);
            lists.add(new Ticket(dest, i));
            hashMap.put(from, lists);
        }
        dfs("ICN", "", 0, tickets.length);
        Collections.sort(res);
        return res.get(0).split(" ");
    }

    private void dfs(String from, String path, int cnt, int goal) {
        path += from + " ";
        if (cnt == goal) {
            res.add(path);
            return;
        }
        if (hashMap.get(from) == null) return;
        for (Ticket ticket : hashMap.get(from)) {
            int no = ticket.no;
            String dest = ticket.dest;
            if (visited[no]) continue;
            visited[no] = true;
            dfs(dest, path, cnt + 1, goal);
            visited[no] = false;
        }
    }
}

class Ticket {
    String dest;
    int no;

    public Ticket(String dest, int no) {
        this.dest = dest;
        this.no = no;
    }
}