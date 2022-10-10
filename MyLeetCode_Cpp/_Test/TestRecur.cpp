// LeetCode Template
// Created by dvlcis @ 2022/10/1 16:46
// 

#include  <stdio.h>
#include  <iostream>

using namespace std;

int foo(int n) {
    int s;
    if (n <=1) {
        s = 1;
        return s;
    }
    s = foo(n/2) *2;
    return s;
}




// for test
int main() {

    return 0;
}