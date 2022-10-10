// LeetCode Template
// Created by dvlcis @ 2022/10/6 23:11
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class Problem_31_NextPermutation {

public:
    void nextPermutation(vector<int> &nums) {
        int N = nums.size();
        // 从右往左找第一个降序的位置 firstLess
        int firstLess = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }
        if (firstLess == -1) { // 全是升序, 即最大, 整体逆序
            // Reverses the order of the elements in the range [first,last)
            reverse(nums.begin(), nums.end());
            return;
        }
        int rightClosestMore = -1; // 一定存在
        // 从N-1~firstLess+1 找最靠右的、同时比nums[firstLess]大的数，位置在哪, 可以二分(但是没必要了)
        for (int i = N - 1; i > firstLess; i--) {
            if (nums[i] > nums[firstLess]) {
                rightClosestMore = i;
                break;
            }
        }
        swap(nums[firstLess], nums[rightClosestMore]);
        reverse(nums.begin() + firstLess + 1, nums.end());
    }
};

// for test
int main() {
    Problem_31_NextPermutation *sl = new Problem_31_NextPermutation();

    return 0;
}