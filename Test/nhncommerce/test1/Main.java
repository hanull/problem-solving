package Test.nhncommerce.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
10
SMS 010-1234-5678
CALL 010-1234-5678
CALL 010-1234-5678
SMS 010-1111-1111
SMS 010-5555-5555
CALL 010-5555-5555
CALL 010-5555-5555
CALL 010-4444-4444
SMS 010-5555-5555
CALL 010-5555-5555

4
SMS 010-1234-5678
SMS 010-1234-5678
SMS 010-1234-5678
CALL 010-5555-5555
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] logs = new String[n]; // 송수신 기록
        for(int i = 0 ; i < n ; i ++)
        {
            logs[i] = br.readLine();
        }
        String[] input = logs[0].split(" ");
        String currentType = input[0];
        String currentNumber = input[1];
        int count = 1;
        int total = 1;
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < logs.length; i++) {
            input = logs[i].split(" ");
            String type = input[0];
            String number = input[1];
            if (currentType.equals(type) && currentNumber.equals(number)) {
                count++;
            } else {
                total++;
                answer.append(currentType + " " + currentNumber);
                if (count > 1) {
                    answer.append(" (" + count + ")");
                }
                answer.append("\n");
                count = 1;
            }
            currentType = type;
            currentNumber = number;
        }
        answer.append(currentType + " " + currentNumber);
        if (count > 1) {
            answer.append(" (" + count + ")");
        }
        System.out.println(total);
        System.out.println(answer);
    }

}
