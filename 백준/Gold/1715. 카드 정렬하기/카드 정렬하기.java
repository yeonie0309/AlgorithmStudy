import java.io.*;
import java.util.*;
 
public class Main {
 
    static int N;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(Integer.parseInt(st.nextToken()));
        }
        int sum = 0;
 
        // 초기 요소의 갯수가 1개라면 비교 횟수가 존재 X
        // 누적 합 과정 중 pq의 갯수가 한개가 남으면 비교가 끝남
        while ((pq.size() > 1)) {
            int a = pq.poll();
            int b = pq.poll();
            sum += (a + b);
            pq.add(a + b);
        }
        
        System.out.println(sum);
    }
}