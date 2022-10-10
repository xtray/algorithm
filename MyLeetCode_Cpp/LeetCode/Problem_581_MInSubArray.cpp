// LeetCode Template
// Created by dvlcis @ 2022/10/4 00:14
// 

#include  <stdio.h>
#include  <iostream>
#include  <vector>

using namespace std;

class Problem_581_MInSubArray {
public:
    int findUnsortedSubarray(vector<int> &nums) {
        // 从左往右小-->大(记录最大)
        // 从右往左大-->小(记录最小)
        int N = nums.size();
        int maxLeft = nums[0];
        int minRight = nums[N - 1];
        int posR = -1;
        int posL = -1;
        for (int i = 1; i < N; i++) {
            if (nums[i] < maxLeft) {
                posR = i;
            }
            maxLeft = max(maxLeft, nums[i]);
        }
        if (posR == -1) { // 都是有序的
            return 0;
        }
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] > minRight) {
                posL = i;
            }
            minRight = min(minRight, nums[i]);
        }
        return posR - posL + 1;
    }

    int findUnsortedSubarray1(vector<int> &nums) {
        // 从左往右小-->大(记录最大)
        // 从右往左大-->小(记录最小)
        int N = nums.size();
        int maxLeft = nums[0];
        int minRight = nums[N - 1];
        int posR = -1;
        int posL = -1;
        for (int i = 1; i < N; i++) {
            if (nums[i] < maxLeft) {
                posR = i;
            } else {
                maxLeft = nums[i];
            }
        }
        if (posR == -1) { // 都是有序的
            return 0;
        }
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] > minRight) {
                posL = i;
            } else {
                minRight = nums[i];
            }
        }
        return posR - posL + 1;
    }
};

// for test
int main() {
    Problem_581_MInSubArray *sl = new Problem_581_MInSubArray();

    return 0;
}