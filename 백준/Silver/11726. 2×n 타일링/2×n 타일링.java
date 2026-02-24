import java.io.BufferedReader;      // 빠른 입력을 위한 BufferedReader 사용
import java.io.IOException;         // 예외 처리를 위한 import
import java.io.InputStreamReader;   // 입력 스트림 연결용

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 객체 생성
		int n = Integer.parseInt(br.readLine()); // n 입력받기
		
		if(n == 1) { // n이 1일 경우
			System.out.println(1); // 결과 출력
			return; // 프로그램 종료
		}
		
		int[] dp = new int[n + 1]; // dp 배열 생성 (1 ~ n)
		
		dp[1] = 1; // 2x1 타일링 방법은 1가지
		dp[2] = 2; // 2x2 타일링 방법은 2가지
		
		for(int i = 3; i <= n; i++) { // 3부터 n까지 반복
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007; // 점화식 적용 + 10007로 나눈 나머지
		}
		
		System.out.println(dp[n]); // 결과 출력
	}
}