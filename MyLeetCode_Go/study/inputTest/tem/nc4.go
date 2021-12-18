package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

// 读取一行输入按空格分割
func read1(reader *bufio.Reader) []int {
	line, _ := reader.ReadString('\n')
	list := strings.Split(line[:len(line)-1], " ")
	array := make([]int, len(list))
	for idx, val := range list {
		array[idx], _ = strconv.Atoi(val)
	}
	return array
}

// 读取一行输入按空格分割
func read(reader *bufio.Reader) []int {
	line, _, _:= reader.ReadLine()
	list := strings.Split(string(line), " ")
	array := make([]int, len(list))
	for idx, val := range list {
		array[idx], _ = strconv.Atoi(val)
	}
	return array
}
func main() {
	reader := bufio.NewReader(os.Stdin)

	// 将strs中的字符串转为整数后传入
	array := read(reader)
	n := array[0]
	k := array[1]

	var arr1, arr2 []int
	arr1 = read(reader)
	arr2 = read(reader)

	sort.Ints(arr1)
	sort.Ints(arr2)

	fmt.Printf("n: %v, v:%v", n, k)

}
