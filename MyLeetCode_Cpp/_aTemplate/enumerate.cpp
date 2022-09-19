//
// Created by dvlcis anima vivas on 2022/9/4.
//

// 枚举n的所有子集, 一直不断从最大的mask减1
// #define subset(i, n) for(int _=(1<<n)-1,i=_;i;i=_&i-1)
#define subset(i, n) for(int _=(1<<n)-1,i=_;i;i-=1)

// 从 n个1里选择r个1出来
#define cnr(i, n, r) for(int i=(1<<r)-1,_;i<1<<n;_=i+(i&-i),i=(i&~_)>>__builtin_ffs(i)|_)

#include <iostream>
#include "bitset"

using namespace std;

int main() {
    // int n = 1;//1
    // int m = 8;//1000
    // // 二进制末尾最后一个1的位置，从1开始
    // cout << __builtin_ffs(n) << endl;//输出1
    // cout << __builtin_ffs(m) << endl;//输出4
    const int n = 5;
    int r = 3;
    cnr(i, n, r) {
        cout<< bitset<n>(i) <<endl;
    }
    cout<<"========================"<<endl;
    subset(i, n) {
        cout<< bitset<n>(i) <<endl;
    }
    return 0;
}
