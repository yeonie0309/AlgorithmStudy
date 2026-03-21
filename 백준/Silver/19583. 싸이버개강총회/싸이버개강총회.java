/*
개강총회 시작 전까지 채팅한 사람을 집합에 저장한 뒤,
개강총회 종료 후부터 스트리밍 종료 전까지 다시 채팅한 사람이 그 집합에 있으면 출석으로 인정한다.
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken(); // 개강총회 시작 시간
        String E = st.nextToken(); // 개강총회 종료 시간
        String Q = st.nextToken(); // 스트리밍 종료 시간

        HashSet<String> enter = new HashSet<>(); // 시작 시간 이전에 들어온 사람 저장
        HashSet<String> answerSet = new HashSet<>(); // 출석 인정된 사람 저장

        String line;

        // EOF까지 한 줄씩 입력 받기
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue; // 빈 줄이면 넘어감

            st = new StringTokenizer(line);
            String time = st.nextToken(); // 채팅 시간
            String name = st.nextToken(); // 닉네임

            // 시작 시간 이전 또는 같은 시간에 채팅했다면 입장 체크
            if (time.compareTo(S) <= 0) {
                enter.add(name);
            }
            // 종료 시간 이후이면서 스트리밍 종료 전 또는 같은 시간이고,
            // 시작 전에 들어온 사람이라면 출석 인정
            else if (time.compareTo(E) >= 0 && time.compareTo(Q) <= 0) {
                if (enter.contains(name)) {
                    answerSet.add(name);
                }
            }
        }

        System.out.println(answerSet.size()); // 출석한 사람 수 출력
    }
}