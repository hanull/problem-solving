package Programmers.여행경로;

import java.util.*;

class Solution {

    static int totalTicket;
    static HashMap<String, ArrayList<Ticket>> hashMap = new HashMap<>();
    static boolean[] isUsed;
    static List<String> result = new ArrayList<>();

    static class Ticket implements Comparable<Ticket> {
        int no;
        String to;

        public Ticket(int no, String to) {
            this.no = no;
            this.to = to;
        }

        public int compareTo(Ticket o) {
            return this.to.compareTo(o.to);
        }
    }

    public String[] solution(String[][] tickets) {
        for (int i=0; i<tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            if (!hashMap.containsKey(from)) {
                hashMap.put(from, new ArrayList<>());
            }
            hashMap.get(from).add(new Ticket(i, to));
        }
        totalTicket = tickets.length;
        isUsed = new boolean[totalTicket];

        dfs("ICN", "ICN", 0);

        Collections.sort(result);
        String[] answer = new String[result.get(0).length() / 3];
        String resultString = result.get(0);
        int idx = 0;
        for (int i = 0; i < resultString.length(); i+=3) {
            answer[idx++] = resultString.substring(i, i + 3);
        }
        return answer;
    }

    public void dfs(String airport, String path, int cnt) {
        if (cnt == totalTicket) {
            result.add(path);
            return;
        }
        if (hashMap.get(airport) == null) return;
        for (Ticket ticket : hashMap.get(airport)) {
            int no = ticket.no;
            String next = ticket.to;
            if (isUsed[no]) continue;
            isUsed[no] = true;
            dfs(next, path + next, cnt + 1);
            isUsed[no] = false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        System.out.println(Arrays.toString(sol.solution(tickets)));
    }
}