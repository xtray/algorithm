// LeetCode Template
// Created by dvlcis @ 2021/11/19 6:06 下午
// 

#include  <stdio.h>
#include  <iostream>

using namespace std;

class LEARN_LVAL_RVAL {
public:

};


int main()
{
    cout << "-------引用左值--------" << endl;
    int a = 5;
    int &add_a(a);

    cout << "   a  =" << a <<"    "<<"    &a = "<<&a<< endl;
    cout << "add_a =" << add_a<<"    "<< "&add_a = " << &add_a << endl;
    cout << "-----------------------" << endl;

    cout << "-------引用右值--------" << endl;
    int b = 10;
    int &&add_b(b + 1);
    cout << "   b  =" << b << "    " << "    &b = " << &b << endl;
    cout << "add_b =" << add_b << "    " << "&add_b = " << &add_b << endl;
    add_b++;
    cout << "add_b++ =" << add_b << "    " << "&add_b++ = " << &add_b << endl;
    cout << "-----------------------" << endl;

    return 0;
}