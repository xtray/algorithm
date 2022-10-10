// LeetCode Template
// Created by dvlcis @ 2022/10/4 00:40
// 

#include  <stdio.h>
#include  <iostream>
#include  <vector>

using namespace std;

struct Point {
    int x;
    int y;

    Point() : x(0), y(0) {}

    Point(int a, int b) : x(a), y(b) {}
};

class Problem_844_PairNumbers {
public:
    int pairNumbers(vector<Point> &p) {
        int N = p.size();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((p[i].x + p[j].x) % 2 == 0 && (p[i].y + p[j].y) % 2 == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     *
     * x, y四种情况， 奇偶， 奇奇， 偶偶， 偶奇， 分别能和前面的奇偶， 奇奇， 偶偶， 偶奇 的数目配对成偶数对，
     * 所以不断遍历数组并统计即可
     */
    int pairNumbers1(vector<Point> &p) {
        int N = p.size();
        int oe = 0;
        int oo = 0;
        int eo = 0;
        int ee = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            int x = p[i].x;
            int y = p[i].y;
            if (x % 2 == 0 && y % 2 == 0) {
                count += ee;
                ee++;
            } else if (x % 2 == 1 && y % 2 == 0) {
                count += oe;
                oe++;
            } else if (x % 2 == 0 && y % 2 == 1) {
                count += eo;
                eo++;
            } else if (x % 2 == 1 && y % 2 == 1) {
                count += oo;
                oo++;
            }
        }
        return count;
    }
};

// for test
int main() {
    Problem_844_PairNumbers *sl = new Problem_844_PairNumbers();

    return 0;
}