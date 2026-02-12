import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] S, W;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        S = new int[N];
        W = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int idx) {
        if (idx == N) {
            int broken = 0;
            for (int i = 0; i < N; i++) if (S[i] <= 0) broken++;
            answer = Math.max(answer, broken);
            return;
        }

        if (S[idx] <= 0) {
            dfs(idx + 1);
            return;
        }

        boolean hit = false;

        for (int j = 0; j < N; j++) {
            if (j == idx) continue;
            if (S[j] <= 0) continue;

            hit = true;

            S[idx] -= W[j];
            S[j] -= W[idx];

            dfs(idx + 1);

            S[idx] += W[j];
            S[j] += W[idx];
        }

        if (!hit) {
            dfs(idx + 1);
        }
    }
}
