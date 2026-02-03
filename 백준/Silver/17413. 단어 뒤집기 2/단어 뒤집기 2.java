import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringBuilder out = new StringBuilder();
        StringBuilder word = new StringBuilder();
        boolean inTag = false;

        for (int i=0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '<') {
                //태그 시작 전 쌓인 단어들 처리
                if (word.length() > 0) {
                    out.append(word.reverse()); // out에 word 뒤집어서 집어넣어
                    word.setLength(0); // word 클리어
                }
                inTag = true;
                out.append(c); // '<' 여기선 이거만 출력
            } else if (c == '>') {
                inTag = false;
                out.append(c);
            } else if (inTag) {   //inTag가 true면
                out.append(c);
            } else {
                //태그 밖
                if (c == ' ') { //공백이 나왔을때
                    if (word.length() > 0) { //word가 존재하면
                        out.append(word.reverse()); //뒤집어서 out에
                        word.setLength(0);
                    }
                    out.append(' '); //공백 출력
                } else {
                    word.append(c); //공백이 나오기 전에는 out에 원래상태로 집어넣어. 나중에 공백나오면 한꺼번에 처리
                }
            }
        }
        //마지막 단어 처리 - 태그 밖에선 공백이 나와야만 리버스 처리를 했기에 마지막 단어는 처리 안된 상태
        if(word.length() > 0){
            out.append(word.reverse());
        }
        System.out.print(out);

    }
}