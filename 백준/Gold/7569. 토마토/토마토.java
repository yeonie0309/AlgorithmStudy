import java.util.LinkedList; // BFS용 큐 구현체로 LinkedList 사용
import java.util.Queue;      // Queue 인터페이스 사용
import java.util.Scanner;    // 입력 받기용

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 입력 객체 생성

        int[] dy = { 0, 0, -1, 1, 0, 0 };  // y(행) 방향 이동: 위/아래
        int[] dx = { 0, 0, 0, 0, -1, 1 };  // x(열) 방향 이동: 왼/오
        int[] dz = { -1, 1, 0, 0, 0, 0 };  // z(층) 방향 이동: 위층/아래층

        int M = sc.nextInt(), N = sc.nextInt(), H = sc.nextInt(); // M:가로, N:세로, H:높이 입력

        int[][][] tomato = new int[H][N][M]; // 3차원 토마토 상자 배열
        int cnt = 0, days = 0;               // cnt: 안 익은(0) 토마토 개수, days: 지난 날짜
        Queue<int[]> que = new LinkedList<>(); // BFS를 위한 큐(좌표 저장)

        for (int h = 0; h < H; h++) {          // 모든 층 반복
            for (int n = 0; n < N; n++)        // 모든 행 반복
                for (int m = 0; m < M; m++) {  // 모든 열 반복
                    tomato[h][n][m] = sc.nextInt(); // 해당 칸 상태 입력
                    if (tomato[h][n][m] == 1)       // 익은 토마토면
                        que.add(new int[] { h, n, m }); // 시작점으로 큐에 넣기
                    else if (tomato[h][n][m] == 0)   // 안 익은 토마토면
                        cnt++;                       // 안 익은 개수 증가
                }
        }

        while (cnt > 0 && !que.isEmpty()) {    // 안 익은 게 남아있고, 퍼질 곳이 있으면 반복
            for (int s = que.size(); s > 0; s--) { // 현재 큐 크기만큼 = 하루치 레벨 탐색
                int[] cur = que.poll();            // 현재 익은 토마토 위치 꺼내기

                for (int k = 0; k < 6; k++) {      // 6방향으로 확산 시도
                    int nz = cur[0] + dz[k];       // 다음 층(z)
                    int ny = cur[1] + dy[k];       // 다음 행(y)
                    int nx = cur[2] + dx[k];       // 다음 열(x)

                    // 범위 밖이거나, 안 익은 토마토(0)가 아니면 스킵
                    if (ny < 0 || nx < 0 || nz < 0 || ny >= N || nx >= M || nz >= H || tomato[nz][ny][nx] != 0)
                        continue;

                    cnt--;                         // 안 익은 토마토 하나 익힘 → 개수 감소
                    tomato[nz][ny][nx] = 1;        // 해당 칸을 익은 상태로 변경
                    que.add(new int[] { nz, ny, nx }); // 새로 익은 위치를 큐에 추가
                }
            }
            days++; // 하루가 지남(한 레벨 처리 끝)
        }

        System.out.println(cnt == 0 ? days : -1); // 다 익었으면 days 출력, 아니면 -1 출력
    }
}
