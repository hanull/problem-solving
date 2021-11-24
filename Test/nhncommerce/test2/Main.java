package Test.nhncommerce.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = stoi(br.readLine());
        while (T-- > 0) {
            int lastCustomer = 0;
            int total = 0;
            HashSet<String> usedNumber = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            int N = stoi(st.nextToken());
            int M = stoi(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                if (total == M) break;
                String number = st.nextToken();
                if (usedNumber.contains(number)) continue;
                usedNumber.add(number);
                total++;
                lastCustomer = i;
            }
            System.out.println(lastCustomer + 1);
        }
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }
}
