package Programmers.방금그곡;

public class Solution {

    static String[] beforeMelody = {"C#", "D#", "F#", "G#", "A#"};
    static String[] afterMelody = {"c", "d", "f", "g", "a"};

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        m = changeString(m);
        int maxTime = 0;
        for (int i = 0; i < musicinfos.length; i++) {
            String[] input = musicinfos[i].split(",");
            int startTime = changeTime(input[0]);
            int endTime = changeTime(input[1]);
            int playTime = endTime - startTime;
            String title = input[2];
            String score = totalMelody(endTime - startTime, changeString(input[3]));
            if (score.contains(m) && maxTime < playTime) {
                maxTime = playTime;
                answer = title;
            }
        }
        return answer;
    }

    private String changeString(String melody) {
        for (int i = 0; i < beforeMelody.length; i++) {
            if (melody.contains(beforeMelody[i])) {
                melody = melody.replaceAll(beforeMelody[i], afterMelody[i]);
            }
        }
        return melody;
    }

    private String totalMelody(int time, String score) {
        int len = score.length();
        if (time == len) return score;
        if (time < len) return score.substring(0, time);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < time / len; i++) {
            str.append(score);
        }
        str.append(score, 0, time % len);
        return str.toString();
    }

    private int changeTime(String time) {
        int total;
        String[] input = time.split(":");
        total = Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1]);
        return total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String m = "CC#BCC#BCC#BCC#B";
        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        System.out.println(sol.solution(m, musicinfos));
    }
}
