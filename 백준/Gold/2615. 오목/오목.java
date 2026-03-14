import java.io.*;
import java.util.*;

public class Main {

    static int[][] board = new int[19][19]; // 바둑판 저장 배열

    // 오른쪽, 아래, 오른쪽 아래 대각선, 오른쪽 위 대각선
    static int[] dx = {0, 1, 1, -1};
    static int[] dy = {1, 0, 1, 1};

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 19 x 19 바둑판 입력
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken()); // 현재 칸의 돌 정보 저장
            }
        }

        // 바둑판을 위에서 아래, 왼쪽에서 오른쪽 순서로 확인
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {

                // 현재 칸에 돌이 없으면 넘어감
                if (board[i][j] == 0) {
                    continue;
                }

                // 4방향에 대해 오목인지 확인
                for (int d = 0; d < 4; d++) {
                    if (isOmok(i, j, d)) {
                        System.out.println(board[i][j]); // 승리한 돌의 색 출력
                        System.out.println((i + 1) + " " + (j + 1)); // 시작 좌표 출력 (1부터 시작)
                        return;
                    }
                }
            }
        }

        // 승부가 나지 않으면 0 출력
        System.out.println(0);
    }

    static boolean isOmok(int x, int y, int dir) {

        int color = board[x][y]; // 현재 돌의 색
        int count = 1; // 현재 돌부터 시작하므로 1개부터 시작

        int px = x - dx[dir]; // 시작점 이전 칸의 행 위치
        int py = y - dy[dir]; // 시작점 이전 칸의 열 위치

        // 시작점 이전 칸이 범위 안이고 같은 색이면
        // 현재 칸은 해당 방향 연속 돌의 시작점이 아니므로 false
        if (isRange(px, py) && board[px][py] == color) {
            return false;
        }

        int nx = x + dx[dir]; // 다음 칸의 행 위치
        int ny = y + dy[dir]; // 다음 칸의 열 위치

        // 같은 방향으로 같은 색 돌이 몇 개 이어지는지 확인
        while (isRange(nx, ny) && board[nx][ny] == color) {
            count++; // 연속된 돌 개수 증가
            nx += dx[dir]; // 다음 행으로 이동
            ny += dy[dir]; // 다음 열로 이동
        }

        // 정확히 5개가 아니면 오목이 아님
        if (count != 5) {
            return false;
        }

        // 정확히 5개를 센 뒤의 다음 칸이 같은 색이면 6목 이상이므로 false
        if (isRange(nx, ny) && board[nx][ny] == color) {
            return false;
        }

        return true; // 정확히 5개인 경우 true
    }

    static boolean isRange(int x, int y) {
        // 바둑판 범위 안인지 확인
        return x >= 0 && y >= 0 && x < 19 && y < 19;
    }
}