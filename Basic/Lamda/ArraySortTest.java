package Basic.Lamda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class ArraySortTest {

    private static class Person{
        private String name;
        private int age;
        private String job;
        private int score;

        public Person() {
        }

        public Person(String name, int age, String job, int score) {
            super();
            this.name = name;
            this.age = age;
            this.job = job;
            this.score = score;
        }


        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Person [name=");
            builder.append(name);
            builder.append(", age=");
            builder.append(age);
            builder.append(", job=");
            builder.append(job);
            builder.append(", score=");
            builder.append(score);
            builder.append("]\n");
            return builder.toString();
        }
    }



    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("갓용수", 11, "학생", 100));
        list.add(new Person("갓민권", 15, "학생", 90));
        list.add(new Person("깡승현", 12, "학생", 76));
        list.add(new Person("킹나영", 10, "학생", 80));
        list.add(new Person("황태", 12, "학생", 85));

        Collections.sort(list, (o1, o2) -> o2.score - o1.score);    // 하나의 리턴값만 가진다면 {} 생략 가능.

        System.out.println(list);

    }// main
}
