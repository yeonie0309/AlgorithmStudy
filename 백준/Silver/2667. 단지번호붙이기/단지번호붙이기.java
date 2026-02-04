import java.io.*;
import java.util.*;

public class Main{
    static int N; //지도 크기
    static int [][] map; //입력 지도
    static boolean[][] visited; //방문 체크 배열

    //상하좌우 이동을 위한 방향 배열 (dx, dy 같은 방향 배열을 사용하면 한번에 네방향 순회)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = { 0, 0, -1, 1};

    static int dfs(int x, int y){
        visited[x][y] = true; // 현재 칸 방문 처리
        int count = 1; //현재 칸 집(1)이므로 카운트에 추가

        //상하좌우 네방향으로 인접한 칸 확인
        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue; //지도 범위 벗어나면 무시
            if (visited[nx][ny]) continue; //이미 방문한 집 무시
            if (map[nx][ny] == 0) continue; //집 없으면 무시
            count += dfs(nx, ny); //방문하지 않은 집이라면 그 칸으로 dfs 이어가서 찾은 집 개수 count에 더함
        }
        return count; //이 단지의 총 집 개수

        }

        public static void main (String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine()); //N 입력

            map = new int[N][N]; //지도 배열 초기화
            visited = new boolean[N][N]; //방문 배열 초기화
            //지도 입력
            for (int i=0; i < N; i++){
                String line = br.readLine();
                for(int j=0; j<N; j++){ //0과 1을 숫자로 변환
                    map[i][j] = line.charAt(j) - '0';

                }
            }
            //단지 크기들 담을 리스트
            ArrayList<Integer> sizes = new ArrayList<>();
            //모든 칸을 순회하면서 단지찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        // DFS로 이 단지의 크기(집 개수) 계산
                        int size = dfs(i, j);

                        // 결과 저장
                        sizes.add(size);
                    }
                }
            }

            Collections.sort(sizes); //단지크기 오름차순 정렬

            StringBuilder sb = new StringBuilder();
            sb.append(sizes.size()).append("\n");
            for (int s: sizes){
                sb.append(s).append("\n");
            }
            System.out.print(sb.toString());
    }


}