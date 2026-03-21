/*
각 기차의 좌석 상태를 비트마스크로 관리하면 사람 태우기, 내리기, 뒤로 밀기, 앞으로 당기기를 빠르게 처리할 수 있다.
모든 명령 수행 후 좌석 배치가 서로 다른 기차의 개수를 집합으로 세면 된다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N, M; // 기차 개수 N, 명령 개수 M
    static int[] train; // 각 기차의 좌석 상태를 비트로 저장하는 배열

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 기차 개수 N 입력
        M = Integer.parseInt(st.nextToken()); // 명령 개수 M 입력

        train = new int[N + 1]; // 1번 기차부터 사용하기 위해 N+1 크기로 생성

        // 명령 개수만큼 반복
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken()); // 명령 번호
            int idx = Integer.parseInt(st.nextToken()); // 기차 번호

            // 1번 명령 : x번 좌석에 사람 태우기
            if (command == 1) {
                int x = Integer.parseInt(st.nextToken());
                train[idx] |= (1 << (x - 1));
            }
            // 2번 명령 : x번 좌석 사람 내리기
            else if (command == 2) {
                int x = Integer.parseInt(st.nextToken());
                train[idx] &= ~(1 << (x - 1));
            }
            // 3번 명령 : 뒤로 한 칸 밀기
            else if (command == 3) {
                train[idx] = train[idx] << 1; // 한 칸 뒤로 이동
                train[idx] &= ((1 << 20) - 1); // 20번 좌석까지만 남기기
            }
            // 4번 명령 : 앞으로 한 칸 당기기
            else if (command == 4) {
                train[idx] = train[idx] >> 1; // 한 칸 앞으로 이동
            }
        }

        HashSet<Integer> set = new HashSet<>(); // 서로 다른 좌석 상태 저장 집합

        // 각 기차의 최종 좌석 상태를 집합에 저장
        for (int i = 1; i <= N; i++) {
            set.add(train[i]);
        }

        System.out.println(set.size()); // 은하수를 건널 수 있는 기차 수 출력
    }
}