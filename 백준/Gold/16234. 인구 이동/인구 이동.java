import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;              // N: 지도 크기 L/R: 국경 열리는 인구차 범위
    static int[][] map;              // 각 나라 인구 수
    static boolean[][] visited;      // 하루 단위 방문 체크

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = { 0, 0,-1, 1};

    // DFS로 찾은 현재 연합의 좌표들 저장할 리스트
    static ArrayList<int[]> unionCells;

    // 현재 연합의 총 인구 합
    static int unionSum;

    static void dfs(int x, int y) {
        visited[x][y] = true;            // 방문 처리
        unionCells.add(new int[]{x, y}); // 연합에 포함
        unionSum += map[x][y];           // 인구 합 누적
        
        for (int k = 0; k < 4; k++) {  // 상하좌우 네 방향으로 인접 국가 확인
            int nx = x + dx[k];
            int ny = y + dy[k];
            
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue; // 범위 밖이면 무시
            if (visited[nx][ny]) continue; // 이미 방문했으면 무시
            int diff = Math.abs(map[x][y] - map[nx][ny]); // 인구 차이 계산
            
            if (diff >= L && diff <= R) { // 국경을 열 수 있는 조건일 때만 DFS 확장
                dfs(nx, ny);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); 
        L = Integer.parseInt(st.nextToken()); 
        R = Integer.parseInt(st.nextToken()); 

        map = new int[N][N]; // 지도 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 인구 입력
            }
        }
        int days = 0; // 인구 이동이 일어난 날짜 수
        
        while (true) {
            visited = new boolean[N][N]; // 매일 방문 배열 초기화
            boolean moved = false;       // 오늘 인구 이동이 한 번이라도 있었는지 체크

            // 모든 칸을 돌며 연합 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) { // 아직 방문 안 한 나라면 DFS로 연합 구성
                        unionCells = new ArrayList<>(); // 새 연합 리스트
                        unionSum = 0;                   // 새 연합 인구 합 초기화

                        dfs(i, j); // (i,j) 포함 연합 탐색

                        // 연합 크기가 2 이상이면 인구 이동 발생 (1이면 변화 없음)
                        if (unionCells.size() >= 2) {
                            moved = true;
                            int avg = unionSum / unionCells.size(); // 연합 평균 인구 = 합 / 나라 수
                            for (int[] cell : unionCells) { // 연합에 속한 모든 나라 인구를 평균으로 갱신
                                map[cell[0]][cell[1]] = avg;
                            }
                        }
                    }
                }
            }
            if (!moved) break; // 오늘 이동이 없었으면 종료
            days++; // 이동이 있었으면 하루 증가 후 다음 날 진행
        }
        System.out.println(days); 
    }
}