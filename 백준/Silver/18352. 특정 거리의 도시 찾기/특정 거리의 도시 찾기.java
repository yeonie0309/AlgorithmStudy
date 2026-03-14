import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, X; // 도시 개수 N, 도로 개수 M, 목표 거리 K, 출발 도시 X
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 인접 리스트
    static int[] dist; // 시작 도시 X로부터 각 도시까지의 최단 거리 저장 배열

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 첫 줄 입력을 공백 기준으로 분리
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시 개수 N 입력
        M = Integer.parseInt(st.nextToken()); // 도로 개수 M 입력
        K = Integer.parseInt(st.nextToken()); // 목표 거리 K 입력
        X = Integer.parseInt(st.nextToken()); // 출발 도시 X 입력

        dist = new int[N + 1]; // 최단 거리 배열 생성

        // 처음에는 모든 도시를 방문하지 않은 상태로 -1로 초기화
        Arrays.fill(dist, -1);

        // 도시 번호는 1번부터 시작하므로 N+1개 리스트 생성
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()); // 출발 도시 A
            int B = Integer.parseInt(st.nextToken()); // 도착 도시 B

            graph.get(A).add(B); // A -> B 단방향 도로 저장
        }

        bfs(X); // 출발 도시 X부터 BFS 수행

        boolean check = false; // 거리 K인 도시가 존재하는지 확인하는 변수

        // 1번 도시부터 N번 도시까지 확인
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) { // 최단 거리가 정확히 K라면
                sb.append(i).append("\n"); // 해당 도시 번호 출력 내용에 추가
                check = true; // 존재 여부 true로 변경
            }
        }

        // 거리 K인 도시가 하나도 없다면 -1 출력
        if (!check) {
            System.out.println(-1);
        } else {
            System.out.print(sb);
        }
    }

    static void bfs(int start) {

        Queue<Integer> queue = new LinkedList<>(); // BFS를 위한 큐 생성
        queue.offer(start); // 시작 도시를 큐에 삽입
        dist[start] = 0; // 시작 도시까지의 거리는 0

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {

            int now = queue.poll(); // 현재 도시 꺼내기

            // 현재 도시와 연결된 다른 도시들 확인
            for (int next : graph.get(now)) {

                // 아직 방문하지 않은 도시라면
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1; // 최단 거리 갱신
                    queue.offer(next); // 큐에 삽입
                }
            }
        }
    }
}