#include <bits/stdc++.h>
using namespace std;


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<long long> a(N);
    for (int i = 0; i < N; i++) cin >> a[i];

    sort(a.begin(), a.end());

    int l = 0, r = N - 1;

    long long bestAbs = LLONG_MAX;
    long long ans1 = a[l], ans2 = a[r];

    while (l < r) {
        long long sum = a[l] + a[r];
        long long curAbs = llabs(sum);

        // 더 0에 가까운 합을 찾았으면 정답 갱신
        if (curAbs < bestAbs) {
            bestAbs = curAbs;
            ans1 = a[l];
            ans2 = a[r];

            // 절댓값 0이면 더 좋아질 수 없으므로 즉시 종료
            if (bestAbs == 0) break;
        }

        // 투 포인터 이동 규칙
        if (sum < 0) {
            // 합이 음수면 0에 가까워지려면 합을 키워야 함 -> l을 오른쪽으로
            l++;
        } else {
            // 합이 양수거나 0이면 합을 줄여야 함 -> r을 왼쪽으로
            r--;
        }
    }

    // 문제 요구: 오름차순 출력 (정렬된 배열에서 l<r이므로 이미 ans1<=ans2)
    cout << ans1 << " " << ans2 << "\n";
    return 0;
}
