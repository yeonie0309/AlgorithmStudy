import java.io.BufferedReader;      // 빠른 입력을 위한 BufferedReader 사용
import java.io.IOException;         // 예외 처리를 위한 import
import java.io.InputStreamReader;   // 입력 스트림 연결용
import java.util.StringTokenizer;   // 공백 기준 빠른 파싱용

public class Main {
    // 합을 구하는 문제 + 인덱스가 1부터 시작하면 누적합(2차원 prefix sum) 의심 // 아이디어 설명
    static int N;                   // 행 개수
    static int M;                   // 열 개수
    static int peopleDP[][];        // 2차원 누적합 배열
    static int K;                   // 질의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 객체 생성
        StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄 토큰 분리

        N = Integer.parseInt(st.nextToken()); // N 입력받기
        M = Integer.parseInt(st.nextToken()); // M 입력받기

        peopleDP = new int[N + 1][M + 1]; // 누적합 배열 생성 (1-based)

        for (int i = 1; i <= N; i++) { // 1행부터 N행까지 반복
            st = new StringTokenizer(br.readLine()); // 한 줄 입력받기
            for (int j = 1; j <= M; j++) { // 1열부터 M열까지 반복
                int value = Integer.parseInt(st.nextToken()); // 현재 값 입력받기
                peopleDP[i][j] = value + peopleDP[i - 1][j] + peopleDP[i][j - 1] - peopleDP[i - 1][j - 1]; // 누적합 계산
            }
        }
        // -----입력을 받으면서 누적합으로 바꿈 // 설명 주석

        K = Integer.parseInt(br.readLine()); // 질의 개수 입력받기

        StringBuilder sb = new StringBuilder(); // 출력 누적용 StringBuilder 생성

        for (int k = 0; k < K; k++) { // 질의 개수만큼 반복
            st = new StringTokenizer(br.readLine()); // 질의 한 줄 입력받기

            int x1 = Integer.parseInt(st.nextToken()); // 시작 x 입력받기
            int y1 = Integer.parseInt(st.nextToken()); // 시작 y 입력받기
            int x2 = Integer.parseInt(st.nextToken()); // 끝 x 입력받기
            int y2 = Integer.parseInt(st.nextToken()); // 끝 y 입력받기

            sb.append(peopleDP[x2][y2] - peopleDP[x1 - 1][y2] - peopleDP[x2][y1 - 1] + peopleDP[x1 - 1][y1 - 1]) // 구간합 계산
              .append("\n"); // 줄바꿈 추가
        }

        System.out.println(sb.toString()); // 결과 출력
    }
}