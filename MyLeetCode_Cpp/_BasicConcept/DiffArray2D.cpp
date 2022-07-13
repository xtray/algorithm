//
// Created by dvlcis anima vivas on 2022/5/18.
//
#include <iostream>

using namespace std;

const int n = 3;
const int m = 4;
int g[n][m] = {
        {1, 5, 6, 8},
        {9, 6, 7, 3},
        {5, 3, 2, 4}};
int sum[n][m];
int d[n + 1][m + 1];

void preSum() {
    sum[0][0] = d[0][0];
    for (int i = 1; i < n; i++) { // 第一列
        sum[i][0] = sum[i - 1][0] + g[i][0];
    }
    for (int j = 1; j < m; j++) { // 第一行
        sum[0][j] = sum[0][j - 1] + g[0][j];
    }
    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + g[i][j];
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            g[i][j] += d[i][j];
        }
    }
}

void printArray(int **arr, int row, int col) {
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            cout << ((int*)arr + i*col)[j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

void add(int x1, int y1, int x2, int y2, int v) {
    d[x1][y1] += v;
    d[x1][y2 + 1] -= v;
    d[x2 + 1][y1] -= v;
    d[x2 + 1][y2 + 1] += v;
}

int main() {
    add(0, 0, 2, 1, 3);
    add(1, 1, 2, 2, -1);
    preSum();
    printArray((int **) d, n + 1, m + 1);
    printArray((int **)g, n, m);

    return 0;
}


