// LeetCode Template
// Created by dvlcis @ 2022/10/2 09:38
// 

#include  <stdio.h>
#include  <iostream>
#include  <vector>

using namespace std;

class Problem_1313_DecompRLEList {
public:
    vector<int> decompressRLElist(vector<int> &nums) {
        int size = nums.size();
        if ((size & 1) != 0) {
            return nums;
        }
        vector<int> ans;
        for (int i = 0; i + 1 < size; i += 2) {
            int num = nums[i + 1];
            int cnt = nums[i];
            for (int j = 0; j < cnt; j++) {
                ans.push_back(num);
            }
        }
        return ans;
    }
};
