#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int L;
    cin >> L;

    while(L--){
        string s;
        cin >> s;
        
        list<char> L;
        list<char>::iterator cursor = L.begin();

        for(int i=0; i<s.length(); i++){
            if(s[i] == '<'){
                if(cursor != L.begin()) cursor--;
            }
            else if(s[i] == '>'){
                if(cursor != L.end()) cursor++;
            }
            else if(s[i] == '-'){
                if(cursor != L.begin()) cursor = L.erase(--cursor);
            }
            else{
                L.insert(cursor, s[i]);
            }
        }
        
        for(auto c : L) cout << c;
        cout << '\n';
    }
}