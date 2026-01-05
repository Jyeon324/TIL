#include <bits/stdc++.h>
using namespace std;

int arr[102];
int N;
int v;
int ans;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    
    for(int i=0; i<N; i++){
        cin >> arr[i];
    }

    cin >> v;

    for(int i=0; i<N; i++)
        if(arr[i] == v) ans++;

    cout << ans;
}