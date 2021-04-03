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
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
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
        leftString = leftString.concat(new String(left));
        rightString = rightString.concat(new String(right));
        System.out.println(leftString.equals(rightString) ? 1 : 0);

    }
}
