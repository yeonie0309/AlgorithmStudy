import java.io.*;
import java.util.*;

public class Main {

///l이 왼쪽포인터, r이 오른쪽 포인터

    // s[l..r] 구간이 회문인지 확인
    static boolean Check(char[] s, int l, int r) { 
        while (l < r) {
            if (s[l] != s[r]) return false; //왼오 다른 구간 - 회문아니게되는구간
            //왼오 포인터 문자가 같은경우 두 포인터 안쪽으로 이동
            l++; 
            r--;
        }
        return true;
    }

    // 0: 회문, 1: 유사회문(한 글자 삭제), 2: 둘 다 아님
    static int classify(char[] s) {
        int l = 0, r = s.length - 1; //포인터 초기설정

        while (l < r) { //이 조건에만 반복
            if (s[l] == s[r]) { //왼오 포인터 문자가 같으면 두 포인터 안쪽으로 이동
                l++; 
                r--;
            } else {
                // 불일치가 처음 발생한 지점에서 "한 번만" 삭제를 시도
                boolean skipLeft = Check(s, l + 1, r); //왼포인터 문자 하나 건너뛰기해서 회문 검사
                boolean skipRight = Check(s, l, r - 1); //오른족 포인터 ,,
                return (skipLeft || skipRight) ? 1 : 2; // 둘중에 하나라도 맞으면 유사회문이니 1리턴 둘다 아니면 2 리턴
            }
        }
        return 0; // 끝까지 맞았으면 회문이니  0리턴
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim()); //입력받을 문자열 개수 T를 입력받아 int로 파싱

        StringBuilder sb = new StringBuilder(); //출력변수 sb (한번에 출력위해)
        for (int i = 0; i < T; i++) {
            char[] s = br.readLine().trim().toCharArray(); //문자열s T번 입력받아
            sb.append(classify(s)).append('\n'); //classify함수로 문자열 회문 검사을 쭉 처리하고 출력변수sb에 넣어
        }
        System.out.print(sb.toString()); //출력
    }
}
