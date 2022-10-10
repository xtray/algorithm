// LeetCode Template
// Created by dvlcis @ 2022/10/5 13:33
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class Problem_702_ConcatStr {
public:
    string concatenetedString(string &s1, string &s2) {
        unordered_map<char, bool> map;
        for (char ch: s2) {
            map[ch] = false;
        }
        string ans = "";
        for (char ch: s1) {
            if (map.count(ch) == 1) {
                map[ch] = true;
            } else {
                ans += ch;
            }
        }
        for (char ch: s2) {
            if (!map[ch]) ans += ch;
        }
        return ans;
    }
};

// for test
int main() {
    Problem_702_ConcatStr *sl = new Problem_702_ConcatStr();

    return 0;
}