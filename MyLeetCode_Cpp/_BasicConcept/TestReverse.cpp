// LeetCode Template
// Created by dvlcis @ 2022/10/6 23:29
// 

// reverse algorithm example
#include <iostream>     // std::cout
#include <algorithm>    // std::reverse
#include <vector>       // std::vector

int main() {
    std::vector<int> myVec;

    // set some values:
    for (int i = 1; i < 10; ++i) {
        myVec.push_back(i);   // 1 2 3 4 5 6 7 8 9
    }

    std::reverse(myVec.begin(), myVec.end());    // 9 8 7 6 5 4 3 2 1

    // print out content:
    std::cout << "myVec: ";
    for (auto iter = myVec.begin(); iter < myVec.end(); iter++) {
        std::cout << ' ' << *iter;
    }
    std::cout << std::endl;

    return 0;
}