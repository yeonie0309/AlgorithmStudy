#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, X;
    cin >> N >> X;

    vector<int> a(N);
    for (int i = 0; i < N; i++) cin >> a[i];

    long long windowSum = 0;

    for (int i = 0; i < X; i++) windowSum += a[i];

    long long best = windowSum;
    int count = 1;

    for (int i = X; i < N; i++) {
        windowSum = windowSum - a[i - X] + a[i];

        if (windowSum > best) {
            best = windowSum;
            count = 1;
        } else if (windowSum == best) {
            count++;
        }
    }

    if (best == 0) {
        cout << "SAD\n";
    } else {
        cout << best << "\n" << count << "\n";
    }

    return 0;
}
