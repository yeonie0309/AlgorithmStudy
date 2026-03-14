/*
시작점에서 도착점까지 최단 거리를 구해야 하므로 BFS를 사용한다.
단, 벽을 최대 한 번만 부술 수 있으므로
각 위치에 대해 "벽을 아직 부수지 않은 상태"와 "이미 한 번 부순 상태"를
구분해서 방문 처리해야 한다.
BFS 탐색 중 빈 칸은 그대로 이동하고, 벽은 아직 부수지 않은 경우에만 한 번 부수고 이동한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N, M; // 행 크기 N, 열 크기 M
    static int[][] map; // 지도 정보 저장 배열
    static boolean[][][] visited; // visited[x][y][z] : (x,y)를 z상태로 방문했는지 저장
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동용 행 변화량
    static int[] dy = {0, 0, -1, 1}; // 상하좌우 이동용 열 변화량

    static class Node {
        int x;      // 현재 행 위치
        int y;      // 현재 열 위치
        int dist;   // 시작점부터 현재 위치까지의 거리
        int broken; // 벽을 부쉈는지 여부 (0 = 아직 안 부숨, 1 = 이미 부숨)

        Node(int x, int y, int dist, int broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력을 공백 기준으로 분리
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행 크기 N 입력
        M = Integer.parseInt(st.nextToken()); // 열 크기 M 입력

        map = new int[N][M]; // 지도 배열 생성
        visited = new boolean[N][M][2]; // 방문 배열 생성

        // 지도 정보 입력
        for (int i = 0; i < N; i++) {
            String str = br.readLine(); // 한 줄 입력 받기
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0'; // 문자 '0','1'을 숫자로 변환하여 저장
            }
        }

        // BFS 결과 출력
        System.out.println(bfs());
    }

    static int bfs() {

        Queue<Node> queue = new LinkedList<>(); // BFS를 위한 큐 생성

        queue.offer(new Node(0, 0, 1, 0)); // 시작점 (0,0), 거리 1, 아직 벽 안 부숨 상태로 삽입
        visited[0][0][0] = true; // 시작점 방문 처리

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {

            Node now = queue.poll(); // 현재 위치 꺼내기

            // 현재 위치가 도착점이면 최단 거리 반환
            if (now.x == N - 1 && now.y == M - 1) {
                return now.dist;
            }

            // 상하좌우 네 방향 확인
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i]; // 다음 행 위치
                int ny = now.y + dy[i]; // 다음 열 위치

                // 지도 범위를 벗어나면 넘어감
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                // 다음 칸이 빈 칸(0)이고 아직 해당 상태로 방문하지 않았다면
                if (map[nx][ny] == 0 && !visited[nx][ny][now.broken]) {
                    visited[nx][ny][now.broken] = true; // 방문 처리
                    queue.offer(new Node(nx, ny, now.dist + 1, now.broken)); // 같은 broken 상태로 이동
                }

                // 다음 칸이 벽(1)이고 아직 벽을 부수지 않은 상태라면
                if (map[nx][ny] == 1 && now.broken == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true; // 벽을 부순 상태로 방문 처리
                    queue.offer(new Node(nx, ny, now.dist + 1, 1)); // broken 상태를 1로 바꿔 이동
                }
            }
        }

        // 도착점까지 갈 수 없다면 -1 반환
        return -1;
    }
}