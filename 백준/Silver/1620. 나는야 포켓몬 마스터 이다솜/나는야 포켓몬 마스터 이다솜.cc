#include <bits/stdc++.h>
using namespace std;

int main() {
    // 1) C++에서 입출력 속도를 높이는 설정입니다.
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    // 2) 번호 -> 이름 저장 (1-based)
    // vector는 "동적 배열"이라고 생각하면 됩니다.
    // 크기를 N+1로 해서 0번은 비워둡니다.
    vector<string> idx_to_name(N + 1);

    // 3) 이름 -> 번호 저장 (해시맵)
    // unordered_map은 해시 기반이라 평균적으로 빠릅니다.
    unordered_map<string, int> name_to_idx;

    // 해시맵 성능 안정화(초보자에게는 필수는 아니지만, 큰 입력에서 좋습니다)
    name_to_idx.reserve(N * 2);
    name_to_idx.max_load_factor(0.7f);

    // 4) 도감 입력
    for (int i = 1; i <= N; i++) {
        string name;
        cin >> name;
        idx_to_name[i] = name;     // 번호 -> 이름
        name_to_idx[name] = i;     // 이름 -> 번호
    }

    // 5) 질의 처리
    for (int i = 0; i < M; i++) {
        string q;
        cin >> q;

        // 첫 글자가 숫자면 번호로 판단
        if (isdigit((unsigned char)q[0])) {
            int num = stoi(q);                 // 문자열 -> 정수
            cout << idx_to_name[num] << "\n";  // 번호 -> 이름
        } else {
            cout << name_to_idx[q] << "\n";    // 이름 -> 번호
        }
    }

    return 0;
}
