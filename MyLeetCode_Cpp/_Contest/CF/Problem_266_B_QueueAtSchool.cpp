// LeetCode Template
// Created by dvlcis @ 2022/10/5 14:24
// https://codeforces.com/contest/266/problem/B
// 

#include  <stdio.h>
#include  <string>
#include  <iostream>
#include  <vector>
#include  <unordered_map>
#include  <set>

using namespace std;

class Problem_266_B_QueueAtSchool {
public:

    string getArrangedQueue(string s, int t) {
        int size = s.size();
        while (t--) {
            for (int i = 0; i + 1 < size;) {
                if (s[i] == 'B' && s[i + 1] == 'G') {
                    swap(s[i], s[i + 1]);
                    i += 2;
                } else {
                    i++;
                }
            }
        }
        return s;
    }


};

int main() {
    Problem_266_B_QueueAtSchool *sl = new Problem_266_B_QueueAtSchool();
    int n;
    int t;
    cin >> n >> t;
    string s;
    cin >> s;
    auto ans = sl->getArrangedQueue(s, t);
    cout << ans << endl;
    return 0;
}


// for test
// int main() {
//     Problem_266_B_QueueAtSchool *sl = new Problem_266_B_QueueAtSchool();
//     int n = 5;
//     int t = 1;
//     string s = "BGGBG";
//     auto ans = sl->getArrangedQueue(s, t);
//     cout << ans << endl;
//     return 0;
// }