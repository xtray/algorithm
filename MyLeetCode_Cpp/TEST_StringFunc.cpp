//
// Created bAy dvlcis anima vivas on 2021/9/27.
//

# include<string>
# include<iostream>
using namespace std;

int main()
{
    string str = "baabbba";
    cout <<
    (str.empty() ? 0 :str.rfind('a') == -1 ? 0
    :str.rfind('a')-str.find_last_not_of('a', str.rfind('a')));

    return 0;
}