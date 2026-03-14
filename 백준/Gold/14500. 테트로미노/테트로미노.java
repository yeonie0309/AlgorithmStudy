/*
테트로미노는 칸 4개를 이어서 만드는 도형이므로
대부분의 경우는 DFS를 이용해 깊이 4까지 탐색하면서 최대 합을 구할 수 있다.
단, ㅗ 모양은 DFS 경로만으로 만들 수 없으므로
각 칸을 중심으로 상하좌우 중 3방향을 선택하는 방식으로 따로 계산한다.
모든 칸에서 DFS와 ㅗ 모양 계산을 수행해 테트로미노 합의 최댓값을 구한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N, M; // 행 크기 N, 열 크기 M
    static int[][] map; // 종이 위 숫자 저장 배열
    static boolean[][] visited; // DFS 방문 체크 배열
    static int answer = 0; // 테트로미노로 만들 수 있는 최대 합
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 행 이동
    static int[] dy = {0, 0, -1, 1}; // 상하좌우 열 이동

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력을 공백 기준으로 분리
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행 크기 N 입력
        M = Integer.parseInt(st.nextToken()); // 열 크기 M 입력

        map = new int[N][M]; // 숫자판 배열 생성
        visited = new boolean[N][M]; // 방문 배열 생성

        // 숫자판 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 각 칸의 숫자 입력
            }
        }

        // 모든 칸을 시작점으로 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                visited[i][j] = true; // 시작 칸 방문 처리
                dfs(i, j, 1, map[i][j]); // DFS로 길이 4인 도형 탐색
                visited[i][j] = false; // 탐색 끝나면 방문 해제

                checkT(i, j); // ㅗ 모양 따로 계산
            }
        }

        // 최대 합 출력
        System.out.println(answer);
    }

    static void dfs(int x, int y, int depth, int sum) {

        // 칸 4개를 모두 선택했다면 최대값 갱신 후 종료
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        // 상하좌우 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // 다음 행 위치
            int ny = y + dy[i]; // 다음 열 위치

            // 범위를 벗어나면 넘어감
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }

            // 아직 방문하지 않은 칸이면 DFS 진행
            if (!visited[nx][ny]) {
                visited[nx][ny] = true; // 방문 처리
                dfs(nx, ny, depth + 1, sum + map[nx][ny]); // 다음 칸 탐색
                visited[nx][ny] = false; // 백트래킹
            }
        }
    }

    static void checkT(int x, int y) {

        int center = map[x][y]; // 가운데 칸 값
        int sum = center; // 현재 합
        int min = Integer.MAX_VALUE; // 포함된 날개 중 가장 작은 값
        int wing = 0; // 범위 안에 존재하는 날개 칸 개수

        // 상하좌우 4칸을 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // 인접한 행 위치
            int ny = y + dy[i]; // 인접한 열 위치

            // 범위 안에 있는 칸만 더함
            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                wing++; // 날개 개수 증가
                sum += map[nx][ny]; // 합에 추가
                min = Math.min(min, map[nx][ny]); // 가장 작은 날개 값 갱신
            }
        }

        // 인접 칸이 3개 미만이면 ㅗ 모양 자체를 만들 수 없음
        if (wing < 3) {
            return;
        }

        // 인접 칸이 정확히 3개면 그 자체가 ㅗ 모양
        if (wing == 3) {
            answer = Math.max(answer, sum);
        }

        // 인접 칸이 4개면 그중 하나를 제외해야 ㅗ 모양이 됨
        // 가장 작은 값을 제외하는 것이 최대 합
        if (wing == 4) {
            answer = Math.max(answer, sum - min);
        }
    }
}