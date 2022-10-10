// LeetCode Template
// Created by dvlcis @ 2022/10/7 21:49
// 

#include <iostream>
#include <cstring>
// #include <malloc.h>
using namespace std;

int sum1,sum2,sum3,sum4,sum5;
int iArray[]={1,4,2,7,13,32,21,48,16,30};
int *iPtr;

void Access_data_through_pointers(); //通过指针访问数据
void Pointer_operations(); //指针运算
void Dynamic_memory_allocation(); //动态内存分配
void Pointers_and_arrays(); //数组和指针

int main() {
    //Point:
    Access_data_through_pointers();
    cout<<"\n";
    Pointer_operations();
    cout<<"\n";
    Dynamic_memory_allocation();
    cout<<"\n";

    return 0;
}

void Access_data_through_pointers(){
    int a=10,b=20,sum,accumulate,*pa,*pb;
    pa=&a;
    pb=&b;
    sum=*pa+*pb;
    accumulate=*pa * *pb;
    cout<<"a="<<a<<","<<"b="<<b<<","<<"a+b="<<a+b<<","<<"a*b="<<a*b<<","<<endl;
    cout<<"sum="<<sum<<","<<"accumulate="<<accumulate<<","<<endl;
}
void Pointer_operations(){
    int a,b,c,*pmax,*pmin;
    cout<<"input three numbers:  "<<flush;
    cin>>a>>b>>c;
    if(a>b){
        pmax=&a;
        pmin=&b;
    }
    else{
        pmax=&b;
        pmin=&a;
    }
    if(c>*pmax)
        pmax=&c;
    if(c<*pmin)
        pmin=&c;
    cout<<"max="<<*pmax<<","<<"min"<<*pmin<<","<<endl;
}
void Dynamic_memory_allocation(){
    char *ptr1,*ptr2,*temp;
    ptr1= (char *) malloc(sizeof(char)*30);
    ptr2= (char *) malloc(sizeof(char)*20);
    temp= (char *) malloc(sizeof(char)*30);
//    getchar();  //1.吸收换行符，一定要加
    cin.ignore(); //2.也可以用cin.ignore()吸收  //3.或者前面用cin.get() , cin.getline()作为输入，这两个函数以'\n'作为结束符
    cout<<"请输如字符串1：";
    gets(ptr1);
    cout<<"请输如字符串2：";
    gets(ptr2);
    cout<<"字符串1------------字符串2"<<endl;
    cout<<ptr1<<"------------"<<ptr2<<endl;
    strcpy(temp,ptr1);
    strcpy(ptr1,ptr2);
    strcpy(ptr2,temp);
    cout<<"字符串1------------字符串2"<<endl;
    cout<<ptr1<<"------------"<<ptr2<<endl;
    free(ptr1);
    free(ptr2);
    //这里用的是malloc和free，C++中有更优越的new和delete，当然在C++中也是用后面两个函数居多
}
void Pointers_and_arrays(){
    int size,n;
    size= sizeof(iArray)/ sizeof(*iArray);
    for(n=0;n<size;n++) //Method one
        sum1+=iArray[n];
    iPtr=iArray;
    for(n=0;n<size;n++) //Method two
        sum2+=*iPtr++;
    iPtr=iArray; //此处不能省略，因为Method two修改了iPtr
    for(n=0;n<size;n++) //Method three
        sum3+=*(iPtr+n);
    iPtr=iArray; //此处不能省略，因为Method three修改了iPtr
    for(n=0;n<size;n++) //Method four
        sum4+=iPtr[n];
    for(n=0;n<size;n++) //Method five
        sum5+=*(iArray+n);
    cout<<sum1<<endl
        <<sum2<<endl
        <<sum3<<endl
        <<sum4<<endl
        <<sum5<<endl;
}