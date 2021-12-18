package main

import (
	"fmt"
)

func getHint(secret string, guess string) string {
	se := []byte(secret)
	gu := []byte(guess)
	// 统计 secret 中的字符频次
	count := make([]int, 10)
	for _,ch := range se {
		index:= ch - '0'
		count[index]++
	}
	var A int
	var B int
	// 检查 guess 数组
	for i,ch := range gu {
		index:= ch - '0'
		if count[index] > 0 { //字符存在
			B++ // B 目前代表存在的字符数
			count[index]--
		}
		if se[i] == gu[i] {
			A++
		}
	}
	return fmt.Sprintf("%vA%vB", A, B-A)
}

func main() {
	secret := "1122"
	guess := "1222"
	res := getHint(secret, guess)
	fmt.Println(res)

}