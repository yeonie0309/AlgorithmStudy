import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 컴퓨터 수 N 입력
        int m = Integer.parseInt(br.readLine()); // 연결 수 M 입력

        // 그래프를 인접 리스트로
        //graph[i] = i번 컴퓨터와 직접 연결된 컴퓨터 목록
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { // 컴퓨터 번호가 1~n 이므로 n+1 크기
            graph.add(new ArrayList<>());
        }

        //간선 정보 입력받아 양방향으로 저장
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // BFS 위한 방문 배열
        boolean[] visited = new boolean[n + 1];

        // BFS 시작 - 1번 컴퓨터
        Queue<Integer> q = new ArrayDeque<>();
        visited[1] = true;     // 1번은 시작점이므로 방문 처리
        q.offer(1);

        int infectedCount = 0; // 1번을 통해 감염되는 컴퓨터 수를 세기 위한 변수

        // BFS 수행
        while (!q.isEmpty()) {
            int cur = q.poll();

            // cur과 연결된 모든 컴퓨터(next)를 확인
            for (int next : graph.get(cur)) {
                // 아직 방문하지 않았다면 새로 감염되는 컴퓨터
                if (!visited[next]) {
                    visited[next] = true; // 방문 처리
                    q.offer(next);        // 다음 탐색 대상으로 큐에 추가
                    infectedCount++;      // 1번 제외하고 "새로 감염된 컴퓨터"만 카운트
                }
            }
        }
        
        System.out.println(infectedCount);
    }
}