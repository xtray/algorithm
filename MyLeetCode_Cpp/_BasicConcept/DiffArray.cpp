//
// Created by dvlcis anima vivas on 2022/5/18.
//
#include <iostream>
using namespace std;

int d[6] = {0};

void add(int L, int R, int V) {
    d[L] += V;
    d[R + 1] -= V;
}

int main() {
    int arr[5] = {1, 3, 7, 5, 2};
    add(2, 4, 5);
    add(1, 3, 2);
    add(0, 2, -3);
    for (int i = 1; i < 5; i++) {
        d[i] += d[i - 1];
    }
    for (int i = 0; i < 5; i++) {
        arr[i] += d[i];
        cout << arr[i] << " ";
    }
    cout << endl;
    memset(d, 0, sizeof(d));
    return 0;
}


