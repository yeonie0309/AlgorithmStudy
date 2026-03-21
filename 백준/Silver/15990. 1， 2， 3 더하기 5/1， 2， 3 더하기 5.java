/*
같은 수를 연속해서 사용할 수 없으므로 dp[n][마지막에 사용한 수] 형태로 경우의 수를 저장한다.
이전 수와 다른 수만 이어 붙이도록 점화식을 세워 각 n에 대한 정답을 구한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1000000009;
    static long[][] dp = new long[100001][4]; // dp[n][k] = 합이 n이고 마지막 수가 k인 경우의 수

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수 T 입력

        // 기본값 세팅
        dp[1][1] = 1; // 1은 [1] 한 가지
        dp[2][2] = 1; // 2는 [2] 한 가지
        dp[3][1] = 1; // 3은 [2+1]
        dp[3][2] = 1; // 3은 [1+2]
        dp[3][3] = 1; // 3은 [3]

        // 4부터 100000까지 미리 dp 계산
        for (int i = 4; i <= 100000; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % MOD; // 마지막이 1이면 이전은 2 또는 3
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % MOD; // 마지막이 2이면 이전은 1 또는 3
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % MOD; // 마지막이 3이면 이전은 1 또는 2
        }

        // 테스트케이스마다 정답 출력
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine()); // 만들 숫자 n 입력
            long answer = (dp[n][1] + dp[n][2] + dp[n][3]) % MOD; // 마지막 수가 1,2,3인 경우 합치기
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }
}