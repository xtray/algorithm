// LeetCode Template
// Created by dvlcis @ 2022/10/6 23:48
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class Problem_2119_TwoTimeReverse {
public:
    bool isSameAfterReversals(int num) {
        string s = to_string(num);
        int zeroCnt = 0;
        int size = s.size();
        for (int i = size - 1; i >= 0; i--) {
            if (s[i] != '0') {
                break;
            }
            zeroCnt++;
        }
        return size == 1 && zeroCnt == 1 || zeroCnt == 0;
    }
};

// for test
int main() {
    Problem_2119_TwoTimeReverse *sl = new Problem_2119_TwoTimeReverse();

    return 0;
}