package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main()  {

	//var a, b int
	//input := bufio.NewScanner(os.Stdin)
	//for input.Scan() {
	//	a, _ = strconv.Atoi(strings.Split(input.Text(), " ")[0])
	//	b, _ = strconv.Atoi(strings.Split(input.Text(), " ")[1])
	//	fmt.Println(a+b)
	//}

	var N int
	var arr1[]int
	if _, err := fmt.Scan(&N); err != nil {
		return
	}
	//if _, err := fmt.Scan(&K); err != nil {
	//	return
	//}
	arr1 = make([]int, N)
	input := bufio.NewScanner(os.Stdin)
	for input.Scan() {
		str := strings.Split(input.Text(), " ")
		for i := 0; i < N; i++ {
			arr1[i], _ = strconv.Atoi(str[i])
		}
	}

	fmt.Println(len(arr1))
}
