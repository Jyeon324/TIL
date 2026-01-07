#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int N, K;
    cin >> N >> K;
    
    list<int> L;
    for(int i=1; i<=N; i++) L.push_back(i);
    
    auto it = L.begin();
    cout << "<";
    
    while(!L.empty()){
        // K-1번 다음 칸으로 이동
        for(int i=0; i<K-1; i++) {
            it++;
            if(it == L.end()) it = L.begin(); // 끝에 도달하면 처음으로
        }
        
        // 현재 가리키는 원소 출력
        cout << *it;
        
        // 원소 삭제 후 다음 반복자 획득
        it = L.erase(it);
        if(it == L.end()) it = L.begin(); // 삭제 후 끝이면 다시 처음으로
        
        if(!L.empty()) cout << ", "; // 아직 남은 원소가 있다면 쉼표 출력
    }
    cout << ">";
}