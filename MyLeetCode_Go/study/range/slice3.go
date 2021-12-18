package main

import "fmt"

func main()  {
	ap:=[5]int{3:2}//下标3的值赋值为2
	fmt.Printf("%v\n", ap)

	s := make([] int, 5, 10)
	fmt.Println(len(s), cap(s)) //获取切片长度和容量
	s = append(s, 1, 2, 3, 4, 5, 6)//从尾部添加元素
	s = append(s);    //可以将一个切片添加到另一个切片中
	for _, al := range s {
		fmt.Print(al)
	}
}
