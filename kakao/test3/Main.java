package kakao.test3;

import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(sol.solution(info, query));
    }
}

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        String[][] applicant = new String[info.length][5];

        init_info(applicant, info);
        for (int i = 0; i < query.length; i++) {
            answer[i] = getCountApplicant(applicant, query[i]);
            System.out.println(answer[i]);
        }
        return answer;
    }

    private int getCountApplicant(String[][] applicant, String query) {
        StringTokenizer st = new StringTokenizer(query);
        String lang = st.nextToken(); st.nextToken();
        String job = st.nextToken(); st.nextToken();
        String career = st.nextToken(); st.nextToken();
        String food = st.nextToken();
        String score = st.nextToken();
        int cnt = 0;

        for (int i = 0; i < applicant.length; i++) {
            if (!(applicant[i][0].equals(lang) || lang.equals("-"))) continue;
            if (!(applicant[i][1].equals(job) || job.equals("-"))) continue;
            if (!(applicant[i][2].equals(career) || career.equals("-"))) continue;
            if (!(applicant[i][3].equals(food) || food.equals("-"))) continue;
            if (Integer.valueOf(applicant[i][4]) < Integer.valueOf(score)) continue;
            cnt++;
        }
        return cnt;
    }

    private void init_info(String[][] applicant, String[] info) {
        for (int i = 0; i < info.length; i++) {
            StringTokenizer st = new StringTokenizer(info[i]);
            for (int j = 0; j < 5; j++) {
                applicant[i][j] = st.nextToken();
            }
        }
    }
}