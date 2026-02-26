import java.io.BufferedReader;      // 빠른 입력을 위한 BufferedReader 사용
import java.io.InputStreamReader;   // 입력 스트림 연결용
import java.io.IOException;         // 예외 처리를 위한 import
import java.util.StringTokenizer;   // 공백 기준 빠른 파싱용

public class Main {

    static Integer[][] dp;          // dp[i][k] = i번째까지 고려, 무게 k에서 최대 가치 (null이면 미계산)
    static int[] W;                 // 각 물건의 무게 배열
    static int[] V;                 // 각 물건의 가치 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 객체 생성

        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // N, K 입력 라인 토큰화

        int N = Integer.parseInt(st.nextToken()); // 물건 개수 N 입력받기
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 최대 무게 K 입력받기

        W = new int[N]; // 무게 배열 생성
        V = new int[N]; // 가치 배열 생성

        dp = new Integer[N][K + 1]; // dp 배열 생성 (물건 0~N-1, 무게 0~K)

        for (int i = 0; i < N; i++) { // 물건 정보 N개 입력받기
            st = new StringTokenizer(br.readLine(), " "); // 한 줄 입력 토큰화
            W[i] = Integer.parseInt(st.nextToken()); // i번째 물건 무게 입력받기
            V[i] = Integer.parseInt(st.nextToken()); // i번째 물건 가치 입력받기
        }

        System.out.println(knapsack(N - 1, K)); // 결과 출력 (마지막 물건까지, 무게 K)
    }

    static int knapsack(int i, int k) { // i번째까지 고려, 남은/가능 무게 k일 때 최대 가치 반환
        if (i < 0) // 물건을 더 이상 볼 수 없으면
            return 0; // 가치 0 반환

        if (dp[i][k] == null) { // 아직 계산 안 된 상태라면

            if (W[i] > k) { // 현재 물건이 너무 무거워서 못 담으면
                dp[i][k] = knapsack(i - 1, k); // 현재 물건 제외하고 이전 물건들로 계산
            } else { // 현재 물건을 담을 수 있으면
                dp[i][k] = Math.max(
                        knapsack(i - 1, k),                 // 현재 물건 안 담는 경우
                        knapsack(i - 1, k - W[i]) + V[i]     // 현재 물건 담는 경우
                ); // 둘 중 최대값 저장
            }
        }

        return dp[i][k]; // 저장된 결과 반환
    }
}