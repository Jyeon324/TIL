#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n;
    string s1, s2;

    cin >> n;

    while(n--){
        int a[26] = {};

        cin >> s1 >> s2;

        for(auto c: s1) a[c-'a']++; // 첫 번째 문자열의 각 문자 + 1
        for(auto c: s2) a[c-'a']--; // 두 번째 문자열의 각 문자 - 1

        // 0이 아닌 배열의 요소가 있으면 다른 문자가 있으니 false
        bool isPossible = true;
        
        for(int i : a)
            if(i != 0) isPossible = false;

        if(isPossible) cout << "Possible\n";
        else cout << "Impossible\n";
    }
}