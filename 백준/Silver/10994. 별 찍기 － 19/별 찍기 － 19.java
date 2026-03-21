/*
가장 바깥 테두리부터 안쪽으로 들어가며 정사각형 모양의 별을 그리면 된다.
배열에 공백을 채워두고 각 테두리의 위, 아래, 왼쪽, 오른쪽을 별로 바꿔 최종 모양을 출력한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    static char[][] arr; // 별 모양을 저장할 2차원 배열

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 입력 N

        int size = 4 * N - 3; // 전체 배열 크기 계산
        arr = new char[size][size]; // 별 모양 저장 배열 생성

        // 배열 전체를 공백으로 초기화
        for (int i = 0; i < size; i++) {
            Arrays.fill(arr[i], ' ');
        }

        draw(0, size); // 바깥쪽부터 별 그리기

        StringBuilder sb = new StringBuilder();

        // 결과 출력 문자열 만들기
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    // start부터 len 크기의 정사각형 테두리를 그리고 안쪽으로 재귀 호출
    static void draw(int start, int len) {

        // 더 이상 그릴 크기가 없으면 종료
        if (len <= 0) return;

        // 윗변과 아랫변 그리기
        for (int i = start; i < start + len; i++) {
            arr[start][i] = '*';
            arr[start + len - 1][i] = '*';
        }

        // 왼쪽변과 오른쪽변 그리기
        for (int i = start; i < start + len; i++) {
            arr[i][start] = '*';
            arr[i][start + len - 1] = '*';
        }

        // 안쪽 정사각형 그리기
        draw(start + 2, len - 4);
    }
}