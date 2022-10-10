// LeetCode Template
// Created by dvlcis @ 2022/10/4 21:43
// 

#include  <stdio.h>
#include  <iostream>

using namespace std;

class Problem_1820_FindLetter {
public:
    char findLetter(string &str) {
        int small[26] = {0};
        int big[26] = {0};
        for (char ch: str) {
            if (ch >= 'a' && ch <= 'z') {
                small[ch - 'a']++;
            } else {
                big[ch - 'A']++;
            }
        }
        char ans = '~';
        for (int i = 25; i >= 0; i--) {
            if (small[i] > 0 && big[i] > 0) {
                ans = (char)(i + 'A');
                break;
            }
        }
        return ans;
    }
};

// for test
int main() {
    Problem_1820_FindLetter *sl = new Problem_1820_FindLetter();
    string s = "aAbBcD";
    auto ans = sl->findLetter(s);
    cout<<ans<<endl;

    return 0;
}