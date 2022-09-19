//
// https://leetcode.cn/contest/autox2023/problems/l9HbCJ/
// AutoX-1. 网页瀑布流
//
//

#include <iostream>
#include <string>
#include <queue>

using namespace std;

class Solution {
public:
    int getLengthOfWaterfallFlow(int num, vector<int> &block) {
        if (block.empty()) {
            return 0;
        }
        // NOTE: 默认是大根堆, 需要设置为小根堆
        priority_queue<int, vector<int>, greater<> > pq;
        int size = block.size();
        int ans = 0;
        for (int i = 0; i < min(num, size); i++) {
            pq.push(block[i]);
            ans = max(ans, block[i]);
        }
        if (size < 3) {
            return ans;
        }
        int i = num;
        while (i < size) {
            int cur = pq.top();
            pq.pop();
            cur += block[i];
            ans = max(ans, cur);
            pq.push(cur);
            i++;
        }
        return ans;
    }
};


int main() {
    Solution *sl = new Solution();
    int num = 3;
    vector<int> block;
    block.push_back(5);
    block.push_back(9);
    block.push_back(8);
    block.push_back(6);
    auto ans = sl->getLengthOfWaterfallFlow(num, block);
    cout << ans << endl;


    return 0;
}

