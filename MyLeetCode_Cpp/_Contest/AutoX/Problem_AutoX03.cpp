//
// https://leetcode.cn/contest/autox2023/problems/BjAFy9/
//

#include <iostream>
#include <string>
#include <queue>
#include <map>
#include <unordered_map>

using namespace std;

class Solution {
public:


    long long minCostToTravelOnDays(vector<int> &days, vector<vector<int>> &tickets) {
        // dp[i] : 当前来到位置i, 返回从i位置购票到最后一天的最少费用
        return process(days, tickets, 0);
    }

    // 当前来到第i次出行, 尝试每一种票策略, 返回覆盖到最后的时间的最小费用
    long long process(vector<int> days, vector<vector<int>> tickets, int index) {
        int N = days.size();
        if (index == N) {
            return 0;
        }
        // 尝试每一个购票策略
        long cost = LONG_MAX;
        for (auto &ticket: tickets) {
            int cover = days[index] + ticket[0] - 1;
            int price = ticket[1];
            // 在有序数组中找>cover的最左
            int L = index;
            int R = N - 1;
            int pos = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (days[mid] > cover) {
                    pos = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            pos = pos == -1 ? N : pos;
            long cur = price + process(days, tickets, pos);
            cost = min(cost, cur);
        }
        return cost;
    }

    // 记忆化
    long long minCostToTravelOnDays1(vector<int> &days, vector<vector<int>> &tickets) {
        // dp[i] : 当前来到位置i, 返回从i位置购票到最后一天的最少费用
        int N = days.size();
        int dp[N + 1];
        memset(dp, -1, sizeof(dp));
        return process1(days, tickets, 0, dp);
    }

    // 当前来到第i次出行, 尝试每一种票策略, 返回覆盖到最后的时间的最小费用
    long long process1(vector<int> days, vector<vector<int>> tickets, int index, int *dp) {
        int N = days.size();
        if (index == N) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        // 尝试每一个购票策略
        long cost = LONG_MAX;
        for (auto &ticket: tickets) {
            int cover = days[index] + ticket[0] - 1;
            int price = ticket[1];
            // 在有序数组中找>cover的最左
            int L = index;
            int R = N - 1;
            int pos = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (days[mid] > cover) {
                    pos = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            pos = pos == -1 ? N : pos;
            long cur = price + process(days, tickets, pos);
            cost = min(cost, cur);
        }
        dp[index] = cost;
        return cost;
    }

    ///
    long long minCostToTravelOnDays2(vector<int> &days, vector<vector<int>> &tickets) {
        map<int, long long> dp;
        unordered_map<int, int> bestTickets;
        for (auto &ticket: tickets) {
            int orDefault = bestTickets.count(ticket[0]) > 0 ? bestTickets[ticket[0]] : INT_MAX;
            bestTickets[ticket[0]] = min(orDefault, ticket[1]);
        }
        dp[INT_MIN] = 0L;
        for (int i = 0; i < days.size(); i++) {
            int curDay = days[i];
            long long result = LONG_MAX;
            for (auto &[key, val]: bestTickets) {
                int validDay = key;
                int price = val;
                int preDay = max(0, curDay - validDay);
                auto iter = dp.upper_bound(preDay);
                if(iter != dp.end()) {
                    iter--;
                    long long prePrice = iter->second;
                    result = min(prePrice + price, result);
                }
            }
            dp[curDay, result];
        }
        return dp[days[days.size() - 1]];
    }

    long long minCostToTravelOnDays3(vector<int> &days, vector<vector<int>> &tickets) {

        int N = days.size();
        long long dp[N+1];
        memset(dp, 0, sizeof(dp));
        map<int, int> map;
        map[0] = 0;
        for (int i = 0; i < N; i++) {
            map[days[i]] = i + 1;
        }

        for (int i = 1; i <= N; i++) {
            long long minVal = LONG_MAX;
            int day = days[i - 1];
            for (auto& ticket : tickets) {
                int duration = ticket[0];
                int price = ticket[1];
                int pre = max(0, day - duration);
                // int preIdx = map.floorEntry(pre).getValue();

                auto iter = map.upper_bound(pre);
                if(iter != map.end()) {
                    iter--;
                    int preIdx = iter->second;
                    minVal = min(minVal, dp[preIdx] + price);
                }

            }
            dp[i] = minVal;
        }
        return dp[N];
    }

};


int main() {
    Solution *sl = new Solution();
    // vector<int> days = {1, 2, 3, 4};
    // vector<vector<int>> tickets;
    // vector<int> ticket;
    // ticket.push_back(1);
    // ticket.push_back(3);
    // tickets.push_back(ticket);
    // ticket.clear();
    // ticket.push_back(2);
    // ticket.push_back(5);
    // tickets.push_back(ticket);
    // ticket.clear();
    // ticket.push_back(3);
    // ticket.push_back(7);
    // tickets.push_back(ticket);
    vector<int> days = {2,3};
    vector<vector<int>> tickets;
    vector<int> ticket;
    ticket.push_back(1);
    ticket.push_back(268);
    tickets.push_back(ticket);
    ticket.clear();
    ticket.push_back(2);
    ticket.push_back(382);
    tickets.push_back(ticket);
    ticket.clear();
    ticket.push_back(3);
    ticket.push_back(518);
    tickets.push_back(ticket); // 382
    auto ans = sl->minCostToTravelOnDays2(days, tickets);
    cout << ans << endl;

    return 0;
}

