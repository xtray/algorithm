// LeetCode Template
// Created by dvlcis @ 2022/10/1 00:06
// 

#include  <stdio.h>
#include  <iostream>

using namespace std;

class Problem_1694_ReformatNumber {
public:
    string reformatNumber(string number) {
        string tmp;
        for (char ch: number) {
            if (ch == ' ' || ch == '-') continue;
            tmp += ch;
        }
        int N = tmp.size();
        string ans;
        int end = N;
        int remain = N % 3;
        if (remain == 1) {
            end = N - 4;
        } else if (remain == 2) {
            end = N - 2;
        }
        int cnt = 0;
        for (int i = 0; i < end; i++) {
            ans += tmp[i];
            cnt++;
            if (cnt == 3) {
                ans += "-";
                cnt = 0;
            }
        }
        cnt = 0;
        for (int i = end; i < N; i++) {
            ans += tmp[i];
            cnt++;
            if (cnt == 2) {
                ans += "-";
                cnt = 0;
            }
        }
        N = ans.size();
        if (ans[N-1] == '-') {
            ans.resize(N-1);
        }
        return ans;
    }
};

// for test
int main() {
    Problem_1694_ReformatNumber *sl = new Problem_1694_ReformatNumber();

    string number = "--17-5 229 35-39475 ";
    auto ans = sl->reformatNumber(number);
    cout<<ans<<endl;
    return 0;
}