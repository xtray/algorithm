// LeetCode Template
// Created by dvlcis @ 2022/10/6 23:41
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class Problem_1800_MaxAscendSum {
public:
    int maxAscendingSum(vector<int> &nums) {
        int sum = 0;
        int maxSum = 0;
        int pre = 0;
        for (int &num: nums) {
            if (num > pre) {
                sum += num;
            } else {
                sum = num;
            }
            maxSum = max(maxSum, sum);
            pre = num;
        }
        return maxSum;
    }
};

// for test
int main() {
    Problem_1800_MaxAscendSum *sl = new Problem_1800_MaxAscendSum();

    return 0;
}