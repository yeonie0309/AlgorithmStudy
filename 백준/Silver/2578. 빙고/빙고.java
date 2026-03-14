/*
빙고판에서 사회자가 부르는 수를 하나씩 지워가며
매 호출마다 가로, 세로, 대각선의 빙고 줄 개수를 확인한다.
빙고 줄의 개수가 처음으로 3개 이상이 되는 순간
몇 번째 수를 부른 뒤인지 출력한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static int[][] board = new int[5][5]; // 빙고판 숫자 저장 배열

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 빙고판 입력
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken()); // 빙고판 숫자 저장
            }
        }

        int count = 0; // 사회자가 부른 숫자의 개수

        // 사회자가 부르는 숫자 25개 입력
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken()); // 현재 부른 숫자
                count++; // 호출 횟수 증가

                erase(num); // 빙고판에서 해당 숫자를 지움

                // 현재 빙고 줄 개수가 3개 이상이면 정답 출력 후 종료
                if (checkBingo() >= 3) {
                    System.out.println(count);
                    return;
                }
            }
        }
    }

    static void erase(int num) {

        // 빙고판 전체를 돌면서 부른 숫자를 찾음
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == num) {
                    board[i][j] = 0; // 찾은 숫자를 0으로 바꿔 지운 것으로 처리
                    return; // 숫자는 한 번만 등장하므로 바로 종료
                }
            }
        }
    }

    static int checkBingo() {

        int bingo = 0; // 현재 완성된 빙고 줄 개수

        // 가로줄 확인
        for (int i = 0; i < 5; i++) {
            boolean complete = true; // 현재 가로줄이 모두 지워졌는지 확인하는 변수

            for (int j = 0; j < 5; j++) {
                if (board[i][j] != 0) { // 하나라도 남아 있으면 빙고 아님
                    complete = false;
                    break;
                }
            }

            if (complete) {
                bingo++; // 가로 빙고 1개 추가
            }
        }

        // 세로줄 확인
        for (int j = 0; j < 5; j++) {
            boolean complete = true; // 현재 세로줄이 모두 지워졌는지 확인하는 변수

            for (int i = 0; i < 5; i++) {
                if (board[i][j] != 0) { // 하나라도 남아 있으면 빙고 아님
                    complete = false;
                    break;
                }
            }

            if (complete) {
                bingo++; // 세로 빙고 1개 추가
            }
        }

        // 왼쪽 위 -> 오른쪽 아래 대각선 확인
        boolean complete = true;
        for (int i = 0; i < 5; i++) {
            if (board[i][i] != 0) {
                complete = false;
                break;
            }
        }
        if (complete) {
            bingo++; // 대각선 빙고 1개 추가
        }

        // 오른쪽 위 -> 왼쪽 아래 대각선 확인
        complete = true;
        for (int i = 0; i < 5; i++) {
            if (board[i][4 - i] != 0) {
                complete = false;
                break;
            }
        }
        if (complete) {
            bingo++; // 대각선 빙고 1개 추가
        }

        return bingo; // 현재 빙고 줄 개수 반환
    }
}