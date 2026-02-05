import java.io.*;
import java.util.*;

public class Main {

    static int R, C; // 격자 행, 열 크기
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우 이동용 행 변화
    static int[] dc = {0, 0, -1, 1}; // 상하좌우 이동용 열 변화

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 행 입력
        C = Integer.parseInt(st.nextToken()); // 열 입력
        int N = Integer.parseInt(st.nextToken()); // 시간 N 입력

        // 초기 격자 입력 ('.' 또는 'O')
        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // N == 1이면 초기 상태 그대로 출력
        if (N == 1) {
            print(grid);
            return;
        }

        // N이 짝수면 모든 칸에 폭탄 설치 상태 -> 전부 0
        if (N % 2 == 0) {
            print(fullBomb());
            return;
        }

        char[][] first = explode(grid); // t=3 상태-초기 폭탄들이 터진 결과

        if (N % 4 == 3) {
            //N=3,7,11,.. - first와 동일
            print(first);
        } else {
            // 예) N=5,9,13,.. - first에서 한 번 더 폭발한 결과
            char[][] second = explode(first);
            print(second);
        }
    }

    // 모든 칸을 'O'로 채운 격자 생성 (짝수초 상태)
    static char[][] fullBomb() {
        char[][] all = new char[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(all[i], 'O'); // 한 행을 전부 'O'로 채움
        }
        return all;
    }
    
    static char[][] explode(char[][] cur) {
        boolean[][] boom = new boolean[R][C]; // 이번 폭발로 비게 될 칸 표시 배열

        // 현재 폭탄을 기준으로 자기자신 + 상하좌우를 boom처리
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (cur[r][c] == 'O') {
                    // 폭탄이 있는 위치는 무조건 폭발로 빈칸이 됨
                    boom[r][c] = true;
                    // 상하좌우 함께 파괴됨
                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        // 범위를 벗어나면 무시
                        if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                        boom[nr][nc] = true; // 폭발 영향 표시
                    }
                }
            }
        }
        //폭발하기 직전을 기본으로 만듦
        char[][] next = fullBomb();

        //폭발 영향 받은 칸은 '.'로 비움
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (boom[r][c]) {
                    next[r][c] = '.';
                }
            }
        }
        return next; // 폭발 1회 적용된 결과 반환
    }
    //격자 출력 함수
    static void print(char[][] g) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(g[i]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
