package boj.boj15711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int max = (int) Math.sqrt(4 * Math.pow(10, 12));
    static boolean[] prime;
    static ArrayList<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = stoi(br.readLine());
        setPrime();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = stoi(st.nextToken());
            long B = stoi(st.nextToken());
            long hap = A + B;
            if (hap < 4) System.out.println("NO");
            else if (hap % 2 == 0) {
                System.out.println("YES");
            } else {
                long tmp = hap - 2;
                if (isPrime(tmp)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }

    private static boolean isPrime(long input) {
        if (input <= max) return prime[(int) input];
        for (long tmp : primeList) {
            if (input % tmp == 0) return false;
        }
        return true;
    }

    private static void setPrime() {
        prime = new boolean[max + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i * i <= max; i++) {
            for (int j = i * i; j <= max; j += i) {
                if (prime[j]) prime[j] = false;
            }
        }
        for (int i = 2; i <= max; i++) {
            if (prime[i]) primeList.add(i);
        }
    }

    private static long stoi(String input) {
        return Long.valueOf(input);
    }
}
