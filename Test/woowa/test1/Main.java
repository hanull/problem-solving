package Test.woowa.test1;


public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

    }
}

class Solution {
    public int solution(String[] grades, int[] weights, int threshold) {
        int total = 0;
        for (int i = 0; i < grades.length; i++) {
            int gradeWeight = getGradeWeight(grades[i]);
            total += gradeWeight * weights[i];
        }
        return total - threshold;
    }

    private int getGradeWeight(String grade) {
        int res = 0;
        switch (grade) {
            case "A+":
                res = 10;
                break;
            case "A0":
                res = 9;
                break;
            case "B+":
                res = 8;
                break;
            case "B0":
                res = 7;
                break;
            case "C+":
                res = 6;
                break;
            case "C0":
                res = 5;
                break;
            case "D+":
                res = 4;
                break;
            case "D0":
                res = 3;
                break;
        }
        return res;
    }
}