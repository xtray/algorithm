// LeetCode Template
// Created by dvlcis @ 2022/10/4 00:06
// 

#include  <stdio.h>
#include  <iostream>

using namespace std;

class Problem_921_MinAddParentheses {
public:
    int minAddToMakeValid(string s) {
        int cnt = 0;
        int add = 0;
        for (char ch: s) {
            if (ch == '(') cnt++;
            else {
                if (--cnt < 0) {
                    cnt = 0;
                    add++;
                }
            }
        }
        return cnt + add;
    }
};

// for test
int main() {
    Problem_921_MinAddParentheses *sl = new Problem_921_MinAddParentheses();

    return 0;
}