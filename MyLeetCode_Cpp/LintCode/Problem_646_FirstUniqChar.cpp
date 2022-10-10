// LeetCode Template
// Created by dvlcis @ 2022/10/6 22:55
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class Problem_646_FirstUniqChar {
public:
    int firstUniqChar(string &s) {
        char cnt[128] = {0};
        int N = s.size();
        for (char ch: s) {
            cnt[ch]++;
        }
        for (int i = 0; i < N; i++) {
            if (cnt[s[i]] == 1) {
                return i;
            }
        }
        return -1;
    }
};

// for test
int main() {
    Problem_646_FirstUniqChar *sl = new Problem_646_FirstUniqChar();
    string s = "lintcode";
    auto ans = sl->firstUniqChar(s);
    cout << ans << endl;

    return 0;
}