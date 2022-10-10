#include <vector>
#include <iostream>

using namespace std;

class Problem_327_RangeSum {
public:

    int merge(vector<long> &arr, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L; // [L...R)
        int windowR = L;
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= M && arr[windowR] <= max) {// while 出来, windowR是不合适的位置
                windowR++;
            }
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        int p1 = L;
        int p2 = M + 1;
        vector<long> help(R - L + 1);
        int i = 0;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int i = 0; i < help.size(); i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    int process(vector<long> &sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) +
               process(sum, M + 1, R, lower, upper) +
               merge(sum, L, M, R, lower, upper);
    }

    int countRangeSum(vector<int> &nums, int lower, int upper) {
        if (nums.size() == 0) {
            return 0;
        }
        int N = nums.size();
        vector<long> sum(N, 0);
        // sum.push_back(nums[0]);
        sum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, N - 1, lower, upper);
    }
};

// for test
int main() {

    vector<int> nums = {-2, 5, -1};
    int lower = -2, upper = 2; // 3
    auto sl = new Problem_327_RangeSum();
    auto ans = sl->countRangeSum(nums, lower, upper);
    cout << ans << endl;
    return 0;
}

