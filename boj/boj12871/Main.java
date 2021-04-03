package boj.boj12871;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String leftString = br.readLine();
        String rightString = br.readLine();

        if (leftString.length() == rightString.length()) {
            System.out.println(leftString.equals(rightString) ? 1 : 0);
            System.exit(0);
        }

        int gcd = BigInteger.valueOf(leftString.length()).gcd(BigInteger.valueOf(rightString.length())).intValue();
        int lcm = leftString.length() * rightString.length() / gcd;
        StringBuilder left = new StringBuilder(leftString);
        StringBuilder right = new StringBuilder(rightString);
        int j = 0;
        for (int i = 0; i < lcm - leftString.length(); i++) {
            if (j == leftString.length()) j = 0;
            left.append(leftString.charAt(j++));
        }
        j = 0;
        for (int i = 0; i < lcm - rightString.length(); i++) {
            if (j== rightString.length()) j = 0;
            right.append(rightString.charAt(j++));
        }
        System.out.println(new String(left).equals(new String(right)) ? 1 : 0);

    }
}
