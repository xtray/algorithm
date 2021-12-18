package main

import (
	"fmt"
)

var (
	//实例化一个长度容量为5的slice
	//从slice切出一个slice2
	slice = make([]int, 5)
	slice2 = slice[2:]
)

func main() {
	printSlice()
	//修改slice2第0个元素
	slice2[0] = 1
	printSlice()
	//扩容slice2
	slice2 = append(slice2, 2)
	printSlice()
	//修改slice2第0个元素
	slice2[0] = 3
	printSlice()
	//修改slice第0个元素
	slice[0] = 4
	printSlice()
}

func printSlice() {
	fmt.Printf("len=%d cap=%d %v\n", len(slice), cap(slice), slice)
	fmt.Printf("len=%d cap=%d %v\n", len(slice2), cap(slice2), slice2)
}
