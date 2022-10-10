// LeetCode Template
// Created by dvlcis @ 2022/10/9 01:12
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class Problem_856_ParenthesesScore {
public:
    int *dfs(string s, int i) {
        if (s[i] == ')') {
            return new int[2]{1, i};
        }
        int N = s.size();
        int ans = 0;
        while (i < N && s[i] != ')') {
            int *info = dfs(s, i + 1);
            ans += info[0] * 2;
            i = info[1] + 1;
        }
        return new int[]{ans, i};
    }

    int scoreOfParentheses(string s) {
        if (s.empty()) return 0;
        int *ret = dfs(s, 0);
        int ans = ret[0] >> 1;
        delete[] ret;
        return ans;
    }
};

// for test
int main() {
    Problem_856_ParenthesesScore *sl = new Problem_856_ParenthesesScore();
    string s = "(()(()))";
    auto ans = sl->scoreOfParentheses(s);
    cout<<ans<<endl;

    return 0;
}