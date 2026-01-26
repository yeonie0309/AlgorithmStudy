#include <iostream>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    priority_queue<int, vector<int>, greater<int>> pq;

    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            int temp;
            cin >> temp;
            pq.push(temp);
						if (pq.size() > n) {
						    pq.pop();
						}
        }
    }
    cout << pq.top();
    return 0;
}