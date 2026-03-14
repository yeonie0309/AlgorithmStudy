/*
스위치 상태를 배열에 저장한 뒤 학생의 성별에 따라 규칙대로 스위치를 반전한다.
남학생은 받은 번호의 배수에 해당하는 스위치들을 모두 바꾸고,
여학생은 받은 번호를 중심으로 좌우가 같은 구간을 최대한 찾은 뒤 그 구간의 스위치를 모두 바꾼다.
마지막에는 스위치 상태를 1번부터 차례대로 한 줄에 20개씩 출력한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N; // 스위치 개수 N
    static int[] sw; // 스위치 상태 저장 배열

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에서 스위치 개수 N 입력
        N = Integer.parseInt(br.readLine());

        sw = new int[N + 1]; // 스위치 번호를 1번부터 사용하기 위해 N+1 크기로 생성

        // 둘째 줄의 스위치 상태 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sw[i] = Integer.parseInt(st.nextToken()); // 각 스위치의 현재 상태 저장
        }

        // 학생 수 입력
        int student = Integer.parseInt(br.readLine());

        // 학생 수만큼 반복
        for (int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine());

            int gender = Integer.parseInt(st.nextToken()); // 성별 입력 (남학생 1, 여학생 2)
            int num = Integer.parseInt(st.nextToken()); // 받은 스위치 번호 입력

            // 남학생인 경우
            if (gender == 1) {
                // num의 배수 번호 스위치들을 모두 반전
                for (int j = num; j <= N; j += num) {
                    change(j);
                }
            }

            // 여학생인 경우
            else {
                int left = num;  // 중심에서 왼쪽으로 확장할 인덱스
                int right = num; // 중심에서 오른쪽으로 확장할 인덱스

                // 좌우가 범위 안에 있고 값이 같으면 계속 확장
                while (left - 1 >= 1 && right + 1 <= N && sw[left - 1] == sw[right + 1]) {
                    left--;
                    right++;
                }

                // 최종으로 찾은 대칭 구간의 스위치들을 모두 반전
                for (int j = left; j <= right; j++) {
                    change(j);
                }
            }
        }

        // 결과 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 스위치 상태를 1번부터 차례대로 출력
        for (int i = 1; i <= N; i++) {
            sb.append(sw[i]).append(" ");

            // 20개마다 줄바꿈
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }

        // 출력
        System.out.print(sb);
    }

    static void change(int idx) {
        // 스위치가 0이면 1로, 1이면 0으로 반전
        if (sw[idx] == 0) {
            sw[idx] = 1;
        } else {
            sw[idx] = 0;
        }
    }
}