import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    // A~F인지 빠르게 확인하는 함수
    private static boolean isAF(char c) {
        return c >= 'A' && c <= 'F';
    }

    // 문자열 s가 규칙을 만족하는지 검사
    private static boolean isInfected(String s) {
        int n = s.length();
        int i = 0;

        // 0) 유효 문자는 A~F만 존재한다고 가정하지 말고 체크해주는 게 안전함
        //    (문제 입력은 대문자 알파벳이지만, 혹시 모를 실수를 방지)
        //    다만 이 문제는 주로 A~F만 의미가 있으니, 여기서 걸러도 되고 안 걸러도 됨.
        for (int k = 0; k < n; k++) {
            if (!isAF(s.charAt(k))) return false;
        }

        // 1) 앞쪽 optional [A-F]?
        //    "앞쪽 1글자가 있을 수도 있다"를 처리해야 하는데,
        //    다음 구간이 A+ 이므로, 현재 문자가 'A'가 아니면 그 1글자를 optional로 소비해도 됨.
        //    (현재가 'A'면 optional을 굳이 소비하지 않고 바로 A+로 들어가는 게 편함)
        if (i < n && s.charAt(i) != 'A') {
            // s[i]는 A가 아니고 A~F 중 하나 -> optional 1글자 소비
            i++;
        }

        // 2) A+  (A가 1개 이상이어야 함)
        if (i >= n || s.charAt(i) != 'A') return false;
        while (i < n && s.charAt(i) == 'A') i++;

        // 3) F+  (F가 1개 이상이어야 함)
        if (i >= n || s.charAt(i) != 'F') return false;
        while (i < n && s.charAt(i) == 'F') i++;

        // 4) C+  (C가 1개 이상이어야 함)
        if (i >= n || s.charAt(i) != 'C') return false;
        while (i < n && s.charAt(i) == 'C') i++;

        // 5) 뒤쪽 optional [A-F]?
        //    남은 문자가 0개면 OK (optional 없음)
        //    남은 문자가 1개면 그 1개가 A~F면 OK (optional 1개)
        //    남은 문자가 2개 이상이면 optional 조건 위반 -> false
        int remain = n - i;
        if (remain == 0) return true;
        if (remain == 1) return isAF(s.charAt(i));
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            String s = br.readLine();

            if (isInfected(s)) out.append("Infected!\n");
            else out.append("Good\n");
        }

        System.out.print(out);
    }
}
