// LeetCode Template
// Created by dvlcis @ 2022/10/1 00:36
// 

#include  <stdio.h>
#include  <iostream>

using namespace std;


// for test
int main() {
    string s("Hello Word!!!");

    for (auto &c: s) {
        c = toupper(c);
    }

    cout << s << endl;

    return 0;
}