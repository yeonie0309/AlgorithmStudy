#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<int> cards(N);
    for (int i = 0; i < N; i++) {
        cin >> cards[i];
    }

    // 이분 탐색을 위한 정렬
    sort(cards.begin(), cards.end());

    int M;
    cin >> M;

    while (M--) {
        int x;
        cin >> x;

        // 이분 탐색으로 존재 여부 확인
        if (binary_search(cards.begin(), cards.end(), x))
            cout << 1 << " ";
        else
            cout << 0 << " ";
    }

    return 0;
}
