package boj.boj3107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] answer = new String[8];
        Arrays.fill(answer, "0000");

        if (input.contains("::")) {
            String[] splitString = {"", ""};
            int count = 0;
            for (String str : input.split("::")) {
                splitString[count++] = str;
            }
            String[] leftAddress = splitString[0].split(":");
            String[] rightAddress = splitString[1].split(":");
            for (int i = 0; i < leftAddress.length; i++) {
                answer[i] = getNewAddress(leftAddress[i]);
            }
            int end = 7 - rightAddress.length;
            int idx = rightAddress.length - 1;
            for (int i = 7; i > end; i--) {
                answer[i] = getNewAddress(rightAddress[idx--]);
            }
        } else {
            String[] address = input.split(":");
            for (int i = 0; i < address.length; i++) {
                answer[i] = getNewAddress(address[i]);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append(answer[i]);
            if (i < 7) {
                result.append(":");
            }
        }
        System.out.println(result);
    }

    private static String getNewAddress(String input) {
        String newString = "";
        int zeroSize = 4 - input.length();
        for (int j = 0; j < zeroSize; j++) {
            newString += "0";
        }
        newString += input;
        return newString;
    }
}
