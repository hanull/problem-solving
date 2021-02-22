package boj.boj13300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static Student[] students;
    static class Student {
        int gender, grade;

        public Student(int gender, int grade) {
            this.gender = gender;
            this.grade = grade;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        students = new Student[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = new Student(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.gender == o2.gender) {
                    return o1.grade - o2.grade;
                }
                return o1.gender - o2.gender;
            }
        });

        int totalCount = 1;
        int cnt = 1;
        int gender = students[0].gender;
        int grade = students[0].grade;
        for (int i = 1; i < N; i++) {
            int nextGender = students[i].gender;
            int nextGrade = students[i].grade;
            if (gender == nextGender && grade == nextGrade && cnt < K) {
                cnt++;
            } else {
                gender = nextGender;
                grade = nextGrade;
                totalCount++;
                cnt = 1;
            }
        }
        System.out.println(totalCount);

    }

    static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
