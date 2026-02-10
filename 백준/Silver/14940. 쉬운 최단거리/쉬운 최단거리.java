import java.io.*;      // BufferedReader/Writer 등 입출력 클래스
import java.util.*;    // StringTokenizer, Queue, LinkedList 등 유틸

public class Main {
    static int[][] graph;      // 입력으로 받은 지도(0/1/2)
    static boolean[][] visited; // 방문 여부(0인 칸은 미리 true 처리)
    static int[][] result;     // 거리 결과 저장 배열
    static int n, m;           // n: 세로(행), m: 가로(열)

    static int[] dx = {-1, 1, 0, 0};  // x(행) 이동: 위, 아래
    static int[] dy = {0, 0, -1, 1};  // y(열) 이동: 왼, 오른

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른 입력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // (현재 출력에는 미사용)
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 첫 줄 토큰 분리

        n = Integer.parseInt(st.nextToken()); // 행 개수 입력
        m = Integer.parseInt(st.nextToken()); // 열 개수 입력

        graph = new int[n][m];        // 지도 배열 생성
        visited = new boolean[n][m];  // 방문 배열 생성
        result = new int[n][m];       // 결과 배열 생성
        int x=0, y=0;                 // 시작점(값 2)의 좌표 저장

        for (int i = 0; i < n; i++) {                      // 각 행 입력
            st = new StringTokenizer(br.readLine(), " ");   // 한 줄 토큰 분리
            for (int j = 0; j < m; j++) {                   // 각 열 입력
                graph[i][j] = Integer.parseInt(st.nextToken()); // 값 저장
                if (graph[i][j] == 2) {                     // 시작점(2) 찾기
                    x = i;                                  // 시작 x 저장
                    y = j;                                  // 시작 y 저장
                } else if (graph[i][j] == 0) {              // 갈 수 없는 칸(0)이면
                    visited[i][j] = true;                   // 방문 처리해 -1로 안 바뀌게 함
                }
            }
        }

        bfs(x, y); // 시작점에서 BFS로 거리 계산

        for (int i = 0; i < n; i++) {               // 결과 출력(행)
            for (int j = 0; j < m; j++) {           // 결과 출력(열)
                if (!visited[i][j]) {               // 아직 방문 못한 칸이면(도달 불가)
                    result[i][j] = -1;              // -1로 표시
                }
                System.out.print(result[i][j] + " "); // 거리 또는 -1 출력
            }
            System.out.println(); // 줄바꿈
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();   // BFS용 큐 생성
        queue.add(new int[]{x, y});                // 시작 좌표 큐에 추가
        visited[x][y] = true;                      // 시작점 방문 처리

        while (!queue.isEmpty()) {                 // 큐가 빌 때까지 반복
            int tmp[] = queue.poll();              // 현재 좌표 꺼내기

            for (int i = 0; i < 4; i++) {          // 4방향 탐색
                int nx = tmp[0] + dx[i];           // 다음 x(행)
                int ny = tmp[1] + dy[i];           // 다음 y(열)

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) { // 범위 체크
                    if (!visited[nx][ny] && graph[nx][ny] == 1) { // 미방문 + 갈 수 있는 길(1)만
                        visited[nx][ny] = true;                   // 방문 처리
                        result[nx][ny] = result[tmp[0]][tmp[1]] + 1; // 거리 = 현재 거리 + 1
                        queue.add(new int[]{nx, ny});              // 다음 좌표 큐에 추가
                    }
                }
            }
        }
    }
}
