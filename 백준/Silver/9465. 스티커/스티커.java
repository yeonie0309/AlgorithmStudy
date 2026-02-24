import java.io.BufferedReader;      // 빠른 입력을 위한 BufferedReader 사용
import java.io.IOException;         // 예외 처리를 위한 import
import java.io.InputStreamReader;   // 입력 스트림 연결용

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 객체 생성

        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력받기

        for (int i = 0; i < tc; i++) { // 테스트 케이스만큼 반복
            int n = Integer.parseInt(br.readLine()); // 스티커 열 개수 입력받기

            // initialize
            int[][] stickers = new int[2][n+1]; // 스티커 점수 저장 배열
            int[][] dp = new int[2][n+1]; // dp 배열 (해당 위치까지의 최대 점수)

            for(int j=0; j<2; j++){ // 두 행 입력받기
                String[] inputs = br.readLine().split(" "); // 한 줄 입력받아 공백 분리
                for (int k = 1; k <= n; k++) { // 1열부터 n열까지 저장
                    stickers[j][k] = Integer.parseInt(inputs[k-1]); // 스티커 점수 저장
                }
            }

            // 첫번째 column은 자기 자신이 최대이므로 자기 자신으로 초기화
            dp[0][1] = stickers[0][1]; // 1열 위쪽 스티커 선택
            dp[1][1] = stickers[1][1]; // 1열 아래쪽 스티커 선택

            for (int j = 2; j <= n; j++) { // 2열부터 n열까지 반복
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + stickers[0][j]; 
                // 위쪽 선택 시, 이전 열 또는 그 전 열의 아래쪽 최대값 + 현재 점수

                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + stickers[1][j]; 
                // 아래쪽 선택 시, 이전 열 또는 그 전 열의 위쪽 최대값 + 현재 점수
            }

            System.out.println(Math.max(dp[0][n], dp[1][n])); // 마지막 열에서 최대값 출력
        }
    }
}