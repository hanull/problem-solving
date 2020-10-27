package Test.NHN.test1;

import java.util.Scanner;

class Main {

    static char shooter = 'A';
    static char[] seatPlayers;
    static int[] playersCount;

    private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame){
        seatPlayers = new char[numOfAllPlayers - 1];
        playersCount = new int[numOfAllPlayers];
        playersCount[0] = 1;
        for (int i = 0; i < numOfAllPlayers - 1; i++) {
            seatPlayers[i] = (char) ('A' + i + 1);
        }
        int idx = 0;
        for (int t = 0; t < numOfGames; t++) {
            idx = move(numOfMovesPerGame[t], idx, numOfAllPlayers - 1);
            if (isPossible(seatPlayers[idx], namesOfQuickPlayers)) {    // 해당 위치에 앉아 있는 사람이 퀵 플레이어가 아닐 때, 도망갈 수 있음
                playersCount[seatPlayers[idx] - 'A'] += 1;
                char tmp = shooter;
                shooter = seatPlayers[idx];
                seatPlayers[idx] = tmp;
            } else {
                playersCount[shooter - 'A'] += 1;
            }
        }
        for (int i = 0; i < seatPlayers.length; i++) {
            System.out.println(seatPlayers[i] + " " + playersCount[seatPlayers[i] - 'A']);
        }
        System.out.println(shooter + " " + playersCount[shooter - 'A']);
    }

    private static int move(int cnt, int idx, int n) {
        int i = 0;
        if (cnt > 0) {
            i = idx;
            int c = 0;
            while (c < cnt) {
                if (i == n-1) {
                    i = 0;
                    c++;
                    continue;
                }
                i++;
                c++;
            }
        } else {
            i = idx;
            cnt *= -1;
            int c = 0;
            while (c < cnt) {
                if (i == 0) {
                    i = n - 1;
                    c++;
                    continue;
                }
                i--;
                c++;
            }
        }
        return i;
    }

    private static boolean isPossible(char seatPlayer, char[] numOfQuickPlayers) {
        for (int i = 0; i < numOfQuickPlayers.length; i++) {
            if (seatPlayer == numOfQuickPlayers[i]) return false;
        }
        return true;
    }

    private static class InputData {
        int numOfAllPlayers;
        int numOfQuickPlayers;
        char[] namesOfQuickPlayers;
        int numOfGames;
        int[] numOfMovesPerGame;
    }

    private static InputData processStdin() {
        InputData inputData = new InputData();

        try (Scanner scanner = new Scanner(System.in)) {
            inputData.numOfAllPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

            inputData.numOfQuickPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            inputData.namesOfQuickPlayers = new char[inputData.numOfQuickPlayers];
            System.arraycopy(scanner.nextLine().trim().replaceAll("\\s+", "").toCharArray(), 0, inputData.namesOfQuickPlayers, 0, inputData.numOfQuickPlayers);

            inputData.numOfGames = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            inputData.numOfMovesPerGame = new int[inputData.numOfGames];
            String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
            for(int i = 0; i < inputData.numOfGames ; i++){
                inputData.numOfMovesPerGame[i] = Integer.parseInt(buf[i]);
            }
        } catch (Exception e) {
            throw e;
        }

        return inputData;
    }

    public static void main(String[] args) throws Exception {
        InputData inputData = processStdin();

        solution(inputData.numOfAllPlayers, inputData.numOfQuickPlayers, inputData.namesOfQuickPlayers, inputData.numOfGames, inputData.numOfMovesPerGame);
    }
}