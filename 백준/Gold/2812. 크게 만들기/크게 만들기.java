import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 콘솔 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 숫자의 길이 N
        int K = Integer.parseInt(st.nextToken()); // 제거할 숫자의 개수 K

        String num = br.readLine(); // N자리 숫자 입력

        Stack<Character> stack = new Stack<>(); // 숫자를 저장할 스택

        // 숫자를 하나씩 확인
        for(int i = 0; i < N; i++) {

            char c = num.charAt(i); // 현재 숫자

            // 스택이 비어있지 않고 제거할 수 있으며
            // 스택의 마지막 숫자보다 현재 숫자가 더 크다면 제거
            while(!stack.isEmpty() && K > 0 && stack.peek() < c) {
                stack.pop(); // 작은 숫자 제거
                K--; // 제거 횟수 감소
            }

            stack.push(c); // 현재 숫자 스택에 추가
        }

        // 아직 제거할 숫자가 남아 있다면 뒤에서 제거
        while(K > 0) {
            stack.pop();
            K--;
        }

        StringBuilder sb = new StringBuilder();

        // 스택에 남아있는 숫자를 결과로 저장
        for(char c : stack) {
            sb.append(c);
        }

        System.out.println(sb.toString()); // 가장 큰 수 출력
    }
}