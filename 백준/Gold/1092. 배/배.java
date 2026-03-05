/*
가장 무거운 박스부터 옮기는 것이 효율적이므로 크레인은 무게 제한 기준 내림차순,
박스는 무게 기준 오름차순 정렬한 후 가장 무거운 박스부터 확인한다.
모든 박스를 옮길 때까지 반복하여 최소 시간을 계산한다.
*/

import java.io.*;
import java.util.*;

public class Main{

    static int N, M; // 크레인 개수 N, 박스 개수 M
    static ArrayList<Integer> crane = new ArrayList<>(); // 크레인 무게 제한 저장 리스트
    static ArrayList<Integer> box = new ArrayList<>(); // 박스 무게 저장 리스트

    public static void main(String[] args) throws IOException {

        // 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 출력을 위한 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        boolean check = true; // 모든 박스를 옮길 수 있는지 확인하는 변수 check

        N = Integer.parseInt(br.readLine()); // 크레인 개수 입력 받아

        st = new StringTokenizer(br.readLine()," ");

        int max = -1; // 크레인의 최대 무게 제한 저장 변수 max

        // 크레인 무게 제한 입력 및 최대 무게 제한 계산
        for(int i=0;i<N;i++){
            crane.add(Integer.parseInt(st.nextToken()));
            max = Math.max(max, crane.get(i));
        }

        M = Integer.parseInt(br.readLine()); // 박스 개수 입력
        st = new StringTokenizer(br.readLine()," ");
  
        for(int i=0;i<M;i++){  // 박스 무게를 입력 받는데
            box.add(Integer.parseInt(st.nextToken())); 

            // 크레인이 들 수 있는 최대 무게보다 무거운 박스가 있다면
            // 모든 박스를 옮길 수 없으니까
            if(box.get(i) > max){
                check  = false; //check을 false로 설정하고
                bw.write("-1"); // 결과 -1 출력
                break; //반복문 끝냄
            }
        }

        // 모든 박스를 옮길 수 있는 경우
        if(check){

            crane.sort(Collections.reverseOrder()); // 크레인 무게 제한 내림차순 정렬
            Collections.sort(box); // 박스 무게 오름차순 정렬

            int answer = 0; // 걸린 시간
            int cur = 0, index = M-1; // cur = 크레인 인덱스, index = 박스 인덱스

            // 모든 박스를 옮길 때까지 반복
            while(!box.isEmpty()){

                // 현재 크레인이 현재 박스를 옮길 수 있는 경우
                if(box.get(index) <= crane.get(cur)){
                    box.remove(index); // 해당 박스 제거
                    cur++; // 다음 크레인으로 이동
                }

                index--; // 다음 박스 확인

                // 한 사이클(모든 크레인 사용 또는 박스 끝까지 확인) 완료 시
                if(cur == N || index == -1){

                    cur = 0; // 크레인 인덱스 초기화
                    index = box.size()-1; // 박스 인덱스 다시 마지막으로
                    answer++; // 시간 1 증가
                }
            }

            bw.write(answer + ""); // 최소 시간 출력
        }

        bw.flush(); // 결과 출력
        bw.close();
        br.close();
    }
}