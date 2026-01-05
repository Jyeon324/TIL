#include <bits/stdc++.h>
using namespace std;

int alphabet[30];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    string str;

    cin >> str;


    for(int i=0; i<str.length(); i++){
        alphabet[str[i] - 'a']++;
    }

    for(int i=0; i<26; i++)
        cout << alphabet[i] << ' ';
}