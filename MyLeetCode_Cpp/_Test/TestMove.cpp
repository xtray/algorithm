//
// Created by dvlcis anima vivas on 2022/8/21.
//

#include <iostream>
#include <string>

using namespace std;

int main() {
    string a = "123";
    // const string b = "121212"; // std::move for const is useless
    string b = "121212";
    a = std::move(b);
    cout << "a: " << a << endl;
    cout << "b: " << b << endl;


    return 0;
}

