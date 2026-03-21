/*
심사 완료 시간의 최솟값을 구해야 하므로 이분 탐색을 사용한다.
특정 시간 안에 모든 사람을 심사할 수 있는지 계산하여 가능한 최소 시간을 찾는다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N, M; // 심사대 개수 N, 사람 수 M
    static long[] time; // 각 심사대의 심사 시간 저장 배열

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 심사대 개수 N 입력
        M = Integer.parseInt(st.nextToken()); // 사람 수 M 입력

        time = new long[N]; // 심사 시간 배열 생성

        long max = 0; // 가장 오래 걸리는 심사 시간 저장 변수

        // 각 심사대의 심사 시간 입력
        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
            max = Math.max(max, time[i]);
        }

        long left = 1; // 최소 시간
        long right = max * M; // 최악의 경우 시간
        long answer = right; // 정답 후보

        // 이분 탐색 시작
        while (left <= right) {
            long mid = (left + right) / 2; // 중간 시간
            long count = 0; // mid 시간 동안 심사 가능한 사람 수

            // 각 심사대가 mid 시간 동안 처리 가능한 사람 수 누적
            for (int i = 0; i < N; i++) {
                count += mid / time[i];

                // 이미 M명 이상이면 더 볼 필요 없음
                if (count >= M) break;
            }

            // M명 이상 심사 가능하면 시간을 더 줄여봄
            if (count >= M) {
                answer = mid;
                right = mid - 1;
            }
            // M명 미만이면 시간이 부족하므로 늘림
            else {
                left = mid + 1;
            }
        }

        System.out.println(answer); // 최소 시간 출력
    }
}