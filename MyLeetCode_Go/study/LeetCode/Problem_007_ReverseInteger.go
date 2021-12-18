package main

import (
	"fmt"
	"math"
)

func reverse(x int) int {
	neg := ((x>>63) & 1) == 1
	if !neg {
		x = -x
	}
	m := math.MinInt32/10
	o := math.MinInt32%10
	res := 0
	for x!=0 {
		if res < m || res == m && x%10 < o {
			return 0
		}
		res = res * 10 + x%10
		x/=10
	}
	if neg {
		return res
	}
	return abs(res)
}

func abs(x int) int {
	if x>=0 {
		return x
	}
	return -x
}

func main() {

	num := -123
	ans :=  reverse(num)
	fmt.Println(ans)

}
