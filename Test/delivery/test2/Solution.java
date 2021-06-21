package Test.delivery.test2;

public class Solution {

    static final int SIZE = (int) (14 * Math.pow(2, 20));
    static final String DATE = "1990-01-31";
    static final String BACKUP = "~";

    public String solution(String S) {
        String[] input = S.split("\n");
        int length = Integer.MAX_VALUE;
        for (String str : input) {
            String[] file = str.trim().split(" ");
            int size = getFileSize(file[0]);
            String date = file[1];
            if (!file[2].contains(BACKUP)) continue;
            if (size >= SIZE) continue;
            if (date.compareTo(DATE) <= 0) continue;
            String name = file[2].substring(0, file[2].lastIndexOf("."));
            length = Math.min(length, name.length());
        }
        return length == Integer.MAX_VALUE ? "NO FILES" : String.valueOf(length);
    }

    private int getFileSize(String size) {
        int changedSize;
        int number;
        if (size.contains("K")) {
            number = Integer.parseInt(size.split("K")[0]);
            changedSize = (int) (number * Math.pow(2, 10));
        } else if (size.contains("M")) {
            number = Integer.parseInt(size.split("M")[0]);
            changedSize = (int) (number * Math.pow(2, 20));
        } else {
            number = Integer.parseInt(size.split("G")[0]);
            changedSize = (int) (number * Math.pow(2, 30));
        }
        return changedSize;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("715K 2009-09-23 system.zip~\n" +
                " 179K 2013-08-14 to-do-list.xml~\n" +
                " 645K 2013-06-19 blockbuster.mpeg~\n" +
                "  536 2010-12-12 notes.html\n" +
                " 688M 1990-02-11 delete-this.zip~\n" +
                "  23K 1987-05-24 setup.png~\n" +
                " 616M 1965-06-06 important.html\n" +
                "  14M 1992-05-31 crucial-module.java~\n" +
                " 192K 1990-01-31 very-long-filename.dll~"));
    }
}
