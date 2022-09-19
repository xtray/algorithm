//
// https://leetcode.cn/contest/autox2023/problems/8p6t8R/
//

#include <iostream>
#include <string>
#include <queue>

using namespace std;

class Solution {

public:
    int sum = 0;
    int sumSqure = 0;
    double product = 1;

    vector<double> honeyQuotes(vector<vector<int>> &handle) {
        vector<double> ans;
        int size = 0;
        for (auto h: handle) {
            switch (h[0]) {
                case 1: {
                    size++;
                    sum += h[1];
                    product *= h[1];
                    sumSqure += h[1] * h[1];
                    break;
                }
                case 2: {
                    size--;
                    sum -= h[1];
                    product /= h[1];
                    sumSqure -= h[1] * h[1];
                    break;
                }
                case 3: {
                    if (size == 0) {
                        ans.push_back(-1);
                        break;
                    }
                    ans.push_back((double) sum / size);
                    break;
                }
                case 4: {
                    if (size == 0) {
                        ans.push_back(-1);
                        break;
                    }
                    double x = (double) sum / size;
                    double s = 0;
                    s += sumSqure;
                    s += size * x * x;
                    s -= 2 * x * sum;

                    ans.push_back(s / size);
                    break;
                }
                default:
                    break;
            }
        }
        return ans;
    }
};


// int main() {
//     Solution *sl = new Solution();
//
//     auto ans = sl->honeyQuotes(num, block);
//     cout << ans << endl;
//
//
//     return 0;
// }

