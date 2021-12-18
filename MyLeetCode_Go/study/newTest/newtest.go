
package main

import "fmt"

func main(){
	var a1 *int  //只是声明int类型的变量，不开辟内存地址
	fmt.Println(a1)//nil
	var a2=new(int) //申请1个 int指针的变量，开辟内存地址
	fmt.Println(a2)
	*a2=100
	fmt.Println(*a2)

}