import java.io.BufferedReader;      // 빠른 입력을 위한 BufferedReader 사용
import java.io.InputStreamReader;   // 입력 스트림 연결용
import java.io.IOException;         // 예외 처리를 위한 import
 
public class Main {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 객체 생성
        
        char[] str1 = br.readLine().toCharArray(); // 첫 번째 문자열 입력 후 문자 배열로 변환
        char[] str2 = br.readLine().toCharArray(); // 두 번째 문자열 입력 후 문자 배열로 변환
        
        int length_1 = str1.length; // 첫 번째 문자열 길이 저장
        int length_2 = str2.length; // 두 번째 문자열 길이 저장
        
        // 공집합 표현을 위해 인덱스가 한 줄씩 추가 됨 
        int[][] dp = new int[length_1 + 1][length_2 + 1]; // LCS 길이 저장할 DP 배열 생성
        
        // 1부터 시작 (index 0 은 공집합이므로 0의 값을 갖고있음) 
        for(int i = 1; i <= length_1; i++) { // 첫 번째 문자열 기준 반복
            for(int j = 1; j <= length_2; j++) { // 두 번째 문자열 기준 반복
                
                // (i-1)과 (j-1) 번째 문자가 서로 같다면  
                if(str1[i - 1] == str2[j - 1]) {
                    // 대각선 위 (i-1, j-1)의 dp에 +1 한 값으로 갱신 
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 공통 문자 추가
                }
                
                // 같지 않다면 이전 열(i-1)과 이전 행(j-1)의 값 중 큰 것으로 갱신  
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 둘 중 최대값 선택
                }
            }
        }
        
        System.out.println(dp[length_1][length_2]); // 최종 LCS 길이 출력
    }
}