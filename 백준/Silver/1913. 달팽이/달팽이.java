/*
N x N 배열의 가운데에서 시작해 1부터 N*N까지 달팽이 모양으로 채운다.
이동 방향은 상, 우, 하, 좌 순서로 반복하고
이동 칸 수는 1, 1, 2, 2, 3, 3 ... 형태로 증가한다.
숫자를 채우는 과정에서 찾고자 하는 숫자의 위치도 함께 저장한 뒤
완성된 배열과 해당 숫자의 좌표를 출력한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N; // 배열의 크기 N
    static int target; // 찾을 숫자
    static int[][] map; // 달팽이 배열

    // 상, 우, 하, 좌 이동 방향
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 배열 크기 N 입력
        target = Integer.parseInt(br.readLine()); // 찾을 숫자 입력

        map = new int[N][N]; // 배열 생성

        int x = N / 2; // 시작 행 위치(가운데)
        int y = N / 2; // 시작 열 위치(가운데)
        int num = 1; // 현재 넣을 숫자

        map[x][y] = 1; // 가운데 칸에 1 저장

        int answerX = x + 1; // 찾는 숫자의 행 위치 저장
        int answerY = y + 1; // 찾는 숫자의 열 위치 저장

        // 찾는 숫자가 1이면 가운데가 정답
        if (target == 1) {
            answerX = x + 1;
            answerY = y + 1;
        }

        int len = 1; // 현재 방향으로 이동할 칸 수
        int dir = 0; // 현재 방향 인덱스

        // 1부터 N*N까지 모두 채울 때까지 반복
        while (num < N * N) {

            // 같은 이동 칸 수로 2번 이동
            for (int t = 0; t < 2; t++) {

                // 현재 방향으로 len칸 이동
                for (int i = 0; i < len; i++) {
                    if (num == N * N) break; // 모든 숫자를 다 채웠으면 종료

                    x += dx[dir]; // 다음 행으로 이동
                    y += dy[dir]; // 다음 열으로 이동
                    num++; // 숫자 1 증가

                    map[x][y] = num; // 배열에 저장

                    // 현재 숫자가 찾는 숫자라면 위치 저장
                    if (num == target) {
                        answerX = x + 1;
                        answerY = y + 1;
                    }
                }

                dir = (dir + 1) % 4; // 다음 방향으로 변경
            }

            len++; // 이동 칸 수 증가
        }

        // 결과 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 배열 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // 찾는 숫자의 좌표 출력
        sb.append(answerX).append(" ").append(answerY);

        System.out.print(sb);
    }
}